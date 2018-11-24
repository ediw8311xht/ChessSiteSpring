package com.csite.site.engine;

public class King extends Piece {

    public String to_string() {
        if (Color == 0) { return "k"; }
        else            { return "K"; }
    }

    public King(int PositionY, int PositionX, int C) {
        super(PositionY, PositionX, C);
    }

    public boolean is_valid_move(Piece board[][], int new_posy, int new_posx) {
        if (!position_valid(new_posy, new_posx)) { return false; }

        int ady = Math.abs(new_posy - Position[0]);
        int adx = Math.abs(new_posx - Position[1]);

        //Check if distance is valid.
        if (ady > 1 || adx > 1) { return false; }

        //Check if capture is real.
        if (board[new_posy][new_posx].Color == Color) { return false; }

        return true;
    }
}
