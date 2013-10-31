package com.springapp.mvc.web;

import com.springapp.mvc.entity.Enterprise;
import com.springapp.mvc.service.ICategoryService;
import com.springapp.mvc.service.ICityService;
import com.springapp.mvc.service.IEnterpriseService;
import com.springapp.mvc.utils.FileValidator;
import com.springapp.mvc.utils.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
public class EnterpriseController {

    @Autowired
    private IEnterpriseService enterpriseService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ICityService cityService;
    @Autowired
    FileValidator fileValidator;
    InputStream inputStream = null;
    OutputStream outputStream = null;

    @RequestMapping(value = "enterprises")
    public String enterprises(ModelMap modelMap) {
        modelMap.addAttribute("enterprise", new Enterprise());
        modelMap.addAttribute("uploadedFile", new UploadedFile());
        modelMap.addAttribute("categoryList", categoryService.getAll());
        modelMap.addAttribute("cityList", cityService.getAll());
        modelMap.addAttribute("action", "add");
        return "enterprises";
    }

    @RequestMapping(value = "enterprises/uploadFile")
    public String fileUploaded(@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
                               BindingResult bindingResult, ModelMap modelMap) {


        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, bindingResult);

        String fileName = file.getOriginalFilename();

        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("org.springframework.validation.BindingResult.register", bindingResult);
            modelMap.addAttribute("categoryList", categoryService.getAll());
            modelMap.addAttribute("cityList", cityService.getAll());
            modelMap.addAttribute("action", "add");
            return "enterprises";
        }
        try {
            inputStream = file.getInputStream();

            File dir = new File("c:\\Temp");
            if (!dir.exists()) {
                if (dir.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            File newFile = new File("c:\\Temp\\" + fileName);
            if (!newFile.exists()) {
                newFile.createNewFile();
            }

            outputStream = new FileOutputStream(newFile);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Enterprise enterprise = new Enterprise();
        enterprise.setLogoPath(fileName);
        modelMap.addAttribute("enterprise", enterprise);
        modelMap.addAttribute("categoryList", categoryService.getAll());
        modelMap.addAttribute("cityList", cityService.getAll());
        modelMap.addAttribute("action", "add");
        return "enterprises";
    }

    @RequestMapping(value = "enterprises/add", method = RequestMethod.POST)
    public String addEnterprise(@ModelAttribute("enterprise") @Valid Enterprise enterprise,
                                BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("enterprise", enterprise);
            modelMap.addAttribute("categoryList", categoryService.getAll());
            modelMap.addAttribute("cityList", cityService.getAll());
            modelMap.addAttribute("action", "add");
            return "enterprises";
        }

        File tempFile = new File("c:\\Temp\\" + enterprise.getLogoPath());

        File logo = new File("c:\\Users\\Vitaliy\\FinalProject3\\SSDP\\src\\main\\webapp\\resources\\images\\logos\\" + enterprise.getLogoPath());
        try {
            inputStream = new FileInputStream(tempFile);
            outputStream = new FileOutputStream(logo);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        enterpriseService.add(enterprise);
        return "redirect:/";
    }

    @RequestMapping(value = "enterprises/delete/{firmId}", method = RequestMethod.GET)
    public String deleteEnterprise(@PathVariable("firmId") int firmId) {
        Enterprise enterprise = enterpriseService.get(firmId);
        try {
            File logo = new File("c:\\Users\\Vitaliy\\FinalProject3\\SSDP\\src\\main\\webapp\\resources\\images\\logos\\" + enterprise.getLogoPath());
            logo.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        enterpriseService.delete(firmId);
        return "redirect:/";
    }

    @RequestMapping(value = "enterprises/edit/{firmId}", method = RequestMethod.GET)
    public String getEditingEnterpriseId(@PathVariable("firmId") int firmId, ModelMap modelMap) {
        Enterprise enterpriseToEdit = enterpriseService.get(firmId);
        modelMap.addAttribute("enterprise", enterpriseToEdit);
        modelMap.addAttribute("categoryList", categoryService.getAll());
        modelMap.addAttribute("cityList", cityService.getAll());
        modelMap.addAttribute("action", "edit");
        return "enterprises";
    }

    @RequestMapping(value = "enterprises/edit", method = RequestMethod.POST)
    public String editEnterprise(@ModelAttribute("enterprise") Enterprise enterprise) {
        enterpriseService.update(enterprise);
        return "redirect:/";
    }
}


