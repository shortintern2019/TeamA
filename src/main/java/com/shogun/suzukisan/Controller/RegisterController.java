package com.shogun.suzukisan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String postRegister(Model model) {
        model.addAttribute("name", "postRegister");
        return "hello";
    }

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String getRegister(Model model) {
        model.addAttribute("name", "getRegister");
        return "hello";
    }
}