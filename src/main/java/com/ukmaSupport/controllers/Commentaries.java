package com.ukmaSupport.controllers;


import com.ukmaSupport.models.Comment;
import com.ukmaSupport.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/addComment")
public class Commentaries {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String addComment(@PathVariable("id")int ordereId, Model model, HttpServletRequest request){

        List<Comment> commentaries = commentService.getAllComments(ordereId);
        Comment comment = new Comment();

        model.addAttribute("allCommentaries",commentaries);
        model.addAttribute("comment",comment);
        return "userPage/comentariesPage";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addCommentPost(@PathVariable("id")int ordereId, Model model, HttpServletRequest request) {
        return "";
    }
}

 
