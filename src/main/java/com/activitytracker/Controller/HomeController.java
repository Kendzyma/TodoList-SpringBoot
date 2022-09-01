package com.activitytracker.Controller;

import com.activitytracker.Model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String home(HttpSession session, Model model) {
        if (session.getAttribute("Auth") == null) return "redirect:/account/login";
        model.addAttribute("task", new Task());
        return "index";
    }
}
