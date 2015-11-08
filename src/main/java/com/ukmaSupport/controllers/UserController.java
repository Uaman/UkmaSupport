package com.ukmaSupport.controllers;

import com.ukmaSupport.dao.interfaces.UserDao;
import com.ukmaSupport.dao.interfaces.WorkplaceDao;
import com.ukmaSupport.models.User;
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
    private UserDao userDao;
    @Autowired
    private WorkplaceDao workplaceDao;

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
      /*  Workplace workplace=new Workplace();
        workplace.setAuditoriumId(2);
        workplace.setAccessNumber(2344);

        workplaceDao.save(workplace);*/
       System.out.println(userDao.getAll());

     if(bindingResult.hasErrors()) {
         return "userhome";
     }
      return "result";

    }
}
