package chess.entity;

import java.util.*;

import chess.ChessException;
import chess.enums.*;

public class Game {
    private final Board board;
    private final Player[] players;
    private Color currentTurn;
    private final List<Move> moveHistory;
    private GameStatus status;

    public Game(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.players = new Player[] { whitePlayer, blackPlayer };
        this.currentTurn = Color.WHITE;
        this.moveHistory = new ArrayList<>();
        this.status = GameStatus.ACTIVE;
    }

    public boolean makeMove(Position from, Position to) {
        // Check game is not over
        if (status == GameStatus.CHECKMATE || status == GameStatus.STALEMATE
                || status == GameStatus.RESIGNED) {
            throw new ChessException("Game is already over: " + status);
        }

        Piece piece = board.getPiece(from);

        // Validate piece exists and belongs to current player
        if (piece == null) {
            throw new ChessException("No piece at " + from);
        }
        if (piece.getColor() != currentTurn) {
            throw new ChessException("Not your turn. Current turn: " + currentTurn);
        }

        // Validate the piece can make this move
        if (!piece.canMove(board, from, to)) {
            throw new ChessException("Illegal move for " + piece.getPieceType()
                    + " from " + from + " to " + to);
        }

        // Record move info before executing
        Move move = new Move(from, to, piece);
        Piece capturedPiece = board.getPiece(to);

        // Handle en passant capture
        if (piece.getPieceType() == PieceType.PAWN
                && Math.abs(to.getCol() - from.getCol()) == 1
                && capturedPiece == null) {
            // Diagonal pawn move to empty square = en passant
            Position capturedPawnPos = new Position(from.getRow(), to.getCol());
            Piece capturedPawn = board.getPiece(capturedPawnPos);
            if (capturedPawn != null
                    && capturedPawn.getPieceType() == PieceType.PAWN
                    && capturedPawn.getColor() != currentTurn) {
                // Verify the captured pawn just made a two-square advance
                if (isValidEnPassant(from, to, capturedPawnPos)) {
                    capturedPiece = capturedPawn;
                    board.setPiece(capturedPawnPos, null);
                } else {
                    throw new ChessException("Invalid en passant");
                }
            }
        }

        move.setCapturedPiece(capturedPiece);

        // Execute the move
        board.movePiece(from, to);
        piece.setHasMoved(true);

        // Handle castling rook movement
        if (piece.getPieceType() == PieceType.KING
                && Math.abs(to.getCol() - from.getCol()) == 2) {
            executeCastlingRookMove(from, to);
        }

        // Handle pawn promotion (auto-promote to queen for simplicity)
        if (piece.getPieceType() == PieceType.PAWN) {
            int promotionRow = (piece.getColor() == Color.WHITE) ? 0 : 7;
            if (to.getRow() == promotionRow) {
                Queen promotedQueen = new Queen(piece.getColor());
                promotedQueen.setHasMoved(true);
                board.setPiece(to, promotedQueen);
                move.setPromotion(true, PieceType.QUEEN);
            }
        }

        // Check if the move leaves our own king in check (illegal)
        if (isInCheck(currentTurn)) {
            // Undo the move
            undoMove(move, from, to, piece, capturedPiece);
            throw new ChessException(
                    "Move leaves your king in check");
        }

        // Move is valid, record it
        moveHistory.add(move);

        // Switch turns
        currentTurn = currentTurn.opposite();

        // Update game status for the new current player
        updateGameStatus();

        return true;
    }

    private boolean isValidEnPassant(Position from, Position to,
            Position capturedPawnPos) {
        if (moveHistory.isEmpty())
            return false;
        Move lastMove = moveHistory.get(moveHistory.size() - 1);
        // The last move must have been a pawn moving two squares
        return lastMove.getPiece().getPieceType() == PieceType.PAWN
                && Math.abs(lastMove.getDestination().getRow()
                        - lastMove.getSource().getRow()) == 2
                && lastMove.getDestination().equals(capturedPawnPos);
    }

    private void executeCastlingRookMove(Position kingFrom, Position kingTo) {
        int row = kingFrom.getRow();
        if (kingTo.getCol() > kingFrom.getCol()) {
            // Kingside castling: move rook from h-file to f-file
            Position rookFrom = new Position(row, 7);
            Position rookTo = new Position(row, 5);
            Piece rook = board.getPiece(rookFrom);
            board.movePiece(rookFrom, rookTo);
            rook.setHasMoved(true);
        } else {
            // Queenside castling: move rook from a-file to d-file
            Position rookFrom = new Position(row, 0);
            Position rookTo = new Position(row, 3);
            Piece rook = board.getPiece(rookFrom);
            board.movePiece(rookFrom, rookTo);
            rook.setHasMoved(true);
        }
    }

    private void undoMove(Move move, Position from, Position to,
            Piece piece, Piece capturedPiece) {
        board.setPiece(from, piece);
        board.setPiece(to, capturedPiece);

        // If it was en passant, restore the captured pawn
        if (piece.getPieceType() == PieceType.PAWN
                && Math.abs(to.getCol() - from.getCol()) == 1
                && board.getPiece(to) == null && capturedPiece != null) {
            Position capturedPawnPos = new Position(from.getRow(), to.getCol());
            board.setPiece(capturedPawnPos, capturedPiece);
            board.setPiece(to, null);
        }

        // If it was castling, undo the rook move too
        if (piece.getPieceType() == PieceType.KING
                && Math.abs(to.getCol() - from.getCol()) == 2) {
            undoCastlingRookMove(from, to);
        }

        // If it was a promotion, the piece reference is already correct
        // since we use the original pawn piece for undo
    }

    private void undoCastlingRookMove(Position kingFrom, Position kingTo) {
        int row = kingFrom.getRow();
        if (kingTo.getCol() > kingFrom.getCol()) {
            board.movePiece(new Position(row, 5), new Position(row, 7));
        } else {
            board.movePiece(new Position(row, 3), new Position(row, 0));
        }
    }

    public boolean isInCheck(Color color) {
        Position kingPos = board.findKing(color);
        Color opponent = color.opposite();

        // Check if any opponent piece can capture the king
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(new Position(row, col));
                if (piece != null && piece.getColor() == opponent) {
                    if (piece.canMove(board, new Position(row, col), kingPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(Color color) {
        if (!isInCheck(color))
            return false;
        return !hasAnyLegalMove(color);
    }

    public boolean isStalemate(Color color) {
        if (isInCheck(color))
            return false;
        return !hasAnyLegalMove(color);
    }

    private boolean hasAnyLegalMove(Color color) {
        for (int fromRow = 0; fromRow < 8; fromRow++) {
            for (int fromCol = 0; fromCol < 8; fromCol++) {
                Piece piece = board.getPiece(new Position(fromRow, fromCol));
                if (piece == null || piece.getColor() != color)
                    continue;

                // Try every destination square
                for (int toRow = 0; toRow < 8; toRow++) {
                    for (int toCol = 0; toCol < 8; toCol++) {
                        Position from = new Position(fromRow, fromCol);
                        Position to = new Position(toRow, toCol);

                        if (from.equals(to))
                            continue;

                        if (piece.canMove(board, from, to)) {
                            // Simulate the move and check if it leaves
                            // our king in check
                            Piece captured = board.getPiece(to);
                            board.movePiece(from, to);

                            boolean stillInCheck = isInCheck(color);

                            // Undo simulation
                            board.setPiece(from, piece);
                            board.setPiece(to, captured);

                            if (!stillInCheck) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void updateGameStatus() {
        if (isCheckmate(currentTurn)) {
            status = GameStatus.CHECKMATE;
        } else if (isStalemate(currentTurn)) {
            status = GameStatus.STALEMATE;
        } else if (isInCheck(currentTurn)) {
            status = GameStatus.CHECK;
        } else {
            status = GameStatus.ACTIVE;
        }
    }

    public void resign(Color color) {
        if (status == GameStatus.CHECKMATE || status == GameStatus.STALEMATE
                || status == GameStatus.RESIGNED) {
            throw new ChessException("Game is already over");
        }
        status = GameStatus.RESIGNED;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Board getBoard() {
        return board;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public List<Move> getMoveHistory() {
        return Collections.unmodifiableList(moveHistory);
    }
}