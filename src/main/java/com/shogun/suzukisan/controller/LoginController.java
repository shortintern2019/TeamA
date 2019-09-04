package com.shogun.suzukisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    /**
     * ログイン中のユーザの情報を表示するページ
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}