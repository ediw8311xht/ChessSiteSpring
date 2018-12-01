package com.csite.site.controllers;


import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csite.site.engine.Game;
import com.csite.site.repositories.GameRepository;

@Controller
@RequestMapping("/")
public class ChessController{

    private GameRepository gameRepo;

    public ChessController(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @RequestMapping(value = "/makeNewChess", method = RequestMethod.POST)
    public ModelAndView makeNewChessGame() {
        String id = "make this into an actual id for new game.";
        return new ModelAndView("redirect:/Game?" + id);
    }

    @RequestMapping(value = "/getChessById", method = RequestMethod.POST)
    public String getChessGame(@RequestBody Game game) {
        System.out.println(game.getId());
        Game g = gameRepo.findOne(game.getId());
        if (g == null) {
            return "chess_get_id";
        }
        return "Game";
    }
}
