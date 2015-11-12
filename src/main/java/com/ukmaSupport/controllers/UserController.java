package com.ukmaSupport.controllers;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.models.Workplace;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuditoriumService auditoriumDao;

    @Autowired
    private WorkplaceService workplaceDao;

    @RequestMapping(value = "/ajaxtest", method = RequestMethod.POST)
    public @ResponseBody List<Workplace> getCharNum(@RequestParam("text") String text) {
        System.out.println(text);
        List<Workplace> workplaces = workplaceDao.getByAuditoryName(text);

        return workplaces;
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.GET)
    @ResponseBody
    public String createOrder(ModelMap model) {
        Order order = new Order();
        List<Auditorium> auditoriums = auditoriumDao.getAll();
        model.addAttribute("newOrder", order);
        model.addAttribute("auditoriums", auditoriums);
        return "userPage/createOrderPage";
    }

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ResponseBody
    public String createOrderPost(@ModelAttribute("newOrder") Order order,ModelMap model, BindingResult result) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        order.setUserId((Integer) session.getAttribute("id"));
        order.setStatus("Undone");
        order.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        order.setAssistantId(order.getUserId());
        order.setWorkplace_id(workplaceDao.getByNumber(Integer.parseInt(order.getWorkplace_access_num())).getId());
        orderService.createOrUpdate(order);
        return "redirect:/userhome";
    }
    @RequestMapping(value = "/userhome" , method = RequestMethod.POST)
    @ResponseBody
    public String listUsersOrders(ModelMap model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        int userid = (Integer) session.getAttribute("id");

        System.out.println(userid);
        List<Order> orders =  orderService.getByUserId(userid);
        model.addAttribute("userOrder", orders);
        model.addAttribute("message", "Gt");

        return "userPage/userHomePage";
    }

    @RequestMapping(value = "/usersCompletedOrders" , method = RequestMethod.POST)
    @ResponseBody
    public String listUsersCompletedOrders(ModelMap model) {

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        int userid = (Integer) session.getAttribute("id");

        System.out.println(userid);
        List<Order> orders =  orderService.getByUserAndStatus(userid, "Done");
        model.addAttribute("completedOrders", orders);
      //  model.addAttribute("message", "Gt");

        return "userPage/userHomePage";
    }

    @RequestMapping(value = "/usersUncompletedOrders" , method = RequestMethod.POST)
    @ResponseBody
    public String listUsersUncompletedOrders(ModelMap model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        int userid = (Integer) session.getAttribute("id");

        System.out.println(userid);
        List<Order> orders = orderService.getByUserAndStatus(userid, "Undone");
        model.addAttribute("uncompletedOrders", orders);
    //    model.addAttribute("message", "Gt");

        return "userPage/userHomePage";
    }


    @RequestMapping(value = "/editProfile" , method = RequestMethod.POST)
    @ResponseBody
    public String editProfile(ModelMap model) {
        return "userPage/editProfile";
    }


}
