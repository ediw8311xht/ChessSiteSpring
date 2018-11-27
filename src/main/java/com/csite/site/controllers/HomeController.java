package com.csite.site.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/makeGame")
    public String makeGame() {
        return "chess";
    }

    @GetMapping("/getGame")
    public String getGame() {
        return "chess_get_id";
    }
}
