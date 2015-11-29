package com.ukmaSupport.controllers;


import com.ukmaSupport.mailService.templates.CommentForAssistMail;
import com.ukmaSupport.mailService.templates.CommentForUserMail;
import com.ukmaSupport.models.Comment;
import com.ukmaSupport.models.Order;
import com.ukmaSupport.models.User;
import com.ukmaSupport.models.enums.UserRoles;
import com.ukmaSupport.services.interfaces.CommentService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/addComment")
public class CommentariesController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommentForAssistMail commentForAssistMail;

    @Autowired
    private CommentForUserMail commentForUserMail;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String addComment(@PathVariable("id")int ordereId, Model model){

        List<Comment> commentaries = commentService.getAllComments(ordereId);

        Comment comment = new Comment();

        Order currentOrder = orderService.getById(ordereId);
        User user = userService.getById(getCurrentUser());

        model.addAttribute("order", currentOrder);
        model.addAttribute("allCommentaries",commentaries);
        model.addAttribute("comment",comment);
        model.addAttribute("currentUser", user);

        if(user.getRole().equals("ADMIN")){
            return "adminPage/comentariesPage";
        }
        return "userPage/comentariesPage";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addCommentPost(@PathVariable("id")int ordereId,@ModelAttribute("content") String content, Model model, HttpServletRequest request) {
        System.out.println("comm:" + request.getParameter("content"));

        commentService.createComment(createCommentObject(ordereId, content));
        sentNotification(ordereId,getCurrentUser());
        return "redirect:/addComment/{id}";
    }

    private int getCurrentUser(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        return (Integer) session.getAttribute("id");
    }

    private Comment createCommentObject(int orderId, String content){
        Comment comment = new Comment();

        User author = new User();
        author.setId(getCurrentUser());

        comment.setOrderId(orderId);
        comment.setContent(content);
        comment.setTime(new Timestamp(new Date().getTime()));
        comment.setAuthor(author);

        return comment;
    }

    private void sentNotification (int currentOrderId, int currentUserId){
        User currentUser = userService.getById(currentUserId);
        //Get super admin
        Order currentOrder = orderService.getById(currentOrderId);

        if(currentUser.getRole().equals(UserRoles.USER.toString()) ||
                currentUser.getRole().equals(UserRoles.PROFESSOR.toString())){
            User assistant = userService.getById(currentOrder.getAssistantId());
            if(assistant != null){
                commentForAssistMail.send(assistant.getEmail(),currentOrderId);
            }
        }else if(currentUser.getRole().equals(UserRoles.ASSISTANT.toString())){
            User orderAuthor = userService.getById(currentOrder.getUserId());
            if(!currentUser.getEmail().equals(orderAuthor.getEmail())){
                commentForUserMail.send(orderAuthor.getEmail(),currentOrderId);
            }
        }else if(currentUser.getRole().equals(UserRoles.ADMIN.toString())){
            User orderAuthor = userService.getById(currentOrder.getUserId());
            User orderAssistant = userService.getById(currentOrder.getAssistantId());
            if(orderAssistant != null){
                commentForAssistMail.send(orderAssistant.getEmail(),currentOrderId);
            }
            if(!currentUser.getEmail().equals(orderAuthor.getEmail())){
                commentForUserMail.send(orderAuthor.getEmail(),currentOrderId);
            }
        }

    }
}


