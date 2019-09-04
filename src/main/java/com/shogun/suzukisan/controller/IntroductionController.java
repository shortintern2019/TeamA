package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IntroductionController {

    @RequestMapping(value="", method= RequestMethod.GET)
    public String root(Model model) {
        model.addAttribute("name", "root");
        return "hello";
    }

    @RequestMapping(value="/home", method= RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("name", "home");
        return "hello";
    }
}
