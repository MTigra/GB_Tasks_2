package com.geekbrains.book.store.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/about")
public class AboutController {

    @GetMapping
    public String getAboutPage(){
        return "about-page";
    }
}
