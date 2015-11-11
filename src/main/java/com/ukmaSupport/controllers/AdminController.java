package com.ukmaSupport.controllers;

import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userDao;

    @Autowired
    private AuditoriumService auditoriumDao;

    @Autowired
    private WorkplaceService workplaceDao;

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public ModelAndView admin() {
        return new ModelAndView("adminPages/adminPage", "command", new User());
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.POST)
    public String adminPage(@ModelAttribute("users") User users, BindingResult bindingResult, Model model) {
        //model.addAttribute();
        if (bindingResult.hasErrors())
            return "adminPages/adminPage";
        return "result";

    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<User> users = userDao.getAll();
        model.addAttribute("users", users);
        return "";
    }
}
