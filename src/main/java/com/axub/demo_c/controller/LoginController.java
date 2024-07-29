package com.axub.demo_c.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model, HttpSession session) {
        if (session.getAttribute("message") == null) {
            session.setAttribute("message", "Hello, thank you for logging in");
        }
        return "login";
    }
}
