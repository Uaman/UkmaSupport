package com.ukmaSupport.controllers;

import com.ukmaSupport.mailService.templates.RegistrationMail;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.utils.Constants;
import com.ukmaSupport.utils.PasswordEncryptor;
import com.ukmaSupport.utils.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/register")
public class Registration {
    @Autowired
    private UserService userService;

    @Autowired
    private RegistrationMail registrationMail;


    @Autowired
    @Qualifier("registrationValidator")
    private RegistrationValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Model model) {
        User userForm = new User();
        model.addAttribute("userForm", userForm);
        return "registration/registration";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("userForm") User user, BindingResult result) {
        validator.validate(user, result);
        if (result.hasErrors())
            return "registration/registration";
        user = PasswordEncryptor.encodeUser(user);
        user.setEmail(user.getEmail().toLowerCase());
        userService.saveOrUpdate(user);
        String email = user.getEmail();
        user = userService.getByEmail(email);
        registrationMail.send(email, user.getId());
        return "registration/registrationSuccess";
    }
}