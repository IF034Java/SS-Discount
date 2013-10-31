package com.springapp.mvc.web;

import com.springapp.mvc.entity.User;
import com.springapp.mvc.service.IRoleService;
import com.springapp.mvc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "registration")
public class RegistrationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(method = RequestMethod.GET)
    public String registrationForm(Model model)
    {
        model.addAttribute(new User());
        return "registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String registrationForm(@Valid User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "registration";
        }
        user.setRole(roleService.getRole("ROLE_USER"));
        userService.add(user);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:/";
    }

    private User addDefaultUserAuthorities(User user)
    {
        user.setRole(roleService.getRole("ROLE_USER"));
        return user;
    }
}
