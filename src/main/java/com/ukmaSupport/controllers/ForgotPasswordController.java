package com.ukmaSupport.controllers;

import com.ukmaSupport.mailService.templates.OrderIsDoneMail;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/forgotPassword")
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderIsDoneMail orderIsDoneMail;

    @RequestMapping(method = RequestMethod.GET)
    public String viewForgotPasswordForm() {

        return "registration/forgotPassword";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String sendChangePasswordMail(@ModelAttribute("email") String email, ModelMap modelMap){

        modelMap.addAttribute("email", email);

        if(email == null || email.trim().isEmpty())
            modelMap.addAttribute("error", "Item Email is required!");
        else{
            //User user = userService.getByEmail(email);
            if(!userService.getByEmail(email).equals(null)){
                orderIsDoneMail.send(email);
                modelMap.addAttribute("success", "Success!");
            }
            else{
                modelMap.addAttribute("error", "Item Email is required!");
            }

        }

        return "registration/forgotPassword";

    }

    /*@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String verifyEmail(@RequestParam("id") int id) {

        User user = userService.getById(id);
        user.setAccountStatus("active");
        userService.saveOrUpdate(user);


        return "registration/emailVerificationSuccess";
    }*/

}
