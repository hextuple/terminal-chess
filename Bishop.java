package chess;

public class Bishop extends Piece {

  public Bishop(Color color) {
    super(color);
  }

  @Override
  public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
    //Check if the move is actually diagonal
    // A move is diagonal if the change in rows equals the change in columns
    int rowDiff = Math.abs(endRow - startRow);
    int colDiff = Math.abs(endCol - startCol);

    if (rowDiff != colDiff || rowDiff == 0) {
      return false; // Not a diagonal move, or no movement at all
    }

    // Determine the direction of movement (-1 for up/left, 1 for down/right)
    int rowDirection = (endRow > startRow) ? 1 : -1;
    int colDirection = (endCol > startCol) ? 1 : -1;

    // Check for obstructions along the path
    int currentRow = startRow + rowDirection;
    int currentCol = startCol + colDirection;

    while (currentRow != endRow && currentCol != endCol) {
      if (board.getPiece(currentRow, currentCol) != null) {
        // There is a piece blocking the path
        return false;
      }
      currentRow += rowDirection;
      currentCol += colDirection;
    }

    // Check the destination square
    Piece targetPiece = board.getPiece(endRow, endCol);
    if (targetPiece == null) {
      return true; // The square is empty
    } else {
      // Valid only if the target piece is the opposite color (a capture)
      return targetPiece.getColor() != this.getColor();
    }
  }
}