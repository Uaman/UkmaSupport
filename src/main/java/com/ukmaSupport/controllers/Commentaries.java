package com.ukmaSupport.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/addComment")
public class Commentaries {

    @RequestMapping(value = "/{odereId}", method = RequestMethod.GET)
    public String infoBook(@PathVariable("ordereId")int bookId, Model model,Locale locale, HttpServletRequest request){
        return "";
    }
}
