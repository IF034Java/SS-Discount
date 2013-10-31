package com.springapp.mvc.web;

import com.springapp.mvc.entity.Category;
import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.service.ICategoryService;
import com.springapp.mvc.service.ICityService;
import com.springapp.mvc.service.IEnterpriseRatioService;
import com.springapp.mvc.service.IEnterpriseService;
import com.springapp.mvc.utils.CheckBoxesContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private IEnterpriseService enterpriseService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IEnterpriseRatioService enterpriseRatioService;

    @Autowired
    private ICityService cityService;

    @RequestMapping(value = {"views", "Views", "View"})
    public String viewsHome() {
        return "redirect:view";
    }

    @RequestMapping(value = "view")
    public String views(ModelMap modelMap) {
        modelMap.addAttribute("enterprise", new Enterprise());
        modelMap.addAttribute("enterpriseList", enterpriseService.getAll());
        modelMap.addAttribute("enterprisesCount", method1());
        modelMap.addAttribute("selectedTab", 1);
        modelMap.addAttribute("categoryList", categoryService.getAll());
        modelMap.addAttribute("action", "add");
        return "view";
    }

    private List<AbstractMap.SimpleEntry<Category, Integer>> method1() {
        List<AbstractMap.SimpleEntry<Category, Integer>> values = new ArrayList<AbstractMap.SimpleEntry<Category, Integer>>();
        for (Category category : categoryService.getAll()) {
            values.add(new AbstractMap.SimpleEntry<Category, Integer>(category, enterpriseService.countEnterprises(category.getId(),0)));
            System.out.println(category.getName() + " | " + enterpriseService.countEnterpriseByCategory(category.getId()));
        }
        return values;
    }

    @RequestMapping(value = "view/addCategory",method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("category") @Valid Category category,BindingResult bindingResult, ModelMap model) {
        if(bindingResult.hasErrors())
            return "forward:/view";
        categoryService.add(category);
        model.addAttribute("selectedTab", 2);
        return "forward:/view";
    }

    @RequestMapping(value = "view/deleteEnterprise/{enterpriseId}", method = RequestMethod.GET)
    public String delEnterprise(@PathVariable int enterpriseId, ModelMap model){
        enterpriseService.delete(enterpriseId);
        model.addAttribute("selectedTab", 1);
        return "forward:/view";
    }

    @RequestMapping(value = "view/deleteCategory/{categoryId}", method = RequestMethod.GET)
    public String delCategory(@PathVariable int categoryId, ModelMap model){
        categoryService.delete(categoryId);
        model.addAttribute("selectedTab", 2);
        return "forward:/view";
    }

    @RequestMapping(value = "view/editCategory/{categoryId}", method = RequestMethod.GET)
    public String editCategory(@PathVariable int categoryId, ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.get(categoryId));
        modelMap.addAttribute("categoriesContainer", new CheckBoxesContainer());
        modelMap.addAttribute("categoryList", categoryService.getAll());
        modelMap.addAttribute("selectedTab", 2);
        modelMap.addAttribute("action", "edit");
        return "view";
    }

    @RequestMapping(value = "view/editCategory", method = RequestMethod.POST)
    public String updateCategory(@ModelAttribute Category category, ModelMap model) {

        System.out.println(String.format("INFO: Category updated//name=%s",category.getName()));
        categoryService.update(category);
        model.addAttribute("selectedTab", 2);
        return "forward:/view";
    }

}
