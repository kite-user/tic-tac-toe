package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        char[] chars = new char[9];
        Arrays.fill(chars, ' ');

        printBoard(chars);

        Scanner scanner = new Scanner(System.in);

        char current = 'X';
        while (!checkWinner(chars)) {
            int rMove = 0;
            int cMove = 0;

            do {
                while (true) {
                    try {
                        rMove = Integer.parseInt(scanner.next());
                        cMove = Integer.parseInt(scanner.next());
                    } catch (Exception e) {
                        System.out.println("You should enter numbers!");
                        continue;
                    }

                    break;
                }
            } while (!layAMove(chars, rMove, cMove, current));

            printBoard(chars);
            current = current == 'X' ? 'O' : 'X';
        }
    }

    static boolean checkWinner(char[] chars) {

        int xCells = 0;
        int oCells = 0;

        for (char c : chars) {
            if (c == 'X') {
                xCells++;
            }

            if (c == 'O') {
                oCells++;
            }
        }

        if (Math.abs(xCells - oCells) >= 2) {
            System.out.println("Impossible");
            return true;
        }

        int emptyCells = 9 - xCells - oCells;

        int[][] winMoves = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6},
        };

        boolean isXWin = checkWins('X', chars, winMoves);
        boolean isOWin = checkWins('O', chars, winMoves);

        if (!isXWin && !isOWin && emptyCells > 0) {
            return false;
        }

        if (!isXWin && !isOWin && emptyCells == 0) {
            System.out.println("Draw");
            return true;
        }

        if (!isXWin && isOWin) {
            System.out.println("O wins");
            return true;
        }

        if (isXWin && !isOWin) {
            System.out.println("X wins");
            return true;
        }

        System.out.println("Impossible");
        return false;
    }

    static boolean layAMove(char[] chars, int row, int col, char current) {
        if ((row <= 0 || row >= 4) || (col <= 0 || col >= 4)) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }

        int position = 3 * (row - 1) + col - 1;

        if (chars[position] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }

        chars[position] = current;
        return true;
    }

    static void printBoard(char[] chars) {
        String row1 = String.format("| %s %s %s |", chars[0], chars[1], chars[2]);
        String row2 = String.format("| %s %s %s |", chars[3], chars[4], chars[5]);
        String row3 = String.format("| %s %s %s |", chars[6], chars[7], chars[8]);

        System.out.println("---------");
        System.out.println(row1);
        System.out.println(row2);
        System.out.println(row3);
        System.out.println("---------");
    }

    static boolean checkWins(char c, char[] array, int[][] winMoves) {
        for (int[] move : winMoves) {
            if (array[move[0]] != c) {
                continue;
            }

            if (array[move[1]] != c) {
                continue;
            }

            if (array[move[2]] != c) {
                continue;
            }

            return true;
        }

        return false;
    }
}
