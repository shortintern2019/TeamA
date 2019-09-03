package com.shogun.suzukisan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnectController {

    @RequestMapping(value="/connect", method= RequestMethod.POST)
    public String connect(Model model) {
        model.addAttribute("name", "connect");
        return "hello";
    }

}