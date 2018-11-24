package com.csite.site.engine;

public class Knight extends Piece {

    public String to_string() {
        if (Color == 0) { return "n"; }
        else            { return "N"; }
    }

    public Knight(int PositionY, int PositionX, int C) {
        super(PositionY, PositionX, C);
    }

    public boolean is_valid_move(Piece board[][], int new_posy, int new_posx) {
        if (!position_valid(new_posy, new_posx)) { return false; }

        int ady = Math.abs(new_posy - Position[0]);
        int adx = Math.abs(new_posx - Position[1]);

        //Checking if landing space is invalid.
        if (board[new_posy][new_posx] != null && board[new_posy][new_posx].Color == Color) { return false; }


        if ((ady == 1 && adx == 2) || (ady == 2 && adx == 1)) {  return true; }

        else { return false; }
    }

}
