package chess;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        // Pawn's movement (forward only, attacks diagonally)
        return false;
    }
}