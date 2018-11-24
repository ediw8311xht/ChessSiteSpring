package com.csite.site.engine;

public class Queen extends Piece {

    public String to_string() {
        if (Color == 0) { return "q"; }
        else            { return "Q"; }
    }

    public Queen(int PositionY, int PositionX, int C) {
        super(PositionY, PositionX, C);
    }

    public boolean is_valid_move(Piece board[][], int new_posy, int new_posx) {
        if (!position_valid(new_posy, new_posx)) { return false; }

        if (diagonal_valid(board, new_posy, new_posx) || vertical_horizontal_valid(board, new_posy, new_posx)) {
            return true;
        }
        else {
            return false;
        }
    }

}
