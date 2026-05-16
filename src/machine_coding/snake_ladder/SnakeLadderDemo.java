package machine_coding.snake_ladder;

import java.util.Arrays;
import java.util.List;

import machine_coding.snake_ladder.enitity.BoardEntity;
import machine_coding.snake_ladder.enitity.Dice;
import machine_coding.snake_ladder.enitity.Ladder;
import machine_coding.snake_ladder.enitity.Snake;

public class SnakeLadderDemo {
    public static void main(String[] args) {
        List<BoardEntity> boardEntities = List.of(
                new Snake(17, 7), new Snake(54, 34),
                new Snake(62, 19), new Snake(98, 79),
                new Ladder(3, 38), new Ladder(24, 33),
                new Ladder(42, 93), new Ladder(72, 84));

        List<String> players = Arrays.asList("Alice", "Bob", "Charlie");

        Game game = new Game.Builder()
                .setBoard(100, boardEntities)
                .setPlayers(players)
                .setDice(new Dice(1, 6))
                .build();

        game.play();
    }
}
