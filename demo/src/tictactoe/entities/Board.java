package tictactoe.entities;

import tictactoe.enums.Symbol;

public class Board {
    private Cell[][] grid;
    private int size;

    Board(int size) {
        this.size = size;
        grid = new Cell[size][size];
        intializeBoard();
    }

    private void intializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    public void placeSymbol(int row, int col, Symbol symbol) {
        validatePostion(row, col, symbol);
        grid[row][col].setSymbol(symbol);
    }

    public void validatePostion(int row, int col, Symbol symbol) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        if (grid[row][col].getSymbol() != Symbol.EMPTY) {
            throw new IllegalArgumentException("Cell already occupied");
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getSymbol().getValue() + " ");
            }
            System.out.println();
        }
    }

    public boolean isCellEmpty(int row, int col) {
        validatePostion(row, col, Symbol.EMPTY);
        return grid[row][col].isEmpty();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

}
