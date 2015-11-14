package com.ukmaSupport.controllers;

import com.ukmaSupport.mailService.templates.ForgotPasswordMail;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private ForgotPasswordMail forgotPasswordMail;

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String viewForgotPasswordForm() {

        return "registration/forgotPassword";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String sendChangePasswordMail(@ModelAttribute("email") String email, ModelMap modelMap){

        modelMap.addAttribute("email", email);

        if(email == null || email.trim().isEmpty())
            modelMap.addAttribute("error", "Item Email is required!");
        else{
            User user = userService.getByEmail(email);

            if(!userService.getByEmail(email).equals(null)){
                forgotPasswordMail.send(email, Constants.LOCAL_SERVER + Constants.CHANGE_PASSWORD + user.getId());
                modelMap.addAttribute("success", "Success!");
            }

        }

        return "registration/forgotPassword";

    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String viewChangePassword(@RequestParam("id") int id, Model model) {

        model.addAttribute("user_id", id);

        return "registration/changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("user_id") int id, @ModelAttribute("newPassword") String newPassword, Model model) {

        //model.addAttribute("user_id", id);
        model.addAttribute("newPassword", newPassword);

        if(newPassword == null || newPassword.trim().isEmpty())
            model.addAttribute("error", "Item Password is required!");
        else{

            User user = userService.getById(id);
            user.setPassword(newPassword);

            userService.saveOrUpdate(user);
            model.addAttribute("success", "Success!");
        }

        return "registration/changePassword";
    }

}
