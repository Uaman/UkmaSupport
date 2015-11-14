package com.ukmaSupport.controllers;

import com.ukmaSupport.models.EditForm;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import com.ukmaSupport.utils.OrderValidator;
import com.ukmaSupport.utils.PasswordChangeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class AssistController {

    private final static String DONE = "done";
    private final static String UNDONE = "Undone";

    @Autowired
    private UserController userController;

    @Autowired
    private OrderService orderService;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("passChangeValidator")
    private PasswordChangeValidator passwordValidator;

    @Autowired
    @Qualifier("orderValidator")
    private OrderValidator validator;

    //@Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/assistHome", method = RequestMethod.GET)
    public String listUserOrders(ModelMap model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int assistId = (Integer) session.getAttribute("id");

        System.out.println(assistId);
        List<Order> orders = orderService.getAllAssistOrders(assistId);
        model.addAttribute("assistOrder", orders);
        return "assistPage/assistHomePage";
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/all_my_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> allAssistOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        List<Order> orders = orderService.getAllAssistOrders(assistId);
        return orders;
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/my_compl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistComplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        List<Order> orders = orderService.getByAssistAndStatus(assistId, DONE);
        return orders;
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/my_uncompl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUncomplOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();

        int assistId = (Integer) session.getAttribute("id");
        System.out.println(assistId);
        List<Order> orders = orderService.getByAssistAndStatus(assistId, UNDONE);
        return orders;
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/all_user_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserAllOrders() {
        return userController.allUserOrders();
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/compl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserComplOrders() {
        return userController.getUserComplOrders();

    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/uncompl_orders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAssistUserUncomplOrders() {
        return userController.getUserUncomplOrders();
    }

    //@Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/editAssistProfile", method = RequestMethod.GET)
    public String editProfile(Model model) {
        return userController.editProfile(model);
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/editAssistProfile", method = RequestMethod.POST)
    public String profileEdited(@ModelAttribute("passChangeForm") EditForm editForm, BindingResult result) {
        return userController.profileEdited(editForm, result);
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/createAssistOrder", method = RequestMethod.GET)
    public String createOrder(ModelMap model) {
        return userController.createOrder(model);
    }

    // @Secured("ROLE_ASSISTANT")
    @RequestMapping(value = "/createAssistOrder", method = RequestMethod.POST)
    public String createOrderPost(@ModelAttribute("newOrder") Order order, @RequestParam("workplace_access_num") int workplaceNum, ModelMap model, BindingResult result) {
        return "";//userController.createOrderPost(order, workplaceNum, model, result);
    }


}
