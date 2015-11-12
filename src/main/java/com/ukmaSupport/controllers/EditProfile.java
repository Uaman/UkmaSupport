package com.ukmaSupport.controllers;

import com.ukmaSupport.models.*;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.utils.PasswordChangeValidator;
import com.ukmaSupport.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping(value = "/editProfile")
public class EditProfile {

    @Autowired
    private UserService userDao;

    @Autowired
    @Qualifier("passChangeValidator")
    private PasswordChangeValidator validator;

    @RequestMapping(method = RequestMethod.GET)
    public String viewRegistration(Model model) {
       PasswordPair passwordPair = new PasswordPair();
        model.addAttribute("passChangeForm", passwordPair);
        return "userPage/editProfile";
    }


   @RequestMapping(method = RequestMethod.POST)
    public String passChange(@ModelAttribute("passChangeForm") PasswordPair passwordPair, Model model, BindingResult result) {


        validator.validate(passwordPair.getPassword(), passwordPair.getConfPassword(), result);
        if (result.hasErrors())
            return "userPage/editProfile";
        String pass = PasswordEncryptor.encode(passwordPair.getPassword());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userDao.getByEmail(name);
        user.setPassword(pass);
        userDao.saveOrUpdate(user);


        return "userPage/passwordChangeSuccess";
    }
}