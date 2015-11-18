package com.ukmaSupport.controllers;

import com.ukmaSupport.models.*;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import com.ukmaSupport.utils.EditOrderValidator;
import com.ukmaSupport.utils.OrderValidator;
import com.ukmaSupport.utils.PasswordChangeValidator;
import com.ukmaSupport.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.ukmaSupport.dao.interfaces.OrderDao;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class AssistController {

    private final static String DONE = "done";
    private final static String UNDONE = "Undone";

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    @Qualifier("passChangeValidator")
    private PasswordChangeValidator validatorPassword;

    @Autowired
    @Qualifier("orderValidator")
    private OrderValidator validatorOrder;

    @Autowired
    @Qualifier("editOrderValidator")
    private EditOrderValidator editOrderValidator;

    @RequestMapping(value = "/assist/home", method = RequestMethod.GET)
    public String allAssistOrders(Model model) {
        return "assistPage/assistOrdersAssigned";
    }

    @RequestMapping(value = "/assist/get_all_assigned_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAllAssistOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        return orderService.getAllAssistOrders(assistId);
    }

    @RequestMapping(value = "/assist/created_orders", method = RequestMethod.GET)
    public String allAssistUserOrders(Model model) {
        return "assistPage/assistOrdersCreated";
    }

    @RequestMapping(value = "/assist/get_all_created_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAllAssistUserOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        System.out.println(userId);
        return orderService.getByUserId(userId);
    }

    @RequestMapping(value = "/assist/get_assigned_compl", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistComplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        return orderService.getByAssistAndStatus(assistId, DONE);
    }

    @RequestMapping(value = "/assist/get_assigned_uncompl", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUncomplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        return orderService.getByAssistAndStatus(assistId, UNDONE);
    }

    @RequestMapping(value = "/assist/get_created_compl", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserComplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        System.out.println(userId);
        return orderService.getByUserIdStatus(userId, DONE);
    }

    @RequestMapping(value = "assist/get_created_uncompl", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserUncomplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        System.out.println(userId);
        return orderService.getByUserIdStatus(userId, UNDONE);
    }

    @RequestMapping(value = "/assist/edit_profile", method = RequestMethod.GET)
    public String editAssistProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User assist = userService.getByEmail(name);
        EditForm editForm = new EditForm();
        editForm.setFirstName(assist.getFirstName());
        editForm.setLastName(assist.getLastName());
        editForm.setEmail(assist.getEmail());
        model.addAttribute("passChangeForm", editForm);
        return "assistPage/assistEditProfile";
    }

    @RequestMapping(value = "/assist/edit_profile", method = RequestMethod.POST)
    public String profileEdited(@ModelAttribute("passChangeForm") EditForm editForm, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User assist = userService.getByEmail(name);
        validatorPassword.validate(editForm, result);
        if (result.hasErrors())
            return "assistPage/assistEditProfile";
        String pass = PasswordEncryptor.encode(editForm.getPassword());
        assist.setPassword(pass);
        userService.saveOrUpdate(assist);
        return "assistPage/passwordChangeSuccess";
    }

    @RequestMapping(value = "/assist/create_order", method = RequestMethod.GET)
    public String createOrder(ModelMap model) {
        Order order = new Order();
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("newOrder", order);
        model.addAttribute("auditoriums", auditoriums);
        return "assistPage/assistCreateOrder";
    }

    @RequestMapping(value = "/ajaxAuditoriums", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Workplace> getCharNum(@RequestParam("text") String text) {
        List<Workplace> workplaces = workplaceService.getByAuditoryName(text);
        return workplaces;
    }

    @RequestMapping(value = "assist/create_order", method = RequestMethod.POST)
    public String createOrderPost(@ModelAttribute("newOrder") Order order, ModelMap model, BindingResult result) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        validatorOrder.validate(order, result);
        if (result.hasErrors()) {
            List<Auditorium> auditoriums = auditoriumService.getAll();
            model.addAttribute("auditoriums", auditoriums);
            return "assistPage/assistCreateOrder";
        }
        order.setUserId((Integer) session.getAttribute("id"));
        order.setStatus(UNDONE);
        order.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        User assistant = userService.getResponsibleAssistant(order.getAuditorium());
        int assistantId = 0;
        if (assistant != null) assistantId = assistant.getId();
        order.setAssistantId(assistantId);
        order.setWorkplace_id(workplaceService.getByNumber(Integer.parseInt(order.getWorkplace_access_num())).getId());
        orderService.createOrUpdate(order);
        return "redirect:/assist/created_orders";
    }

    /**
     * ***************************************************************************
     */
    @RequestMapping(value = "/assist/mark_done/{id}", method = RequestMethod.GET)
    public String setToDone(@RequestParam("orderid") Integer id, Model model) {
        Order order = orderService.getById(id);
        order.setStatus(DONE);
        orderService.createOrUpdate(order);
        return "redirect:/assist/home";
    }

    @RequestMapping(value = "/assist/delete_order/{id}", method = RequestMethod.GET)
    public String deleteOrderById(Model model, @PathVariable("id") int id) {
        orderService.delete(id);
        return "redirect:/assist/home";
    }

    @RequestMapping(value = "/assist/edit_order/{id}", method = RequestMethod.GET)
    public String editOrder(@PathVariable("id") Integer id, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        Order order = orderService.getById(id);
        List<Order> orderList = orderService.getByUserId(userId);
        for (Order order1 : orderList) {
            if (order1.getId() != id) {
                return "redirect:/assist/created_orders";
            }
        }
        model.addAttribute("title", order.getTitle());
        model.addAttribute("workplace", order.getWorkplace());
        model.addAttribute("auditorium", order.getAuditorium());
        model.addAttribute("content", order.getContent());
        model.addAttribute("id", order.getId());
        model.addAttribute("editOrder", order);

        // System.out.println("aud"+  model.addAttribute("auditorium",order.getAuditorium()));
        return "assistPage/assistEditOrder";
    }

    @RequestMapping(value = "/assist/edit_order/save", method = RequestMethod.POST)
    public String orderEdited(@ModelAttribute("id") Integer id, @ModelAttribute("editOrder") Order order, ModelMap model, BindingResult result) {
        editOrderValidator.validate(order, result);

        if (result.hasErrors()) {
            model.addAttribute("title", order.getTitle());
            model.addAttribute("workplace", order.getWorkplace());
            model.addAttribute("auditorium", order.getAuditorium());
            model.addAttribute("content", order.getContent());
            model.addAttribute("id", order.getId());
            model.addAttribute("editOrder", order);
            return "assistPage/assistEditOrder";
        }
        order.setId(id);
        order.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        orderService.update(order);

        return "redirect:/assist/created_orders";
    }

}
