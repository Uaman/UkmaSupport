package com.ukmaSupport.controllers;

import com.ukmaSupport.models.Auditorium;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.models.EditForm;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import com.ukmaSupport.utils.PasswordChangeValidator;
import com.ukmaSupport.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    @Qualifier("passChangeValidator")
    private PasswordChangeValidator validator;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private OrderService orderService;

    private final static String USER = "USER";
    private final static String ASSISTANT = "ASSISTANT";
    private final static String PROFESSOR = "PROFESSOR";

    private final static String BLOCKED = "blocked";
    private final static String DONE = "done";
    private final static String UNDONE = "Undone";

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<User> users = userService.getByRole(USER);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/assistants", method = RequestMethod.GET)
    public String showAssistants(Model model) {
        List<User> users = userService.getByRole(ASSISTANT);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/professors", method = RequestMethod.GET)
    public String showProfessors(Model model) {
        List<User> users = userService.getByRole(PROFESSOR);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/blocked", method = RequestMethod.GET)
    public String showBlockedUsers(Model model) {
        List<User> users = userService.getByStatus(BLOCKED);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/allOrders", method = RequestMethod.GET)
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/completedOrders", method = RequestMethod.GET)
    public String showCompletedOrders(Model model) {
        List<Order> orders = orderService.getByStatus(DONE);
        model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/uncompletedOrders", method = RequestMethod.GET)
    public String showUncompletedOrders(Model model) {
        List<Order> orders = orderService.getByStatus(UNDONE);
        model.addAttribute("orders", orders);
        return "adminPage/orders";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/auditoriums", method = RequestMethod.GET)
    public String showAuditoriums(Model model) {
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("auditoriums", auditoriums);
        return "adminPage/auditoriums";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/createAuditorium", method = RequestMethod.GET)
    public String createAuditorium(ModelMap model) {
        Auditorium order = new Auditorium();
        List<User> users = userService.getByRole(ASSISTANT);
        model.addAttribute("newAuditorium", order);
        model.addAttribute("assistants", users);
        return "adminPage/addAuditorium";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/createAuditorium", method = RequestMethod.POST)
    public String saveAuditorium(@ModelAttribute("newAuditorium") Auditorium auditorium, ModelMap model) {
        model.addAttribute("number", auditorium.getNumber());
        auditoriumService.save(auditorium);
        return "redirect:/all";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public String downloadExcel(Model model) {
        List<User> listUsers = userService.getAll();
        model.addAttribute("listUsers", listUsers);
        return "excelView";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/editAdminProfile", method = RequestMethod.GET)
    public String editProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getByEmail(name);
        EditForm editForm = new EditForm();
        editForm.setFirstName(user.getFirstName());
        editForm.setLastName(user.getLastName());
        editForm.setEmail(user.getEmail());
        model.addAttribute("passChangeForm", editForm);
        return "adminPage/editAdminProfile";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/editAdminProfile", method = RequestMethod.POST)
    public String profileEdited(@ModelAttribute("passChangeForm") EditForm editForm, BindingResult result) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userService.getByEmail(name);
        validator.validate(editForm, result);
        if (result.hasErrors())
            return "adminPage/editAdminProfile";
        String pass = PasswordEncryptor.encode(editForm.getPassword());
        user.setPassword(pass);
        userService.saveOrUpdate(user);
        return "userPage/passwordChangeSuccess";
    }
}