package machine_coding.tictactoe.strategy;

import machine_coding.tictactoe.entities.Board;
import machine_coding.tictactoe.enums.Symbol;

public interface WinningStrategy {
    public boolean checkWin(Board board, int row, int col, Symbol symbol);
}
