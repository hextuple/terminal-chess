package chess;

public class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // Valid Knight moves are 2 squares in one direction and 1 in the other
        if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
            Piece targetPiece = board.getPiece(endRow, endCol);
            // Square must be empty or contain an opponent's piece
            return targetPiece == null || targetPiece.getColor() != this.getColor();
        }

        return false;
    }
}