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

import com.csite.site.engine.Game;
import com.csite.site.repositories.GameRepository;

@Controller
@RequestMapping("/ajax")
public class ChessControllerAjax {

    private GameRepository gameRepo;

    public ChessControllerAjax(GameRepository gameRepo) {
        this.gameRepo = gameRepo;
    }

    @RequestMapping(value = "/makeNewChess", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Game makeNewChessGame(@RequestBody Game game) {
        gameRepo.save(game);
        return game;
    }

    @RequestMapping(value = "/getChessById", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody Game getChessGame(@RequestBody Game game) {
        Game g = gameRepo.findOne(game.getId());
        return g;
    }
}
