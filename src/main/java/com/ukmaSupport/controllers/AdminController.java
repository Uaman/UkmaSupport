package com.ukmaSupport.controllers;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
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

    @Autowired
    private OrderService orderDao;

    @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
    public ModelAndView admin() {
        return new ModelAndView("adminPages/adminPage", "command", new User());
    }

    @RequestMapping(value = "/adminPage", method = RequestMethod.POST)
    public String adminPage(@ModelAttribute("users") User users, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "adminPages/adminPage";
        return "result";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<User> users = userDao.getAll();
        //model.addAttribute("users", users);
        return "adminPage/adminPageListUsers";
    }

    @RequestMapping(value = "/orders/all", method = RequestMethod.GET)
    public String showOrders(Model model) {
        List<Order> orders = orderDao.getAll();
        //model.addAttribute("orders", orders);
        return "adminPage/allOrders";
    }

    @RequestMapping(value = "/auditoriums", method = RequestMethod.GET)
    public String showAuditoriums(Model model) {
        List<Auditorium> auditoriums = auditoriumDao.getAll();
        //model.addAttribute("auditoriums", auditoriums);
        return "adminPage/auditoriums";
    }

    @RequestMapping(value = "/orders/completed", method = RequestMethod.GET)
    public String showCompletedOrders(Model model) {
        List<Order> orders = orderDao.getByStatus("done");
        //model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @RequestMapping(value = "/orders/uncompleted", method = RequestMethod.GET)
    public String showUncompletedOrders(Model model) {
        List<Order> orders = orderDao.getByStatus("undone");
        //model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public String downloadExcel(Model model) {
        List<User> listUsers = userDao.getAll();
        model.addAttribute("listUsers", listUsers);
        return "excelView";
    }
}
