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

    @RequestMapping(value = "/makeNewChess", method = RequestMethod.GET)
    public String makeNewChessGameGET()
    {
        return "MakeGame";
    }

    @RequestMapping(value = "/getChessById", method = RequestMethod.GET)
    public String getChessGameGET()
    {
        return "chess_get_id";
    }

    @RequestMapping(value = "/makeNewChess", method = RequestMethod.POST)
    public ModelAndView makeNewChessGamePOST() {
        String id = this.gameRepo.makeRandomId();
        Game new_game = new Game(id);
        this.gameRepo.save(new_game);
        return new ModelAndView("redirect:/Game?id=" + id);
    }

    @RequestMapping(value = "/getChessById", method = RequestMethod.POST)
    public ModelAndView getChessGamePOST(@RequestParam("id") String id) {
        Game g = gameRepo.findOne(id);
        if (g == null) {
            return new ModelAndView("redirect:/Error");
        }
        else {
            return new ModelAndView("redirect:/Game?id=" + id);
        }
    }

    @GetMapping("/Game")
    public String Game(@RequestParam("id") String id, Model model) {
        Game g = this.gameRepo.findOne(id);
        if (g == null) {
            return "Error";
        }
        else {
            model.addAttribute("game", g);
            return "Game";
        }
    }
}
