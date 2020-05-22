package com.company;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner sc = new Scanner(System.in);
    private static final char[] board = new char[9];
    private static final byte[][] winLines = {
            {0, 1, 2}, {0, 3, 6},
            {3, 4, 5}, {1, 4, 7},
            {6, 7, 8}, {2, 5, 8},
            {3, 5, 7}, {0, 4, 8}
    };
    private static char currentTurn = 'X';

    static {
        for (byte i = 1; i < 10; i++)
            board[i - 1] = (char) (i + '0');
    }

    public static void play() {
        for (byte turns = 0; turns < 9; currentTurn = currentTurn == 'O' ? 'X' : 'O') {
            clearScreen();
            System.out.println("Turn: " + currentTurn);
            showBoard();
            byte index = prompt();
            setPosition(index);
            if (checkWin()) {
                clearScreen();
                System.out.println("Winner: " + currentTurn + "\nPress enter to exit.");
                return;
            }
            turns++;
        }
        clearScreen();
        System.out.println("It's a draw.\nPress enter to exit.");
        sc.nextLine();
    }

    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Something went wrong during clearing the console ^_+");
            System.out.println("Don't worry about this error. It may show couple of times.");
        }
    }

    private static void showBoard() {
        System.out.println("┌─┬─┬─┐");
        for (byte i = 0; i != 9; i += 3) {
            System.out.print("│");
            for (byte j = 0; j < 3; j++) {
                System.out.print(board[i + j] + "│");
            }
            if (i != 6) System.out.println("\n├─┼─┼─┤");
        }
        System.out.println("\n└─┴─┴─┘");
    }

    private static byte prompt() {
        System.out.print("Position: ");
        byte index = (byte) (sc.nextInt() - 1);
        while (index < 0 || index > 8 || board[index] == 'O' || board[index] == 'X') {
            System.out.print("Try again: ");
            index = (byte) (sc.nextInt() - 1);
        }
        return index;
    }

    private static void setPosition(byte index) {
        board[index] = currentTurn;
    }

    private static boolean checkWin() {
        for (var winLine : winLines) {
            if (board[winLine[0]] == currentTurn &&
                    board[winLine[1]] == currentTurn &&
                    board[winLine[2]] == currentTurn)
                return true;
        }
        return false;
    }

}
