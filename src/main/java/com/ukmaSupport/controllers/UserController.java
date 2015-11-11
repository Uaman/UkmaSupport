package com.ukmaSupport.controllers;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.models.User;
import com.ukmaSupport.models.Workplace;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userDao;

    @Autowired
    private AuditoriumService auditoriumDao;

    @Autowired
    private WorkplaceService workplaceDao;

    @Autowired
    private OrderService orderDao;


    @RequestMapping(value = "/userhome", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("userPage/userHomePage", "command", new User());
    }

    @RequestMapping(value = "/userhome", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("users") User users, BindingResult bindingResult, ModelMap model) {

        //System.out.println("error" + bindingResult.hasErrors());
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



    @RequestMapping(value = "/ajaxtest", method = RequestMethod.GET)
    public @ResponseBody List<Workplace> getCharNum(@RequestParam("text") String text) {
        System.out.println(text);
        List<Workplace> workplaces = workplaceDao.getByAuditoryName(text);

        return workplaces;
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
    public String createOrderPost(@ModelAttribute("newOrder") Order order,ModelMap model, BindingResult result) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        order.setUserId((Integer) session.getAttribute("id"));
        order.setStatus("Undone");
        order.setAssistantId(order.getUserId());
        order.setWorkplace_id(workplaceDao.getByNumber(Integer.parseInt(order.getWorkplace_access_num())).getId());
        orderDao.createOrUpdate(order);
        return "redirect:/userhome";
    }
    @RequestMapping(value = "userhome/list" , method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<Order> orders = orderService.getAll();
        model.addAttribute("userOrder", orders);
        model.addAttribute("message", "Gt");
        return "userPage/userOrder";
    }

}
