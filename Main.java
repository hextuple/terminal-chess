package chess;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Board board = new Board();
    MoveValidator validator = new MoveValidator();
    Scanner scanner = new Scanner(System.in);

    Color currentTurn = Color.WHITE;

    System.out.println("=== TERMINAL CHESS ===");
    System.out.println("Enter moves using standard algebraic notation (e.g., 'e2e4' or 'e2-e4').");
    System.out.println("Type 'quit' to exit the game.");

    while (true) {
      board.printBoard();
      System.out.println(currentTurn + "'s turn. Enter your move:");

      String input = scanner.nextLine().trim();

      if (input.equalsIgnoreCase("quit")) {
        System.out.println("Thanks for playing!");
        break;
      }

      // Quick check to enforce turns before passing to the MoveValidator
      if (input.matches("^[a-hA-H][1-8][- ]?[a-hA-H][1-8]$")) {
        int startCol = Character.toLowerCase(input.charAt(0)) - 'a';
        int startRow = 8 - Character.getNumericValue(input.charAt(1));
        Piece pieceToMove = board.getPiece(startRow, startCol);

        if (pieceToMove != null && pieceToMove.getColor() != currentTurn) {
          System.out.println("Invalid move: It is " + currentTurn + "'s turn. You cannot move your opponent's pieces.");
          continue;
        }
      }

      // Process the move
      boolean moveSuccessful = validator.processMove(board, input);

      // If the move was valid and executed, switch turns
      if (moveSuccessful) {
        currentTurn = (currentTurn == Color.WHITE) ? Color.BLACK : Color.WHITE;
      }
    }

    scanner.close();
  }
}