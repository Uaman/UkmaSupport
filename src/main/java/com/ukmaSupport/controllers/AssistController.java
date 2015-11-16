package com.ukmaSupport.controllers;

import com.ukmaSupport.models.*;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
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


    //@Secured("ROLE_ASSIST")
    @RequestMapping(value = "/assistHome", method = RequestMethod.GET)
    public String listAssistOrders(ModelMap model) {
        return "assistPage/assistHomePage";
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/all_my_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> allAssistOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        return orderService.getAllAssistOrders(assistId);
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/my_compl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistComplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        return orderService.getByAssistAndStatus(assistId, DONE);
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/my_uncompl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUncomplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        return orderService.getByAssistAndStatus(assistId, UNDONE);
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/all_user_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserAllOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        System.out.println(userId);
        return orderService.getByUserId(userId);
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/compl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserComplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        System.out.println(userId);
        return orderService.getByUserIdStatus(userId, DONE);
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/uncompl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserUncomplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        System.out.println(userId);
        return orderService.getByUserIdStatus(userId, UNDONE);
    }

    //@Secured("ROLE_ASSIST")
    @RequestMapping(value = "/editAssistProfile", method = RequestMethod.GET)
    public String editAssistProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User assist = userService.getByEmail(name);
        EditForm editForm = new EditForm();
        editForm.setFirstName(assist.getFirstName());
        editForm.setLastName(assist.getLastName());
        editForm.setEmail(assist.getEmail());
        model.addAttribute("passChangeForm", editForm);
        return "assistPage/editAssistProfile";
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/editAssistProfile", method = RequestMethod.POST)
    public String profileEdited(@ModelAttribute("passChangeForm") EditForm editForm, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User assist = userService.getByEmail(name);
        validatorPassword.validate(editForm, result);
        if (result.hasErrors())
            return "assistPage/editAssistProfile";
        String pass = PasswordEncryptor.encode(editForm.getPassword());
        assist.setPassword(pass);
        userService.saveOrUpdate(assist);
        return "userPage/passwordChangeSuccess";
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/createAssistOrder", method = RequestMethod.GET)
    public String createOrder(ModelMap model) {
        Order order = new Order();
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("newOrder", order);
        model.addAttribute("auditoriums", auditoriums);
        return "assistPage/createOrderAssistPage";
    }

    // @Secured("ROLE_ASSIST")
    @RequestMapping(value = "/createAssistOrder", method = RequestMethod.POST)
    public String createOrderPost(@ModelAttribute("newOrder") Order order, ModelMap model, BindingResult result) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        validatorOrder.validate(order, result);
        if (result.hasErrors()) {
            List<Auditorium> auditoriums = auditoriumService.getAll();
            model.addAttribute("auditoriums", auditoriums);
            return "assistPage/createOrderAssistPage";
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
        return "redirect:/assistHome";
    }

    @RequestMapping(value = "/myComplOrders", method = RequestMethod.GET)
    public String listAssistComplOrders(ModelMap model) {
        return "assistPage/assistComplOrders";
    }

    @RequestMapping(value = "/myUncomplOrders", method = RequestMethod.GET)
    public String listAssistUncomplOrders(ModelMap model) {
        return "assistPage/assistUncomplOrders";
    }

    @RequestMapping(value = "/ComplOrders", method = RequestMethod.GET)
    public String listAssistUserComplOrders(ModelMap model) {
        return "assistPage/userComplOrders";
    }

    @RequestMapping(value = "/UncomplOrders", method = RequestMethod.GET)
    public String listAssistUserUncomplOrders(ModelMap model) {
        return "assistPage/userUncomplOrders";
    }


    @RequestMapping(value = "/assistUserhome", method = RequestMethod.GET)
    public String listAssistUserAllOrders(ModelMap model) {
        return "assistPage/assistUserhome";
    }

}
