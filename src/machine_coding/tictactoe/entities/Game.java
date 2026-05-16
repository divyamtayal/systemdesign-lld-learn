package machine_coding.tictactoe.entities;

import java.util.ArrayList;
import java.util.List;

import machine_coding.tictactoe.enums.GameStatus;
import machine_coding.tictactoe.enums.Symbol;
import machine_coding.tictactoe.observer.GameObserver;
import machine_coding.tictactoe.strategy.*;

public class Game {
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex;
    private GameStatus status;
    private final List<WinningStrategy> winningStrategies;
    private final List<GameObserver> observers = new ArrayList<>();

    public Game(int boardSize, Player player1, Player player2) {
        this.board = new Board(boardSize);
        this.players = new Player[] { player1, player2 };
        this.currentPlayerIndex = 0;
        this.status = GameStatus.IN_PROGRESS;
        this.winningStrategies = intializeWinningStrategies();
    }

    private List<WinningStrategy> intializeWinningStrategies() {
        List<WinningStrategy> strategies = new ArrayList<>();
        strategies.add(new RowWinningStrategy());
        strategies.add(new ColumnWinningStrategy());
        strategies.add(new DiagnoalWinningStrategy());
        return strategies;
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update(this);
        }
    }

    public synchronized void makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is already over");
        }

        if (!board.isCellEmpty(row, col)) {
            throw new IllegalArgumentException("Cell is already occupied");
        }

        Player currentPlayer = players[currentPlayerIndex];
        board.placeSymbol(row, col, currentPlayer.getSymbol());
        if (checkWin(row, col, currentPlayer.getSymbol())) {
            status = currentPlayer.getSymbol() == Symbol.X ? GameStatus.X_WON
             : GameStatus.O_WON;
             notifyObservers();
        } else if (board.isBoardFull()) {
            notifyObservers();
            status = GameStatus.DRAW;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }
    }

    private boolean checkWin(int row, int col, Symbol symbol) {
        for (WinningStrategy strategy : winningStrategies) {
            if (strategy.checkWin(board, row, col, symbol)) {
                return true;
            }
        }
        return false;
    }

    public void printBoard() {
        board.printBoard();
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getWinner() {
        if (status == GameStatus.X_WON) {
            return players[0];
        } else if (status == GameStatus.O_WON) {
            return players[1];
        }
        return null;
    }
}
