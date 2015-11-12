package com.ukmaSupport.controllers;

import com.ukmaSupport.models.Order;
import com.ukmaSupport.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AssistController {

    private final static String DONE = "done";
    private final static String UNDONE = "Undone";

    @Autowired
    UserController userController;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/allAssistOrders", method = RequestMethod.GET)
    public String allOrders(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistid = (Integer) session.getAttribute("id");
        // System.out.println(assistid);
        List<Order> orders = orderService.getAllAssistOrders(assistid);
        model.addAttribute("assistOrders", orders);

        return "assistPage/assistHomePage";
    }

    @RequestMapping(value = "/uncomplAssistOrders" , method = RequestMethod.GET)
    public String uncompletedAssistOrders(ModelMap model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistid = (Integer) session.getAttribute("id");
        // System.out.println(assistid);
        List<Order> orders =  orderService.getByAssistAndStatus(assistid, UNDONE);
        model.addAttribute("assistOrder", orders);

        return "assistPage/assistHomePage";
    }

    @RequestMapping(value = "/complAssistOrders" , method = RequestMethod.GET)
    public String completedAssistOrders(ModelMap model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistid = (Integer) session.getAttribute("id");
      //  System.out.println(assistid);
        List<Order> orders =  orderService.getByAssistAndStatus(assistid, DONE);
        model.addAttribute("assistOrder", orders);

        return "assistPage/assistHomePage";
    }

/*
    @RequestMapping(value = "/complUserAssistOrders", method = RequestMethod.GET)
    public String uncompletedOrders(ModelMap model) {
        return userController.uncompletedOrders(model);
    }

    @RequestMapping(value = "/uncomplUserAssistOrders" , method = RequestMethod.GET)
    public String completedOrders(ModelMap model) {
        return userController.completedOrders(model);
    }

    @RequestMapping(value = "/createAssistOrder", method = RequestMethod.GET)
    public String createOrder(ModelMap model) {
        return userController.createOrder(model);
    }

    @RequestMapping(value = "/createAssistOrder", method = RequestMethod.POST)
    public String createOrderPost(Order order,ModelMap model, BindingResult result) {
        return userController.createOrderPost(order, model, result);
    }
/*
    @RequestMapping(value = "/editAssistProfile", method = RequestMethod.POST)
    public String editProfile(ModelMap model) {
        return userController.editProfile(model);
    }
*/
}
