package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    @RequestMapping(value="/review", method= RequestMethod.POST)
    public String review(Model model, @RequestParam("ratingScore") String ratingScore) {
        model.addAttribute("name", "review");
        return "root";
    }

}