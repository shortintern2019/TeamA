package com.shogun.suzukisan.controller;

import com.shogun.suzukisan.entity.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    @RequestMapping(value="/review", method= RequestMethod.POST)
    public String review(Model model, @RequestParam("userId") String userId, @RequestParam("ratingScore") Integer ratingScore) {
        model.addAttribute("name", "review");

        System.out.println(userId);
        return "root";
    }

}