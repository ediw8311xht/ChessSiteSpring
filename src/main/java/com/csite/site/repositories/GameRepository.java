package com.csite.site.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.csite.site.engine.Game;

@Repository
public class GameRepository {
    private JdbcTemplate jdbc;

    @Autowired
    public GameRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Game save(Game game) {
        jdbc.update("INSERT INTO Game (id, board, turn) VALUES (?, ?, ?)",
                    game.getId(), game.getBoard(), game.getTurn());
        return game;
    }

    public Game findOne(String id) {
        return jdbc.queryForObject("SELECT id, board, turn FROM Game WHERE id = ?", this::mapRowToGame, id);
    }

    private Game mapRowToGame(ResultSet rs, int rowNum) throws SQLException {
        return new Game(rs.getString("id"), rs.getString("board"), rs.getInt("turn"));
    }


}
