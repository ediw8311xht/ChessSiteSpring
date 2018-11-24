package com.csite.site.engine;

import java.util.*;


public class tests {

    public static void main(String args[]) {
        if (test_check()) { System.out.println("Test 1 Succeeded."); }
        else { System.out.println("Test 1 Failed."); }


    }

    public static boolean test_check() {

        Board tcg = new Board();
        System.out.println(tcg.to_string());
        if (!tcg.move_piece(1, 4, 3, 4)) { return false; }
        System.out.println(tcg.to_string());
        if (!tcg.move_piece(6, 4, 4, 4)) { return false; }
        System.out.println(tcg.to_string());
        if (!tcg.move_piece(0, 6, 2, 5)) { return false; }
        System.out.println(tcg.to_string());
        if (!tcg.move_piece(6, 3, 4, 3)) { return false; }
        System.out.println(tcg.to_string());
        if (!tcg.move_piece(0, 5, 4, 1)) { return false; }
        System.out.println(tcg.to_string());
        if (!tcg.is_check()) { return false; }
        System.out.println(tcg.is_checkmate());
        if (!tcg.move_piece(6, 2, 5, 2)) { return false; }
        System.out.println(tcg.to_string());

        return true;
    }

}
