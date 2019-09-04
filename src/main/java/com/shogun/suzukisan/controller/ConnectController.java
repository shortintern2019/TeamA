package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConnectController {

    @RequestMapping(value="/connect", method= RequestMethod.POST)
    public String connect(Model model) {
        model.addAttribute("name", "connect");
        return "hello";
    }

}