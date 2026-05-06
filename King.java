package chess;

public class King extends Piece {

  public King(Color color) {
    super(color);
  }

  @Override
  public boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol) {
    // Check if the destination is exactly one square away in any direction
    int rowDiff = Math.abs(endRow - startRow);
    int colDiff = Math.abs(endCol - startCol);

    // The King moves a maximum of 1 square in either row or column
    if (rowDiff > 1 || colDiff > 1) {
      return false;
    }

    // The King must actually move to a new square
    if (rowDiff == 0 && colDiff == 0) {
      return false;
    }

    //Check the destination square
    Piece targetPiece = board.getPiece(endRow, endCol);
    if (targetPiece == null) {
      return true; // The square is empty
    } else {
      // Valid only if the target piece is the opposite color (a capture)
      return targetPiece.getColor() != this.getColor();
    }
  }
}