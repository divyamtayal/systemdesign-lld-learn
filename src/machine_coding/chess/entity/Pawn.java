package machine_coding.chess.entity;

import machine_coding.chess.enums.*;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canMove(Board board, Position from, Position to) {
        int direction = (color == Color.WHITE) ? -1 : 1;
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = to.getCol() - from.getCol();

        // Forward one square
        if (colDiff == 0 && rowDiff == direction) {
            return board.getPiece(to) == null;
        }

        // Forward two squares from starting position
        if (colDiff == 0 && rowDiff == 2 * direction && !hasMoved) {
            Position intermediate = new Position(
                    from.getRow() + direction, from.getCol());
            return board.getPiece(intermediate) == null
                    && board.getPiece(to) == null;
        }

        // Diagonal capture (including en passant)
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            Piece target = board.getPiece(to);
            if (target != null && target.getColor() != this.color) {
                return true;
            }
            // En passant: target square is empty but adjacent pawn just
            // made a two-square advance
            // (En passant validation is completed in Game.makeMove()
            // which checks the move history)
            return board.getPiece(to) == null
                    && board.getPiece(new Position(from.getRow(), to.getCol())) != null;
        }

        return false;
    }
}