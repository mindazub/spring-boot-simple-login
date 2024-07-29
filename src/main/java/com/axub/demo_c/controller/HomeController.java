package com.axub.demo_c.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = "User";
        }

        String message = (String) session.getAttribute("message");
        if (message == null) {
            message = "Hello, " + username;
        }
        model.addAttribute("username", username);
        model.addAttribute("message", message);

        // Clear the message after displaying it
        session.removeAttribute("message");

        return "home";
    }
}
