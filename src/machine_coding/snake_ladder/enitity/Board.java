package machine_coding.snake_ladder.enitity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private Map<Integer, Integer> snakesAndLadders; // head to tail

    public Board(int size, List<BoardEntity> snakesAndLadders) {
        this.size = size;
        this.snakesAndLadders = new HashMap<>();
        for (BoardEntity entity : snakesAndLadders) {
            this.snakesAndLadders.put(entity.getStart(), entity.getEnd());
        }
    }

    public int getSize() {
        return size;
    }

    public int getFinalPosition(int position) {
        return snakesAndLadders.getOrDefault(position, position);
    }
}
