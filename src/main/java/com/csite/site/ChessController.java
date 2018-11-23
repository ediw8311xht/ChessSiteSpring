package com.csite.site;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/chess")
public class ChessController {
    @GetMapping
    public String showChess(Model model) {
        model.addAttribute("name", "faggots");
        return "chess";
    }

}
