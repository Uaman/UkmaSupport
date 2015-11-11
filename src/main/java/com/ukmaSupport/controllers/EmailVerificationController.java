package com.ukmaSupport.controllers;

import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailVerificationController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public String verifyEmail(@RequestParam("id") int id) {

        User user = userService.getById(id);
        user.setAccountStatus("active");
        userService.saveOrUpdate(user);


        return "registration/emailVerificationSuccess";
    }

}
