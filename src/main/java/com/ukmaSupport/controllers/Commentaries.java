package com.ukmaSupport.controllers;


import com.ukmaSupport.models.Comment;
import com.ukmaSupport.models.User;
import com.ukmaSupport.services.interfaces.CommentService;
import com.ukmaSupport.services.interfaces.OrderService;
import com.ukmaSupport.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.util.Locale;

@Controller
@RequestMapping("/addComment")
public class Commentaries {

    @Autowired
    private CommentService commentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String addComment(@PathVariable("id")int ordereId, Model model){

        List<Comment> commentaries = commentService.getAllComments(ordereId);
        Comment comment = new Comment();

        model.addAttribute("allCommentaries",commentaries);
        model.addAttribute("comment",comment);
        return "userPage/comentariesPage";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addCommentPost(@PathVariable("id")int ordereId,@ModelAttribute("content") String content, Model model, HttpServletRequest request) {
        System.out.println("comm:" + request.getParameter("content"));

        commentService.createComment(createCommentObject(ordereId, content));

        return "redirect:/addComment/{id}";
    }

    private Comment createCommentObject(int ordereId, String content){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        int currentUserId = (Integer) session.getAttribute("id");

        System.out.println(currentUserId);
        
        Comment comment = new Comment();

        User author = new User();
        author.setId(currentUserId);

        comment.setOrderId(ordereId);
        comment.setContent(content);
        comment.setTime(new Timestamp(new Date().getTime()));
        comment.setAuthor(author);

        return comment;
    }
}


