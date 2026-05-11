package chess;

public class Rook extends Piece {
    private boolean hasMoved = false;

    public Rook(Color color) {
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

        if (rowDiff != 0 && colDiff != 0) {
            return false;
        }
        if (rowDiff == 0 && colDiff == 0) {
            return false;
        }

        int rowDirection = Integer.compare(endRow, startRow);
        int colDirection = Integer.compare(endCol, startCol);

        int currentRow = startRow + rowDirection;
        int currentCol = startCol + colDirection;

        while (currentRow != endRow || currentCol != endCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false; 
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        Piece targetPiece = board.getPiece(endRow, endCol);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }
}