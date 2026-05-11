package chess;

public class King extends Piece {
    private boolean hasMoved = false;

    public King(Color color) {
        super(color);
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // --- CASTLING LOGIC ---
        if (rowDiff == 0 && colDiff == 2) {
            if (this.hasMoved) {
                return false;
            }

            int rookCol = (endCol > startCol) ? 7 : 0; 
            Piece cornerPiece = board.getPiece(startRow, rookCol);

            // Make sure the corner piece is actually a Rook before casting
            if (cornerPiece == null || !(cornerPiece instanceof Rook)) {
                return false;
            }

            Rook rook = (Rook) cornerPiece;
            if (rook.hasMoved()) {
                return false;
            }

            int direction = (endCol > startCol) ? 1 : -1;
            for (int c = startCol + direction; c != rookCol; c += direction) {
                if (board.getPiece(startRow, c) != null) {
                    return false; 
                }
            }
            return true; 
        }
        // --- END CASTLING LOGIC ---

        if (rowDiff > 1 || colDiff > 1) {
            return false;
        }
        if (rowDiff == 0 && colDiff == 0) {
            return false;
        }

        Piece targetPiece = board.getPiece(endRow, endCol);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }
}