package tictactoe.strategy;

import tictactoe.entities.Board;
import tictactoe.enums.Symbol;

public class DiagnoalWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();
        // Check main diagonal
        if (row == col) {
            for (int i = 0; i < size; i++) {
                if (board.getCell(i, i).getSymbol() != symbol) {
                    return false;
                }
            }
            return true;
        }
        // Check anti-diagonal
        if (row + col == size - 1) {
            for (int i = 0; i < size; i++) {
                if (board.getCell(i, size - 1 - i).getSymbol() != symbol) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
