package com.aaradhya.foodapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeController {
    @RequestMapping("/")
    public String greed() {
        return "Hello World!";
    }
}
