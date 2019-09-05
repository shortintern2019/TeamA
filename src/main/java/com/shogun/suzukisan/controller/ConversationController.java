package com.shogun.suzukisan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.shogun.suzukisan.entity.User;

@Controller
public class ConversationController {

    @GetMapping("/standby")
    public String standby() {
        return "standby";
    }

    @GetMapping("/conversation")
    public String conversation(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal() instanceof User){
            User user = User.class.cast(authentication.getPrincipal());
            model.addAttribute("userInfo", "現在ログインしているユーザ名：" + user.getUsername() + "をコントローラクラスから取得しました。");
        }else{
            model.addAttribute("userInfo", "");
        }
        return "conversation";
    }

}