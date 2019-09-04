package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReviewController {

    @RequestMapping(value="/review", method= RequestMethod.POST)
    public String review(Model model) {
        model.addAttribute("name", "review");
        return "hello";
    }

}