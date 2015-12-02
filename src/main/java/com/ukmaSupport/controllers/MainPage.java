package com.ukmaSupport.controllers;

import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainPage {
    /**/
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        return "mainPage/landingPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String landingPage(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        if(!auth.getName().equals("anonymousUser")){
            return "";
        }
        return "mainPage/mainPage";
    }

    @RequestMapping(value = "/loginError")
    public String showLoginError() {
        return "mainPage/failedLogin";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @RequestMapping({"/succesfullRegistration"})
    public String authorizationSuccesfull() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("id", userService.getByEmail(auth.getName()).getId());
        if(userService.getByEmail(auth.getName()).getRole().equals("ADMIN"))
            return "redirect:/admin/allUsers";
        if (userService.getByEmail(auth.getName()).getRole().equals("ASSISTANT"))
            return "redirect:/assist/home";
        return "redirect:/user/userhome";
    }
}