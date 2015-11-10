package com.ukmaSupport.controllers;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userDao;

    @Autowired
    private AuditoriumService auditoriumDao;

    @Autowired
    private WorkplaceService workplaceDao;

    @RequestMapping(value = "/userhome", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("userPage/userHomePage", "command", new User());
    }

    @RequestMapping(value = "/userhome", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("users") User users, BindingResult bindingResult, ModelMap model) {

        System.out.println("error" + bindingResult.hasErrors());
        //  model.addAttribute("title", users.getTitle());
        //  model.addAttribute("order", users.getOrder());
        //  model.addAttribute("id", users.getId());
        //Test dao auditorium
      /*  Workplace workplace=new Workplace();
        workplace.setAuditoriumId(2);
        workplace.setAccessNumber(2344);
        workplaceDao.save(workplace);*/
        System.out.println(userDao.getAll());

        if (bindingResult.hasErrors())
            return "userPage/userHomePage";
        return "result";

    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    public String createOrder(ModelMap model) {
        Order order = new Order();
        List<Auditorium> auditoriums = auditoriumDao.getAll();
        model.addAttribute("newOrder", order);
        model.addAttribute("auditoriums", auditoriums);
        return "userPage/createOrderPage";
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String createOrderPost(@ModelAttribute("newOrder") Order order, Model model, BindingResult result) {
        List<Auditorium> auditoriums = auditoriumDao.getAll();
        model.addAttribute("newOrder", order);
        model.addAttribute("auditoriums",  auditoriums);
        return "null";
    }


    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView downloadExcel() {
        // create some sample data
        List<User> listUsers = userDao.getAll();
        // return a view which will be resolved by an excel view resolver
        return new ModelAndView("excelView", "listUsers", listUsers);
    }
}
