package com.springapp.mvc.web;

import com.springapp.mvc.entity.Category;
import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.entity.EnterpriseRatio;
import com.springapp.mvc.entity.User;
import com.springapp.mvc.service.*;
import com.springapp.mvc.utils.UrlParameterValidator;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class DefaultController {
    public final static int START_INDEX_FOR_PAGINATION = 0;
    public final static int MAX_NUMBER_OF_ELEMENTS_ON_PAGE = 5;
    public final static int DEFAULT_PAGE_NUMBER = 1;

    @Autowired
    private UrlParameterValidator parameterValidator;

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IEnterpriseRatioService enterpriseRatioService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private IUserService userService;

    private ModelMap indexModelAttribute(){
        ModelMap model = new ModelMap();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated()) {
            User user = userService.getUser(auth.getName());
            if (user != null) {
                model.addAttribute("user", user);
            }
        }
        model.addAttribute("CategoryId", 0);
        model.addAttribute("AllEnterprisesCount", enterpriseService.countItems());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("cities", cityService.getAll());
        model.addAttribute("enterprisesCount", method1(0));
        model.addAttribute("OrderByRatio", true);
        model.addAttribute("OrderByDiscount", true);
        model.addAttribute("AllEnterprisesInAllCats", enterpriseService.getAllEnterprisesInCategories());
        model.addAttribute("selectedCity", "Choose city");
        model.addAttribute("topList", enterpriseRatioService.getTopList());
        model.addAttribute("startPage", "/index/");
        model.addAttribute("numberOfPages", enterpriseService.numberOfPagesForPagination(MAX_NUMBER_OF_ELEMENTS_ON_PAGE));

        return model;
    }

    @RequestMapping(value = {"", "/", "welcome"}, method = RequestMethod.GET)
    public String listEnterprises(ModelMap model) {

        model.addAllAttributes(indexModelAttribute());
        model.addAttribute("enterprises", enterpriseService.getScope(START_INDEX_FOR_PAGINATION, MAX_NUMBER_OF_ELEMENTS_ON_PAGE));
        model.addAttribute("currentPageNumber", DEFAULT_PAGE_NUMBER);

        return "index";
    }

    @RequestMapping(value = {"/index/&currentPageNumber={pageNumber}"}, method = RequestMethod.GET)
    public String listEnterprisesPagination(ModelMap model, @PathVariable int pageNumber) {

        model.addAllAttributes(indexModelAttribute());
        model.addAttribute("enterprises", enterpriseService.getScope(MAX_NUMBER_OF_ELEMENTS_ON_PAGE *(pageNumber-1), MAX_NUMBER_OF_ELEMENTS_ON_PAGE));
        model.addAttribute("currentPageNumber", pageNumber);

        return "index";
    }


    @RequestMapping(value = "/params", method = RequestMethod.GET)
    public String listEnterprisesById(@RequestParam MultiValueMap<String, String> params, ModelMap model) {

        model.mergeAttributes(params.toSingleValueMap());
        int categoryId = parameterValidator.getInt(params.getFirst("CategoryId"));
        int townId = parameterValidator.getInt(params.getFirst("TownId"));
        int pageNumber = parameterValidator.getInt(params.getFirst("currentPageNumber"));
        boolean orderByDiscount = parameterValidator.getBoolean(params.getFirst("OrderByDiscount"));
        boolean orderByRatio = parameterValidator.getBoolean(params.getFirst("OrderByRatio"));
        int numberOfPagesForPagination=0;
        /*model.addAttribute("enterprises", enterpriseService.getAllBy(categoryId, townId, orderByDiscount, orderByRatio));*/
        model.addAttribute("enterprisesCount", method1(townId));
        model.addAttribute("AllEnterprisesCount", enterpriseService.countEnterprises(0,townId));
        model.addAttribute("selectedCity", townId == 0 ? "All" : cityService.get(townId).getName());
        model.addAttribute("topList", enterpriseRatioService.getTopList());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("cities", cityService.getAll());
        model.addAttribute("startPage", "/index/");

        if (pageNumber>1){
            model.addAttribute("enterprises", enterpriseService.getAllBy(categoryId, townId, orderByDiscount, orderByRatio, MAX_NUMBER_OF_ELEMENTS_ON_PAGE * (pageNumber - 1), MAX_NUMBER_OF_ELEMENTS_ON_PAGE));
            model.addAttribute("currentPageNumber", pageNumber);
        }else {
            model.addAttribute("enterprises", enterpriseService.getAllBy(categoryId, townId, orderByDiscount, orderByRatio, START_INDEX_FOR_PAGINATION, MAX_NUMBER_OF_ELEMENTS_ON_PAGE));
            model.addAttribute("currentPageNumber", DEFAULT_PAGE_NUMBER);
        }

        if (enterpriseService.countEnterprises(categoryId,townId)%MAX_NUMBER_OF_ELEMENTS_ON_PAGE!=0){
            numberOfPagesForPagination = (enterpriseService.countEnterprises(categoryId,townId)/MAX_NUMBER_OF_ELEMENTS_ON_PAGE)+1;
        } else {
            numberOfPagesForPagination = enterpriseService.countEnterprises(categoryId,townId)/MAX_NUMBER_OF_ELEMENTS_ON_PAGE;
        }
        model.addAttribute("numberOfPages", numberOfPagesForPagination);

        return "index";
    }

    @Cacheable(value = "countCategory")
    private List<AbstractMap.SimpleEntry<Category, Integer>> method1(int townId) {
        List<AbstractMap.SimpleEntry<Category, Integer>> values = new ArrayList<AbstractMap.SimpleEntry<Category, Integer>>();
        for (Category category : categoryService.getAll()) {
            values.add(new AbstractMap.SimpleEntry<Category, Integer>(category, enterpriseService.countEnterprises(category.getId(), townId)));
            System.out.println(category.getName() + " | " + enterpriseService.countEnterprises(category.getId(), townId));
        }
        return values;
    }


    @RequestMapping(value = "/ajaxParams", method = RequestMethod.GET)
    public
    @ResponseBody
    String ratioStars(@RequestParam MultiValueMap<String, String> params) {
        JSONObject json = new JSONObject();
        if (params.getFirst("user-id").equals("")) {
            try {
                json.put("status", "ERR");
                json.put("msg", "Login first, please!");
            } catch (JSONException E) {
                System.out.println("can't form Json response");
            }
            return (json.toString());
        }
        Integer userId = Integer.parseInt(params.getFirst("user-id"));
        System.out.println(userId);
        Integer enterpriseId = Integer.parseInt(params.getFirst("enterprise-id"));
        Integer value = Integer.parseInt(params.getFirst("score"));

        if (!(enterpriseRatioService.userAlreadyVote(userId, enterpriseId))) {
            EnterpriseRatio enterpriseRatio = new EnterpriseRatio();
            enterpriseRatio.setUser(userService.get(userId));
            enterpriseRatio.setEnterprise(enterpriseService.get(enterpriseId));
            enterpriseRatio.setValue(value);
            enterpriseRatioService.add(enterpriseRatio);
            System.out.println("ratio added");
//            Integer votesValue = enterpriseRatioService.votesValue();
            try {
                json.put("status", "OK");
                json.put("msg", "Thank you");
            } catch (JSONException E) {
                System.out.println("can't form Json response");
            }
            return (json.toString());
        } else {
            try {
                json.put("status", "ERR");
                json.put("msg", "You have already vote!");
            } catch (JSONException E) {
                System.out.println("can't form Json response");
            }
            return (json.toString());
        }

    }

    @RequestMapping(value = "/ajaxRatio", method = RequestMethod.GET)
    public
    @ResponseBody
    String ratioStars1(@RequestParam MultiValueMap<String, String> params) {
        JSONObject json = new JSONObject();
        Integer user;
        if (!(params.getFirst("user").equals(""))) {
        user = Integer.parseInt(params.getFirst("user"));
        }
        else   user = 0;
         System.out.println(user);
        Integer enterprise = Integer.parseInt(params.getFirst("enterprise"));
        Integer value = enterpriseRatioService.getVoteValue(enterprise);
        Integer votes = enterpriseRatioService.getVotes(enterprise);
        boolean readOnly = enterpriseRatioService.userAlreadyVote(user, enterprise);
        try {
            json.put("status", "OK");
            json.put("msg", value);
            json.put("vis", readOnly);
            json.put("value", value);
            json.put("votes", votes);
        } catch (JSONException E) {
            System.out.println("can't form Json response");
        }
        return (json.toString());


    }
 /*   @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {
        userRepository.save(user);
        return "redirect:/user";
    }*/


   /* @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        userRepository.delete(userRepository.findOne(userId));
        return "redirect:/user";
    }*/

}
