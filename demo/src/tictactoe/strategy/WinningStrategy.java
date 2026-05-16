package tictactoe.strategy;

import tictactoe.entities.Board;
import tictactoe.enums.Symbol;

public interface WinningStrategy {
    public boolean checkWin(Board board, int row, int col, Symbol symbol);
}
