package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MatchingController {

    @RequestMapping(value="/matching", method= {RequestMethod.GET, RequestMethod.POST})
    public String connect(Model model) {
        return "genre";
    }

}