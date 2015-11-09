package com.ukmaSupport.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainPage {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Welcome!");
        return "mainPage/mainPage";
    }

    @RequestMapping({"/login"})
    public String showLoginForm() {
        return "mainPage/loginPage";
    }


    @RequestMapping({"/login_error"})
    public String showLoginError() {
        return "mainPage/failedLogin";
    }

    @RequestMapping({"/succesfullRegistration"})
    public String authorizationSuccesfull() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return "redirect:/userhome";
    }
}