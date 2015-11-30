package com.ukmaSupport.controllers;

import com.ukmaSupport.mailService.templates.NewOrderMail;
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
public class UserController {

    private final static String DONE = "done";
    private final static String UNDONE = "Undone";

    @Autowired
    private OrderService orderService;

    @Autowired
    private NewOrderMail newOrderMail;

    @Autowired
    private AuditoriumService auditoriumService;

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

    @RequestMapping(value = "/user/ajaxtest", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Workplace> getCharNum(@RequestParam("text") String text) {
        List<Workplace> workplaces = workplaceService.getByAuditoryName(text);
        return workplaces;
    }

    @RequestMapping(value = "/user/userhome", method = RequestMethod.GET)
    public String listUserOrders(ModelMap model) {
        HttpSession session = getCurrentSession();
        session.setAttribute("orderEdit",null);
        User currentUser = userService.getById((Integer) session.getAttribute("id"));
        model.addAttribute("currentUser", currentUser);
        return "userPage/userHomePage";
    }

    @RequestMapping(value = "/user/createOrder", method = RequestMethod.GET)
    public String createOrder(ModelMap model) {
        Order order = new Order();
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("newOrder", order);
        model.addAttribute("auditoriums", auditoriums);
        return "userPage/createOrderPage";
    }

    @RequestMapping(value = "/user/createOrder", method = RequestMethod.POST)
    public String createOrderPost(@ModelAttribute("newOrder") Order order, ModelMap model, BindingResult result) {
        int userId = (Integer) getCurrentSession().getAttribute("id");
        validator.validate(order, result);
        if (result.hasErrors()) {
            List<Auditorium> auditoriums = auditoriumService.getAll();
            model.addAttribute("auditoriums", auditoriums);
            model.addAttribute("newOrder", order);
            return "userPage/createOrderPage";
        }

        order.setUserId(userId);
        order.setStatus(UNDONE);
        order.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        User assistant = userService.getResponsibleAssistant(order.getAuditorium());
        int assistantId = 0;
        if (assistant != null) assistantId = assistant.getId();
        order.setAssistantId(assistantId);
        order.setWorkplace_id(workplaceService.getByNumber(Integer.parseInt(order.getWorkplace_access_num())).getId());

        orderService.createOrUpdate(order);

        if (assistant != null)
            newOrderMail.send(assistant.getEmail());

        return "redirect:/user/userhome";
    }

    @RequestMapping(value = "/user/allUserOrders", method = RequestMethod.GET)
    public @ResponseBody List<Order> allUserOrders() {
        int userId = (Integer) getCurrentSession().getAttribute("id");
        System.out.println(userId);
        List<Order> orders = orderService.getByUserId(userId);
        return orders;
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String deleteOrderById(Model model, @PathVariable("id") int id) {
        orderService.delete(id);
        return "redirect:/user/userhome";
    }

    private HttpSession getCurrentSession(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession();
    }

    @RequestMapping(value = "/user/editOrder/{id}", method = RequestMethod.GET)
    public String editOrder(@PathVariable("id") Integer id, Model model) {

        int userId = (Integer) getCurrentSession().getAttribute("id");


        Order order = orderService.getByUserIdAndId(userId, id);
        if (order == null) {
            return "redirect:/user/userhome";
        }
        model.addAttribute("title", order.getTitle());
        model.addAttribute("workplace", order.getWorkplace());
        model.addAttribute("auditorium",order.getAuditorium());
        model.addAttribute("content", order.getContent());
        model.addAttribute("id", order.getId());
        model.addAttribute("editOrder", order);

        return "userPage/editOrder";
    }

    @RequestMapping(value = "/user/editOrder/save", method = RequestMethod.POST)
    public String orderEdited(@ModelAttribute("id") Integer id, @ModelAttribute("editOrder") Order order, ModelMap model, BindingResult result) {
        validator.validate(order, result);

        if (result.hasErrors()) {
            model.addAttribute("title", order.getTitle());
            model.addAttribute("workplace", order.getWorkplace());
            model.addAttribute("auditorium", order.getAuditorium());
            model.addAttribute("content", order.getContent());
            model.addAttribute("id", order.getId());
            model.addAttribute("editOrder", order);
            return "userPage/editOrder";
        }
        order.setId(id);
        order.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        orderService.update(order);
        Integer orderId = (Integer) getCurrentSession().getAttribute("orderEdit");
        if(orderId != null){
            return "redirect:/addComment/"+orderId;
        }

        return "redirect:/user/userhome";
    }

    @RequestMapping(value = "/user/editProfile", method = RequestMethod.GET)
    public String editProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getByEmail(name);
        EditForm editForm = new EditForm();
        editForm.setFirstName(user.getFirstName());
        editForm.setLastName(user.getLastName());
        editForm.setEmail(user.getEmail());
        model.addAttribute("passChangeForm", editForm);
        return "userPage/editProfile";
    }


    @RequestMapping(value = "/user/editProfile", method = RequestMethod.POST)
    public String profileEdited(@ModelAttribute("passChangeForm") EditForm editForm, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getByEmail(name);
        passwordValidator.validate(editForm, result);
        if (result.hasErrors())
            return "userPage/editProfile";
        String pass = PasswordEncryptor.encode(editForm.getPassword());
        user.setPassword(pass);
        userService.saveOrUpdate(user);
        return "userPage/passwordChangeSuccess";
    }
}
