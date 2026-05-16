package snakeLadder.enitity;

public class Snake extends BoardEntity {
    public Snake(int head, int tail) {
        super(head, tail);
        if (head <= tail) {
            throw new IllegalArgumentException("Snake head must be greater than tail");
        }
    }

}
