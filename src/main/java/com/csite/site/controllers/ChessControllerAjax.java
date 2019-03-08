package com.csite.site.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csite.site.engine.Game;
import com.csite.site.repositories.GameRepository;

@Controller
@RequestMapping("/chess/ajax")
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

    @RequestMapping(value = "/movePiece", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Game movePiece(@RequestParam("id") String id,
                                           @RequestParam("move1") String move1,
                                           @RequestParam("move2") String move2) {
        int im1[] = {Integer.parseInt(move1.split("")[0]), Integer.parseInt(move1.split("")[1])};
        int im2[] = {Integer.parseInt(move2.split("")[0]), Integer.parseInt(move2.split("")[1])};

        Game g = this.gameRepo.findOne(id);
        if (g.move_piece(im1[0], im1[1], im2[0], im2[1])) {
            this.gameRepo.updateGame(g);
            return g;
        }
        else {
            return null;
        }
    }
}
