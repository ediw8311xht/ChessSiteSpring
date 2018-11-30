package com.csite.site.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.csite.site.engine.Game;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/makeGame")
    public String makeGame() {
        return "MakeGame";
    }

    @GetMapping("/getGame")
    public String getGame() {
        return "chess_get_id";
    }

    @PostMapping("/Game")
    public String Game(@ModelAttribute Game game) {
        return "Game";
    }
}
