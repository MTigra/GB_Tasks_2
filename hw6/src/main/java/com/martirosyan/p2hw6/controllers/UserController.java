package com.martirosyan.p2hw6.controllers;

import com.martirosyan.p2hw6.model.Product;
import com.martirosyan.p2hw6.model.User;
import com.martirosyan.p2hw6.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model){
        model.addAttribute("products", userService.getAll());
        return "users";
    }
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model){
        return null;
    }

    @PostMapping()
    public String addProduct(@ModelAttribute User newUser){
        userService.save(newUser);
        return "redirect:/users";
    }
}
