package com.axub.demo_c.controller;

import com.axub.demo_c.model.AppUser;
import com.axub.demo_c.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute AppUser user) {
        userService.registerUser(user.getUsername(), user.getPassword());

        // Auto-login after registration
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/";  // Redirect to the home page
    }
}
