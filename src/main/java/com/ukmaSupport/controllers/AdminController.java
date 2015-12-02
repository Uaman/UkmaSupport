package com.ukmaSupport.controllers;

import com.ukmaSupport.mailService.templates.NewOrderMail;
import com.ukmaSupport.models.*;
import com.ukmaSupport.services.interfaces.AuditoriumService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import com.ukmaSupport.services.interfaces.WorkplaceService;
import com.ukmaSupport.utils.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    @Qualifier("passChangeValidator")
    private PasswordChangeValidator validator;

    @Autowired
    @Qualifier("orderValidator")
    private OrderValidator orderValidator;

    @Autowired
    private NewOrderMail newOrderMail;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private WorkplaceService workplaceService;

    @Autowired
    private OrderService orderService;

    private final static String DONE = "done";
    private final static String UNDONE = "not done";

    private final static String USER = "USER";
    private final static String ASSISTANT = "ASSISTANT";
    private final static String PROFESSOR = "PROFESSOR";
    private final static String ADMIN = "ADMIN";

    private final static String BLOCKED = "blocked";

    @RequestMapping(value = "/admin/allUsers", method = RequestMethod.GET)
    public String allUsers(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int adminId = (Integer) session.getAttribute("id");
        model.addAttribute("adminId", adminId);
        model.addAttribute("link", "AllUsers");
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/getAllUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String users(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int adminId = (Integer) session.getAttribute("id");
        model.addAttribute("adminId", adminId);
        model.addAttribute("link", "Users");
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/getUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getUsers() {
        return userService.getByRole(USER);
    }

    @RequestMapping(value = "/admin/assistants", method = RequestMethod.GET)
    public String assistants(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int adminId = (Integer) session.getAttribute("id");
        model.addAttribute("adminId", adminId);
        model.addAttribute("link", "Assistants");
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/getAssistants", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getAssistants() {
        return userService.getByRole(ASSISTANT);
    }

    @RequestMapping(value = "/admin/professors", method = RequestMethod.GET)
    public String professors(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int adminId = (Integer) session.getAttribute("id");
        model.addAttribute("adminId", adminId);
        model.addAttribute("link", "Professors");
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/getProfessors", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getProfessors() {
        return userService.getByRole(PROFESSOR);
    }

    @RequestMapping(value = "/admin/blockedUsers", method = RequestMethod.GET)
    public String blockedUsers(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int adminId = (Integer) session.getAttribute("id");
        model.addAttribute("adminId", adminId);
        model.addAttribute("link", "BlockedUsers");
        return "adminPage/users";
    }

    @RequestMapping(value = "/admin/getBlockedUsers", method = RequestMethod.GET)
    public
    @ResponseBody
    List<User> getBlockedUsers() {
        return userService.getByStatus(BLOCKED);
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

    @RequestMapping(value = "/admin/myOrders", method = RequestMethod.GET)
    public String myOrders(Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        User currentUser = userService.getById((Integer) session.getAttribute("id"));
        model.addAttribute("currentUser", currentUser);
        return "adminPage/myOrders";
    }

    @RequestMapping(value = "/admin/getMyOrders", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getMyOrders() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        return orderService.getByUserId(userId);
    }

    @RequestMapping(value = "/admin/showWorkplaces", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Workplace> getWorkplaces(@RequestParam("text") String text) {
        return workplaceService.getByAuditoryName(text);
    }

    @RequestMapping(value = "/admin/orders/createOrder", method = RequestMethod.GET)
    public String createOrder(ModelMap model) {
        Order order = new Order();
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("newOrder", order);
        model.addAttribute("auditoriums", auditoriums);
        return "adminPage/createOrder";
    }

    @RequestMapping(value = "/admin/orders/createOrder", method = RequestMethod.POST)
    public String createOrderPost(@ModelAttribute("newOrder") Order order, ModelMap model, BindingResult result) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        orderValidator.validate(order, result);
        if (result.hasErrors()) {
            List<Auditorium> auditoriums = auditoriumService.getAll();
            model.addAttribute("auditoriums", auditoriums);
            model.addAttribute("newOrder", order);
            return "adminPage/createOrder";
        }

        order.setUserId(userId);
        order.setStatus("not done");

        Timestamp timestamp = new Timestamp(new Date().getTime());
        order.setCreatedAt(timestamp);

        User assistant = userService.getResponsibleAssistant(order.getAuditorium());
        int assistantId = 0;
        if (assistant != null) assistantId = assistant.getId();
        order.setAssistantId(assistantId);
        order.setWorkplace_id(workplaceService.getByNumber(Integer.parseInt(order.getWorkplace_access_num())).getId());
        orderService.createOrUpdate(order);

        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(timestamp);
        Order newOrder = orderService.getByTime(date);

        if (assistant != null)
            newOrderMail.send(assistant.getEmail(), newOrder.getId());
        return "redirect:/admin/myOrders";
    }

    @RequestMapping(value = "/admin/orders/delete/{id}", method = RequestMethod.GET)
    public String deleteOrderById(@PathVariable("id") int id) {
        orderService.delete(id);
        return "redirect:/admin/myOrders";
    }

    @RequestMapping(value = "/admin/orders/edit/{id}", method = RequestMethod.GET)
    public String editOrder(@PathVariable("id") Integer id, Model model) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int userId = (Integer) session.getAttribute("id");
        Order order = orderService.getByUserIdAndId(userId, id);
        if (order == null)
            return "redirect:/admin/myOrders";
        model.addAttribute("editOrder", order);
        return "adminPage/editOrder";
    }

    @RequestMapping(value = "/admin/orders/edit/save", method = RequestMethod.POST)
    public String orderEdited(@ModelAttribute("id") Integer id, @ModelAttribute("editOrder") Order order, ModelMap model, BindingResult result) {
        orderValidator.validate(order, result);

        if (result.hasErrors()) {
            model.addAttribute("editOrder", order);
            return "adminPage/editOrder";
        }
        order.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        orderService.update(order);
        return "redirect:/admin/myOrders";
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
    public String deleteAuditoriumById(@PathVariable("id") int id) {
        auditoriumService.delete(id);
        return "redirect:/admin/auditoriums";
    }

    @RequestMapping(value = "/admin/auditoriums/{name}", method = RequestMethod.GET)
    public String showWorkplaces(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return "adminPage/workplaces";
    }

    @RequestMapping(value = "/admin/auditoriums/{name}/getWorkplaces", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Workplace> getWorkplaces(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return workplaceService.getByAuditoryName(name);
    }

    @RequestMapping(value = "/admin/auditoriums/{name}/workplaces/delete/{id}", method = RequestMethod.GET)
    public String deleteWorkplaceById(@PathVariable("name") String name, @PathVariable("id") int id) {
        workplaceService.delete(id);
        return "redirect:/admin/auditoriums/" + name;
    }

    @RequestMapping(value = "/admin/assistantReport/{date_from}/{date_to}/{id}", method = RequestMethod.GET)
    public String assistantReport(@PathVariable("date_from") String date_from,@PathVariable("date_to") String date_to,@PathVariable("id") Integer id, Model model) {
        int countDone = orderService.getCountOrderByAssistantDate(date_from, date_to, id, DONE);
        int contUndone = orderService.getCountOrderByAssistantDate(date_from, date_to, id, UNDONE);

        List<Order> orderList = orderService.getAllByAssisstIdDate(date_from, date_to, id);
        model.addAttribute("orderList", orderList);
        model.addAttribute("countDone", countDone);
        model.addAttribute("countUndone", contUndone);
        return "assistantReport";
    }

    @RequestMapping(value = "/admin/auditoriumReport/{date_from}/{date_to}/{number}", method = RequestMethod.GET)
    public String auditoriumReport(@PathVariable("date_from") String date_from,@PathVariable("date_to") String date_to,@PathVariable("number") String number, Model model) {
        List<Order> orderList = orderService.getAllByAuditoriumAndDate(date_from,date_to,number);
        model.addAttribute("orderList", orderList);
        return "auditoriumReport";
    }

    @RequestMapping(value = "/admin/allReport/{date_from}/{date_to}", method = RequestMethod.GET)
    public String allReport(@PathVariable("date_from") String date_from,@PathVariable("date_to") String date_to,Model model) {
        int countDone = orderService.getCountOrderByDate(date_from, date_to, DONE);
        int contUndone = orderService.getCountOrderByDate(date_from, date_to, UNDONE);

        List<Order> orderList = orderService.getAllByDate(date_from, date_to);
        model.addAttribute("orderList", orderList);
        model.addAttribute("countDone", countDone);
        model.addAttribute("countUndone", contUndone);
        return "allReport";
    }

    @RequestMapping(value = "/admin/createAuditoriums", method = RequestMethod.POST)
    public String saveAuditorium(@RequestBody Map<String, Object> searchParam, Auditorium auditorium) {
        String number = (String) searchParam.get("auditorium");
        auditorium.setNumber(number);
        if(number.matches("^[\\d]{1}[\\u002D]{1}[\\d]{3}$"))
            auditoriumService.save(auditorium);
        return "redirect:/admin/auditoriums";
    }

    @RequestMapping(value = "/admin/createWorkplaces", method = RequestMethod.POST)
    public String saveWorkplaces(@RequestBody Map<String, Object> searchParam, Workplace workplace) {
        Integer access_number = (Integer) searchParam.get("workplaces");
        String number = (String) searchParam.get("number");
        Auditorium auditorium = auditoriumService.getByNumber(number);
        workplace.setAccessNumber(access_number);
        workplace.setAuditoriumId(auditorium.getId());
        workplaceService.save(workplace);
        return "redirect:/admin/auditoriums/" + number;
    }

    @RequestMapping(value = "/admin/setAssistToAuditorium", method = RequestMethod.POST)
    public String setAssistToAuditorium(@RequestBody Map<String, Object> searchParam) {
        String assistID = (String) searchParam.get("assistID");
        String auditoriumID = (String) searchParam.get("auditoriumID");
        Auditorium auditorium = auditoriumService.getByNumber(auditoriumID);
        auditorium.setUserId(Integer.parseInt(assistID));
        auditoriumService.update(auditorium);
        return "redirect:/admin/auditoriums";
    }

    @RequestMapping(value = "/admin/setAssistToOrder", method = RequestMethod.POST)
    public String setAssistToOrder(@RequestBody Map<String, Object> searchParam) {
        String assistID = (String) searchParam.get("assistID");
        String orderID = (String) searchParam.get("orderID");
        Order order = orderService.getById(Integer.parseInt(orderID));
        order.setAssistantId(Integer.parseInt(assistID));
        orderService.update(order);
        return "redirect:/admin/allOrders";
    }

    @RequestMapping(value = "/admin/changeRole", method = RequestMethod.POST)
    @ResponseBody
    public String setUserRole(@RequestBody Map<String, Object> searchParam) {
        String role = (String) searchParam.get("role");
        String id = (String) searchParam.get("userId");
        User user = userService.getById(Integer.parseInt(id));
        String userRole = null;
        if (role.equals("Assistant")) {
            userRole = ASSISTANT;

        } else if (role.equals("User")) {
            userRole = USER;

        } else if (role.equals("Admin")) {
            userRole = ADMIN;

        } else if (role.equals("Professor")) {
            userRole = PROFESSOR;
        }
        user.setRole(userRole);
        userService.saveOrUpdate(user);

        return "redirect:/admin/getAllOrders";
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
        return "redirect:/admin/allUsers";
    }

    @RequestMapping(value = "/admin/users/userProfile/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("passChangeForm", user);
        return "adminPage/userPage";
    }

    @RequestMapping(value = "/admin/users/getUserProfile/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getUserOrders(@PathVariable("id") int id) {
        return orderService.getByUserId(id);
    }

    @RequestMapping(value = "/admin/report_auditorium", method = RequestMethod.GET)
    public String reportByAuditorium(Model model) {
        List<Auditorium> auditoriums = auditoriumService.getAll();
        model.addAttribute("auditoriums", auditoriums);
        model.addAttribute("link", "report_auditorium");
        return "adminPage/reportAudit";
    }

    @RequestMapping(value = "/admin/report_assist/{date_from}/{date_to}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getReportByAssist(@PathVariable("date_from") String date_from,@PathVariable("date_to") String date_to,@PathVariable("id") int id) {
        return orderService.getAllByAssisstIdDate(date_from,date_to,id);

    }
    @RequestMapping(value = "/admin/report_assist", method = RequestMethod.GET)
    public String reportByAssist(Model model) {
        List<User> assistants = userService.getByRole(ASSISTANT);
        model.addAttribute("assistants", assistants);
        model.addAttribute("link", "report_assist");
        return "adminPage/reportAssist";
    }
    @RequestMapping(value = "/admin/get_report_audit/{date_from}/{date_to}/{number}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getReportByAuditor(@PathVariable("date_from") String date_from,@PathVariable("date_to") String date_to,@PathVariable("number") String number) {
        return orderService.getAllByAuditoriumAndDate(date_from,date_to,number);
    }
    @RequestMapping(value = "/admin/report_all", method = RequestMethod.GET)
    public String reportAll(Model model) {
        model.addAttribute("link", "report_all");
        return "adminPage/reportAll";
    }

    @RequestMapping(value = "/admin/get_report_assist/{date_from}/{date_to}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Order> getReportByAuditorium(@PathVariable("date_from") String date_from,@PathVariable("date_to") String date_to) {
        return orderService.getAllByDate(date_from,date_to);
    }


}