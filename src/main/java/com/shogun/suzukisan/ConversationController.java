package com.shogun.suzukisan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversationController {

    @GetMapping("/conversation")
    public String conversation() {
        return "conversation";
    }

}