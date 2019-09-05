package com.shogun.suzukisan.controller;

import com.shogun.suzukisan.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MatchingController {

    @Autowired
    GenreService genreService;

    @RequestMapping(value="/matching", method= {RequestMethod.GET, RequestMethod.POST})
    public String connect(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genre";
    }

}