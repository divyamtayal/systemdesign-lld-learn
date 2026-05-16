package machine_coding.tictactoe.strategy;

import machine_coding.tictactoe.entities.Board;
import machine_coding.tictactoe.enums.Symbol;

public class RowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWin(Board board, int row, int col, Symbol symbol) {
        int size = board.getSize();
        for (int j = 0; j < size; j++) {
            if (board.getCell(row, j).getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }
}
