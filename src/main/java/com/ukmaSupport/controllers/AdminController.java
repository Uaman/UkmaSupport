package com.ukmaSupport.controllers;

import com.ukmaSupport.models.*;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import com.ukmaSupport.utils.AudiroriumValidator;
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
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    @Qualifier("passChangeValidator")
    private PasswordChangeValidator validator;

    @Autowired
    @Qualifier("audiroriumValidator")
    private AudiroriumValidator audiroriumValidator;

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

    @RequestMapping(value = "/admin/allUsers", method = RequestMethod.GET)
    public String allUsers() {
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/admin/users/changeStatus/{id}", method = RequestMethod.GET)
    public String changeUserStatus(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        if (user.getAccountStatus().equals(BLOCKED))
            user.setAccountStatus("active");
        else
            user.setAccountStatus(BLOCKED);
        userService.saveOrUpdate(user);
        return "redirect:/admin/allUsers";
    }

    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUserById(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin/allUsers";
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String showUsers(Model model) {
        List<User> users = userService.getByRole(USER);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/assistants", method = RequestMethod.GET)
    public String showAssistants(Model model) {
        List<User> users = userService.getByRole(ASSISTANT);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/professors", method = RequestMethod.GET)
    public String showProfessors(Model model) {
        List<User> users = userService.getByRole(PROFESSOR);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/blockedUsers", method = RequestMethod.GET)
    public String showBlockedUsers(Model model) {
        List<User> users = userService.getByStatus(BLOCKED);
        model.addAttribute("users", users);
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/allOrders", method = RequestMethod.GET)
    public String allOrders() {
        return "adminPage/orders";
    }

    @RequestMapping(value = "/admin/getAllOrders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @RequestMapping(value = "/admin/auditoriums", method = RequestMethod.GET)
    public String allAuditoriums() {
        return "adminPage/auditoriums";
    }

    @RequestMapping(value = "/admin/getAuditoriums", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Auditorium> getAuditoriums() {
        return auditoriumService.getAll();
    }

    @RequestMapping(value = "/admin/auditoriums/delete/{id}", method = RequestMethod.GET)
    public String deleteOrderById(@PathVariable("id") int id) {
        auditoriumService.delete(id);
        return "redirect:/admin/auditoriums";
    }

    @RequestMapping(value = "/admin/auditoriums/{name}", method = RequestMethod.GET)
    public String showWorkplaces(@PathVariable("name") String name, Model model) {

        model.addAttribute("name", name);
        List<Auditorium> auditoriums = auditoriumService.getAll();

        for (Auditorium auditorium : auditoriums) {
            if (auditorium.getNumber().equals(name)) {
                List<Workplace> workplaces = workplaceService.getByAuditoryName(name);
                model.addAttribute("workplaces", workplaces);
                return "adminPage/workplaces";
            }
        }
        return "/404";
    }

//    @RequestMapping(value = "/admin/createAuditorium", method = RequestMethod.GET)
//    public String createAuditorium(ModelMap model) {
//        Auditorium order = new Auditorium();
//        List<User> users = userService.getByRole(ASSISTANT);
//        model.addAttribute("newAuditorium", order);
//        model.addAttribute("assistants", users);
//        return "adminPage/addAuditorium";
//    }

//    @RequestMapping(value = "/admin/createAuditorium", method = RequestMethod.POST)
//    public String saveAuditorium(@ModelAttribute("newAuditorium") Auditorium auditorium, ModelMap model, BindingResult bindingResult) {
//        model.addAttribute("number", auditorium.getNumber());
//        audiroriumValidator.validate(auditorium, bindingResult);
//        if (bindingResult.hasErrors())
//            return "adminPage/addAuditorium";
//
//        auditoriumService.save(auditorium);
//        return "redirect:/auditoriums";
//    }

    @RequestMapping(value = "/admin/downloadExcel", method = RequestMethod.GET)
    public String downloadExcel(Model model) {
        List<User> listUsers = userService.getAll();
        model.addAttribute("listUsers", listUsers);
        return "excelView";
    }

    @RequestMapping(headers = "Content-Type=application/json", value = "/admin/changeRole", method = RequestMethod.POST)
    public
    @ResponseBody
    void getRole(@RequestParam("role") String role) {
        System.out.print("QQWEWQ");
        System.out.println("ROLE: " + role);
    }

    @RequestMapping(value = "/admin/editProfile", method = RequestMethod.GET)
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

    @RequestMapping(value = "/admin/editProfile", method = RequestMethod.POST)
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