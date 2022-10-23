package org.cis120.TwentyFourtyEight;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class TwentyFourtyEight {

    private int[][] board;
    private boolean gameOver;
    private File file = new File("/Users/ryandelopez" +
            "/Documents/Penn/CIS 120/HW/hw09_local_temp" +
            "/src/main/java/org/cis120/TwentyFourtyEight/gameData.txt");

    /**
     * Constructor sets up game state.
     */
    public TwentyFourtyEight() {
        board = new int[4][4];
        reset();
    }

    /**
     * printGameState prints the current game state
     * for debugging.
     */
    public void printGameState() {
        System.out.println("Current board state:\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
                if (j < 3) {
                    System.out.print(" ");
                }
            }
            if (i < 3) {
                System.out.println("\n");
            }
        }
        System.out.println("\n");
    }

    /**
     * reset (re-)sets the game state to start a new game.
     */
    public void reset() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }
        placeNewTile();
        placeNewTile();
        gameOver = false;
    }

    public int getTileValue(int r, int c) {
        return board[r][c];
    }

    public void setTileValue(int r, int c, int val) {
        board[r][c] = val;
    }

    public int getNextGoal() {
        int max = board[0][0];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] > max) {
                    max = board[i][j];
                }
            }
        }
        return max * 2;
    }

    public int[][] getBoard() {
        int[][] currentBoard = board.clone();
        return currentBoard;
    }

    public void setBoard(int[][] newBoard) {
        board = newBoard;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkGameOver() {
        if (isBoardFull()) {
            int[][] initialBoard = board.clone();
            slideLeft();
            int[][] leftBoard = board.clone();
            board = initialBoard.clone();
            slideRight();
            int[][] rightBoard = board.clone();
            board = initialBoard.clone();
            slideUp();
            int[][] upBoard = board.clone();
            board = initialBoard.clone();
            slideDown();
            int[][] downBoard = board.clone();
            if (Arrays.deepEquals(initialBoard, leftBoard)
                && Arrays.deepEquals(initialBoard, rightBoard)
                && Arrays.deepEquals(initialBoard, upBoard)
                && Arrays.deepEquals(initialBoard, downBoard)) {
                gameOver = true;
            }
            board = initialBoard.clone();
        }
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean checkWinner() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void placeNewTile() {
        if (!isBoardFull()) {
            int row;
            int col;
            while (true) {
                row = (int) (Math.random() * 4);
                col = (int) (Math.random() * 4);
                if (board[row][col] == 0) {
                    break;
                }
            }
            board[row][col] = 2;
        }
    }

    public void slideLeft() {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> shiftedRow = new LinkedList<>();
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    shiftedRow.add(board[i][j]);
                }
            }
            LinkedList<Integer> mergedRow = new LinkedList<>();
            while (shiftedRow.size() > 1) {
                int first = shiftedRow.pop();
                int second = shiftedRow.peek();
                if (first == second) {
                    mergedRow.add(first * 2);
                    shiftedRow.pop();
                } else {
                    mergedRow.add(first);
                }
            }
            mergedRow.addAll(shiftedRow);
            for (int k = 0; k < 4; k++) {
                if (mergedRow.isEmpty()) {
                    newBoard[i][k] = 0;
                } else {
                    newBoard[i][k] = mergedRow.pop();
                }
            }
        }
        board = newBoard;
    }

    public void slideRight() {
        int[][] newBoard = new int[4][4];
        for (int i = 3; i >= 0; i--) {
            LinkedList<Integer> shiftedRow = new LinkedList<>();
            for (int j = 3; j >= 0; j--) {
                if (board[i][j] != 0) {
                    shiftedRow.add(board[i][j]);
                }
            }
            LinkedList<Integer> mergedRow = new LinkedList<>();
            while (shiftedRow.size() > 1) {
                int first = shiftedRow.pop();
                int second = shiftedRow.peek();
                if (first == second) {
                    mergedRow.add(first * 2);
                    shiftedRow.pop();
                } else {
                    mergedRow.add(first);
                }
            }
            mergedRow.addAll(shiftedRow);
            for (int k = 3; k >= 0; k--) {
                if (mergedRow.isEmpty()) {
                    newBoard[i][k] = 0;
                } else {
                    newBoard[i][k] = mergedRow.pop();
                }
            }
        }
        board = newBoard;
    }

    public void slideUp() {
        int[][] newBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            LinkedList<Integer> shiftedRow = new LinkedList<>();
            for (int j = 0; j < 4; j++) {
                if (board[j][i] != 0) {
                    shiftedRow.add(board[j][i]);
                }
            }
            LinkedList<Integer> mergedRow = new LinkedList<>();
            while (shiftedRow.size() > 1) {
                int first = shiftedRow.pop();
                int second = shiftedRow.peek();
                if (first == second) {
                    mergedRow.add(first * 2);
                    shiftedRow.pop();
                } else {
                    mergedRow.add(first);
                }
            }
            mergedRow.addAll(shiftedRow);
            for (int k = 0; k < 4; k++) {
                if (mergedRow.isEmpty()) {
                    newBoard[k][i] = 0;
                } else {
                    newBoard[k][i] = mergedRow.pop();
                }
            }
        }
        board = newBoard;
    }

    public void slideDown() {
        int[][] newBoard = new int[4][4];
        for (int i = 3; i >= 0; i--) {
            LinkedList<Integer> shiftedRow = new LinkedList<>();
            for (int j = 3; j >= 0; j--) {
                if (board[j][i] != 0) {
                    shiftedRow.add(board[j][i]);
                }
            }
            LinkedList<Integer> mergedRow = new LinkedList<>();
            while (shiftedRow.size() > 1) {
                int first = shiftedRow.pop();
                int second = shiftedRow.peek();
                if (first == second) {
                    mergedRow.add(first * 2);
                    shiftedRow.pop();
                } else {
                    mergedRow.add(first);
                }
            }
            mergedRow.addAll(shiftedRow);
            for (int k = 3; k >= 0; k--) {
                if (mergedRow.isEmpty()) {
                    newBoard[k][i] = 0;
                } else {
                    newBoard[k][i] = mergedRow.pop();
                }
            }
        }
        board = newBoard;
    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(writer);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    bw.write(String.valueOf(board[i][j]));
                    if (j < 3) {
                        bw.write(" ");
                    }
                }
                if (i < 3) {
                    bw.write("\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(" ");
                for (int i = 0; i < 4; i++) {
                    board[row][i] = Integer.parseInt(arr[i]);
                }
                row++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TwentyFourtyEight t = new TwentyFourtyEight();
        t.printGameState();
        t.slideUp();
        t.printGameState();
        t.slideRight();
        t.printGameState();
    }
}
