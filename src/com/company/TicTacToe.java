package com.company;

public class TicTacToe {
    private static final String[] board = new String[9];

    private static void showBoard() {
        System.out.println("┌─┬─┬─┐");
        for (byte i = 0; i != 9; i+=3) {
            System.out.print("│");
            for (byte j = 0; j < 3; j++) {
                System.out.print(board[i+j] + "│");
            }
            if (i != 6) System.out.println("\n├─┼─┼─┤");
        }
        System.out.println("\n└─┴─┴─┘");
    }

    public static void play() {
    }
}
