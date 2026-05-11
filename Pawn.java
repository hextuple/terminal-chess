package chess;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        // White pawns move "up" (-1), Black pawns move "down" (+1)
        int direction = (this.getColor() == Color.WHITE) ? -1 : 1;
        
        // Initial ranks based on the Board's setup
        int startingRank = (this.getColor() == Color.WHITE) ? 6 : 1;

        int rowDiff = endRow - startRow;
        int colAbsDiff = Math.abs(endCol - startCol);

        // 1. Moving forward 1 square
        if (colAbsDiff == 0 && rowDiff == direction) {
            return board.getPiece(endRow, endCol) == null; // Square must be empty
        }

        // 2. Initial 2-square move
        if (colAbsDiff == 0 && rowDiff == 2 * direction && startRow == startingRank) {
            // Both the intermediate square and target square must be empty
            return board.getPiece(startRow + direction, startCol) == null && 
                   board.getPiece(endRow, endCol) == null;
        }

        // 3. Diagonal capture
        if (colAbsDiff == 1 && rowDiff == direction) {
            Piece targetPiece = board.getPiece(endRow, endCol);
            // Must contain an opponent's piece
            return targetPiece != null && targetPiece.getColor() != this.getColor();
        }

        return false;
    }
}