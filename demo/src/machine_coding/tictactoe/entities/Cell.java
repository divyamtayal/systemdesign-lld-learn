package machine_coding.tictactoe.entities;

import machine_coding.tictactoe.enums.Symbol;

public class Cell {
    private Symbol symbol;

    Cell() {
        this.symbol = Symbol.EMPTY;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public boolean isEmpty() {
        return this.symbol == Symbol.EMPTY;
    }

}
