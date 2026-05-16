package tictactoe.observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import tictactoe.entities.Game;
import tictactoe.entities.Player;

public class Scoreboard implements GameObserver {
    private ConcurrentHashMap<String, Integer> playerScores;

    public Scoreboard() {
        this.playerScores = new ConcurrentHashMap<>();
    }

    @Override
    public void update(Game game) {
        Player winner = game.getWinner();
        if (winner != null) {
            playerScores.merge(winner.getName(), 1, Integer::sum);
        }
    }

    public void printScoreboard() {
        System.out.println("=== Scoreboard ===");
        playerScores.forEach((player, score) -> {
            System.out.println(player + ": " + score);
        });
    }

}
