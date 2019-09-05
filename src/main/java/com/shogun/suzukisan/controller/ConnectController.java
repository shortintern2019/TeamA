package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnectController {

    @RequestMapping(value="/connect", method= RequestMethod.POST)
    public String connect(Model model, @RequestParam("soudan") String soudan, @RequestParam("genre") String genre) {
        model.addAttribute("soudan", soudan);
        model.addAttribute("genre", genre);
        return "conversation";
    }

}