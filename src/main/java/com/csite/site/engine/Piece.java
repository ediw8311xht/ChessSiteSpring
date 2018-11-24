package com.csite.site.engine;


public abstract class Piece {

    int Position[] = new int[2];
    public int Color = -1;

    public Piece(int PositionY, int PositionX, int C) {
        Position[0] = PositionY;
        Position[1] = PositionX;
        Color = C;
    }

    public void update_position(int new_posy, int new_posx) {
        Position[0] = new_posy;
        Position[1] = new_posx;
    }

    public abstract String to_string();

    public abstract boolean is_valid_move(Piece board[][], int new_posy, int new_posx);

    public boolean position_valid(int new_posy, int new_posx) {
        if (new_posy > 7 || new_posy < 0 || new_posx > 7 || new_posx < 0) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean diagonal_valid(Piece board[][], int new_posy, int new_posx) {

        //Check if actually diagonal
        if (Math.abs(new_posy - Position[0]) != Math.abs(new_posx - Position[1])) { return false; }

        int signy = (new_posy > Position[0] ? 1 : -1);
        int signx = (new_posx > Position[1] ? 1 : -1);
        int ttt[] = {Position[0], Position[1]};

        while (ttt[0] != new_posy) {
            ttt[0] += signy; ttt[1] += signx;
            if (board[ttt[0]][ttt[1]] != null && (board[ttt[0]][ttt[1]].Color == Color || ttt[0] != new_posy)) {
                return false;
            }
        }
        return true;
    }

    public boolean vertical_horizontal_valid(Piece board[][], int new_posy, int new_posx) {

        //Checks if actually horizontal or vertical
        if (Math.abs(new_posy - Position[0]) > 0 && Math.abs(new_posx - Position[1]) > 0) {
            return false;
        }

        int signy = (new_posy != Position[0]) ? ((new_posy - Position[0]) / Math.abs(new_posy - Position[0])) : 0;
        int signx = (new_posx != Position[1]) ? ((new_posx - Position[1]) / Math.abs(new_posx - Position[1])) : 0;
        int ttt[] = {Position[0], Position[1]};
        while (ttt[0] != new_posy || ttt[1] != new_posx) {
            ttt[0] += signy; ttt[1] += signx;
            if (board[ttt[0]][ttt[1]] != null) {
                if (ttt[0] != new_posy || board[ttt[0]][ttt[1]].Color == Color) {
                    return false;
                }
            }
        }

        return true;
    }

}
