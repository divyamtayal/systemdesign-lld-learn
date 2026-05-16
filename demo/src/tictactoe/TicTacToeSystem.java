package tictactoe;

import tictactoe.entities.Game;
import tictactoe.entities.Player;
import tictactoe.enums.GameStatus;
import tictactoe.observer.Scoreboard;

public class TicTacToeSystem {
    private static TicTacToeSystem instance;
    private Game currentGame;
    private final Scoreboard scoreboard;

    private TicTacToeSystem() {
        this.scoreboard = new Scoreboard();
    }

    public static TicTacToeSystem getInstance() {
        if (instance == null) {
            synchronized (TicTacToeSystem.class) {
                if (instance == null) {
                    instance = new TicTacToeSystem();
                }
            }
        }
        return instance;
    }

    public Game createGame(Player player1, Player player2) {
        this.currentGame = new Game(3, player1, player2);
        this.currentGame.addObserver(scoreboard);
        return this.currentGame;
    }

    public void makeMove(Player player, int row, int col) {
        if (currentGame == null) {
            throw new IllegalStateException("No game in progress");
        }
        System.out.println(player.getName() + " plays at (" + row + ", " + col + ")");
        currentGame.makeMove(row, col);
        currentGame.printBoard();
    }

    public GameStatus getGameStatus() {
        if (currentGame == null) {
            throw new IllegalStateException("No active game.");
        }
        return currentGame.getStatus();
    }

    public void printScoreboard() {
        scoreboard.printScoreboard();
    }

}
