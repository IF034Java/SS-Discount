package com.springapp.mvc.web;

import com.springapp.mvc.entity.Category;
import com.springapp.mvc.entity.City;
import com.springapp.mvc.service.ICategoryService;
import com.springapp.mvc.utils.CheckBoxesContainer;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CategoryController {


    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = {"categories", "Categories", "Category"})
    public String categoriesHome() {
        return "redirect:category";
    }

    @RequestMapping(value = "category")
    public String categories(ModelMap modelMap) {
        modelMap.addAttribute("category", new Category());
        modelMap.addAttribute("categoriesContainer", new CheckBoxesContainer());
        modelMap.addAttribute("sourceList", categoryService.getAll());
        modelMap.addAttribute("action", "add");
        return "category";
    }

    @RequestMapping(value = "/categoryDelete", method = RequestMethod.GET)
    public
    @ResponseBody
    String deleteCategory(@RequestParam MultiValueMap<String, String> params) {
        JSONObject json = new JSONObject();
        Integer categoryId = Integer.parseInt(params.getFirst("category"));
        System.out.println("catched "+categoryId);
        categoryService.delete(categoryId);
        try {
            json.put("status","OK");

        } catch (JSONException E) {
            System.out.println("can't form Json response");
        }
        return (json.toString());
    }


    @RequestMapping(value = "/categoryAdd", method = RequestMethod.GET)
    public
    @ResponseBody
    String addCategory(@RequestParam MultiValueMap<String, String> params) {
        JSONObject json = new JSONObject();
        String name = params.getFirst("name");

        Category category = new Category();
        category.setName(name);
        categoryService.add(category);

        Integer idCategory = category.getId();

        try {
            json.put("status","OK");
            json.put("idCategory", idCategory);
        } catch (JSONException E) {
            System.out.println("can't form Json response");
        }
        return (json.toString());
    }

    @RequestMapping(value = "/categoryUpdate", method = RequestMethod.GET)
    public
    @ResponseBody
    String updateCategory(@RequestParam MultiValueMap<String, String> params) {
        JSONObject json = new JSONObject();
        String name = params.getFirst("name");
        Integer id = Integer.parseInt(params.getFirst("id"));
        Category category = new Category();
        category.setName(name);
        category.setId(id);
        categoryService.update(category);

        try {
            json.put("status","OK");

        } catch (JSONException E) {
            System.out.println("can't form Json response");
        }
        return (json.toString());
    }



}