package com.ukmaSupport.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by viktor on 02.11.15.
 */

@Controller

public class UserController {
    @RequestMapping(value = "/userhome", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("userhome", "command", new Users());
    }

    @RequestMapping(value = "/userhome", method = RequestMethod.POST)
    public String addStudent(@Valid @ModelAttribute("users")Users users,BindingResult bindingResult, ModelMap model) {
        System.out.println("error"+bindingResult.hasErrors());
        model.addAttribute("title", users.getTitle());
        model.addAttribute("order", users.getOrder());
        model.addAttribute("id", users.getId());
     if(bindingResult.hasErrors()) {
         return "userhome";
     }
      return "result";

    }
}
