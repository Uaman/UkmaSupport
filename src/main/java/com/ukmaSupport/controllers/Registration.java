package com.ukmaSupport.controllers;

import com.ukmaSupport.models.User;
import com.ukmaSupport.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/register")
public class Registration {
    @Autowired
    @Qualifier("registrationValidator")
    private RegistrationValidator validator;
//    @Autowired
//    @Qualifier("registrationErrors")
//    private Errors errors;

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Model model) {
        User userForm = new User();
        model.addAttribute("userForm", userForm);
        return "registration/registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") User user, Model model, BindingResult result) {
        model.addAttribute("userForm", user);
        //if(validator.validate(user, errors))
        if(user.getFirstName()==null||user.getFirstName().equals(""))
            return "registration/registration";
        if(user.getLastName()==null||user.getLastName().equals(""))
            return "registration/registration";
        if(user.getEmail()==null||user.getEmail().equals(""))
            return "registration/registration";
        if(user.getPassword()==null||!user.getPassword().equals(user.getConfPassword())) {
            return "registration/registration";
        }
//        if(result.hasErrors()) {
//            return "registration";
//        }
        return "registration/registrationSuccess";
    }
}