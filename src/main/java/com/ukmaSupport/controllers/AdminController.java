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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/all", method = RequestMethod.POST)
//    @ResponseBody
    public String showAllUsers(Model model) {
        List<User> users = userDao.getAll();
        model.addAttribute("users", users);
        return "adminPage/usersList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
//    @ResponseBody
    public String showUsers(Model model) {
        List<User> users = userDao.getByRole("USER");
        model.addAttribute("users", users);
        return "adminPage/usersList";
    }

    @RequestMapping(value = "/assistants", method = RequestMethod.POST)
 //   @ResponseBody
    public String showAssistants(Model model) {
        List<User> users = userDao.getByRole("ASSISTANT");
        model.addAttribute("users", users);
        return "adminPage/usersList";
    }

    @RequestMapping(value = "/professors", method = RequestMethod.POST)
//    @ResponseBody
    public String showProfessors(Model model) {
        List<User> users = userDao.getByRole("PROFESSOR");
        model.addAttribute("users", users);
        return "adminPage/usersList";
    }

    @RequestMapping(value = "/blocked", method = RequestMethod.POST)
 //   @ResponseBody
    public String showBlockedUsers(Model model) {
        List<User> users = userDao.getByStatus("blocked");
        model.addAttribute("users", users);
        return "adminPage/usersList";
    }

    @RequestMapping(value = "/allOrders", method = RequestMethod.POST)
 //   @ResponseBody
    public String showOrders(Model model) {
        List<Order> orders = orderDao.getAll();
        model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @RequestMapping(value = "/auditoriums", method = RequestMethod.POST)
 //   @ResponseBody
    public String showAuditoriums(Model model) {
        List<Auditorium> auditoriums = auditoriumDao.getAll();
        model.addAttribute("auditoriums", auditoriums);
        return "adminPage/auditoriums";
    }

    @RequestMapping(value = "/completedOrders", method = RequestMethod.POST)
 //   @ResponseBody
    public String showCompletedOrders(Model model) {
        List<Order> orders = orderDao.getByStatus("Done");
        model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @RequestMapping(value = "/uncompletedOrders", method = RequestMethod.POST)
  //  @ResponseBody
    public String showUncompletedOrders(Model model) {
        List<Order> orders = orderDao.getByStatus("Undone");
        model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @RequestMapping(value = "/downloadExcel", method = RequestMethod.POST)
  //  @ResponseBody
    public String downloadExcel(Model model) {
        List<User> listUsers = userDao.getAll();
        model.addAttribute("listUsers", listUsers);
        return "excelView";
    }
}