package chess;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        boolean isDiagonal = (rowDiff == colDiff && rowDiff != 0);
        boolean isStraight = ((rowDiff == 0 && colDiff != 0) || (rowDiff != 0 && colDiff == 0));

        // Must be a valid straight or diagonal path
        if (!isDiagonal && !isStraight) {
            return false;
        }

        // Determine step direction (-1, 0, or 1)
        int rowDirection = Integer.compare(endRow, startRow);
        int colDirection = Integer.compare(endCol, startCol);

        // Check for obstructions along the path
        int currentRow = startRow + rowDirection;
        int currentCol = startCol + colDirection;

        while (currentRow != endRow || currentCol != endCol) {
            if (board.getPiece(currentRow, currentCol) != null) {
                return false; // Path is blocked
            }
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        // Check the destination square
        Piece targetPiece = board.getPiece(endRow, endCol);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }
}