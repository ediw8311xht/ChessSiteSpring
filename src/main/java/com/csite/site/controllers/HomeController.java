package com.csite.site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/chess")
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
}
