package com.martirosyan.p2hw7.controllers;

import com.martirosyan.p2hw7.model.User;
import com.martirosyan.p2hw7.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model){
        userService.delete(id);
        System.out.println("id");
        return "redirect:/users";
    }

    @PostMapping()
    public String addProduct(@ModelAttribute User newUser) {
        userService.save(newUser);
        return "redirect:/users";
    }
}