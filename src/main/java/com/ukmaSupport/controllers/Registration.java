package com.ukmaSupport.controllers;

import com.ukmaSupport.mailService.templates.RegistrationMail;
import com.ukmaSupport.models.*;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.utils.Constants;
import com.ukmaSupport.utils.RegistrationValidator;
import com.ukmaSupport.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/register")
public class Registration {
    @Autowired
    private UserService userDao;

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
    public String processRegistration(@ModelAttribute("userForm") User user, Model model, BindingResult result) {
        model.addAttribute("userForm", user);
        validator.validate(user, result);
        if (result.hasErrors())
            return "registration/registration";
        user = PasswordEncryptor.encodeUser(user);
        userDao.saveOrUpdate(user);

        String email = user.getEmail();
        user = userDao.getByEmail(email);
        registrationMail.send(email, Constants.LOCAL_SERVER + Constants.VERIFICATION + user.getId());

        return "registration/registrationSuccess";
    }
}