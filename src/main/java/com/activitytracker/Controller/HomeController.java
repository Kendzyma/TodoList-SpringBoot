package com.activitytracker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/")
public class HomeController {
    /*
     * Home
     */
    @GetMapping()
    public String home(HttpSession session){
        if(session.getAttribute("Auth") == null) return "redirect:/account/login";
else return "index";

    }
}
