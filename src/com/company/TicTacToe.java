package com.company;

import java.util.Scanner;

public class TicTacToe {
    private static final Scanner sc = new Scanner(System.in);
    private static final char[] board = new char[9];
    private static final int[][] winLines = {
            {0, 1, 2}, {0, 3, 6},
            {3, 4, 5}, {1, 4, 7},
            {6, 7, 8}, {2, 5, 8},
            {3, 5, 7}, {0, 4, 8}
    };
    private static char currentTurn = 'O';

    static {
        for (int i = 1; i < 10; i++)
            board[i - 1] = (char) (i + '0');
    }

    public static void play() {
        for (int turns = 0; turns < 9; turns++) {
            clearScreenAndPrint("Turn: " + currentTurn);
            showBoard();
            setPosition(prompt());
            if (checkWin()) {
                clearScreenAndPrint("Winner: " + currentTurn + "\nPress enter to exit.");
                sc.nextLine();
                return;
            }
            currentTurn = turns % 2 == 0 ? 'X' : 'O';
        }
        clearScreenAndPrint("It's a draw.\nPress enter to exit.");
        sc.nextLine();
    }

    private static void clearScreenAndPrint(String message) {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Something went wrong during clearing the console ^_+");
            System.out.println("Don't worry about this error. It may show couple of times.");
        }
        System.out.println(message);
    }

    private static void showBoard() {
        System.out.println("┌─┬─┬─┐");
        for (int i = 0; i != 9; i += 3) {
            System.out.print("│");
            for (int j = 0; j < 3; j++)
                System.out.print(board[i + j] + "│");
            if (i != 6) System.out.println("\n├─┼─┼─┤");
        }
        System.out.println("\n└─┴─┴─┘");
    }

    private static int prompt() {
        System.out.print("Position: ");
        int index = sc.nextInt() - 1;
        while (index < 0 || index > 8 || board[index] == 'O' || board[index] == 'X') {
            System.out.print("Try again: ");
            index = sc.nextInt() - 1;
        }
        return index;
    }

    private static void setPosition(int index) {
        board[index] = currentTurn;
    }

    private static boolean checkWin() {
        for (var winLine : winLines)
            if (board[winLine[0]] == currentTurn &&
                    board[winLine[1]] == currentTurn &&
                    board[winLine[2]] == currentTurn)
                return true;
        return false;
    }

}
