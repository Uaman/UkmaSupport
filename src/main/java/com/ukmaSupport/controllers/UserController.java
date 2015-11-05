package com.ukmaSupport.controllers;

import com.ukmaSupport.POJO.Auditorium;
import com.ukmaSupport.POJO.User;
import com.ukmaSupport.postgreDao.AuditoriumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private AuditoriumDao auditoriumDao;

    @RequestMapping(value = "/userhome", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("userhome", "command", new User());
    }

    @RequestMapping(value = "/userhome", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("users")User users,BindingResult bindingResult, ModelMap model) {
        System.out.println("error"+bindingResult.hasErrors());
      //  model.addAttribute("title", users.getTitle());
      //  model.addAttribute("order", users.getOrder());
      //  model.addAttribute("id", users.getId());
        //Test dao auditorium
        Auditorium auditorium=new Auditorium();
        auditorium.setUserId(1);
        auditorium.setNumber("2-304");
        auditoriumDao.save(auditorium);
     if(bindingResult.hasErrors()) {
         return "userhome";
     }
      return "result";

    }
}
