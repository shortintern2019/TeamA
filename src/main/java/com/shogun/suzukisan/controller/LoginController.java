package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("name", "getLogin");
        return "Hello";
    }

//    TODO POST:/login は別途設定
    @PostMapping("/greeting")
    public String postLogin(Model model) {
        model.addAttribute("name", "postLogin");
        return "Hello";
    }

}