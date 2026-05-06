package chess;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        // Logic for Queen's movement (combines Rook and Bishop)
        return false;
    }
}