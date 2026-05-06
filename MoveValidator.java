package chess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveValidator {
    
    // Regex breakdown:
    // ^([a-h]) :  Starts with a letter a-h 
    // ([1-8])  : Followed by a number 1-8
    // [- ]?    : Optional space or hyphen separator
    // ([a-h])  : Followed by a letter a-h 
    // ([1-8])$ : Ends with a number 1-8 
    private static final String MOVE_REGEX = "^([a-h])([1-8])[- ]?([a-h])([1-8])$";
    private static final Pattern MOVE_PATTERN = Pattern.compile(MOVE_REGEX, Pattern.CASE_INSENSITIVE);

    public boolean processMove(Board board, String notation) {
        Matcher matcher = MOVE_PATTERN.matcher(notation.trim());

        if (!matcher.matches()) {
            System.out.println("Invalid notation format. Please use format like 'e2e4' or 'e2-e4'.");
            return false;
        }

        // Map algebraic notation to 2D array indices
        // Columns (a-h) map directly to 0-7
        int startCol = matcher.group(1).toLowerCase().charAt(0) - 'a';
        int endCol = matcher.group(3).toLowerCase().charAt(0) - 'a';

        // Rows (1-8) map inversely to 0-7, assuming index [0][0] is the top-left of the board (a8).
        int startRow = 8 - Integer.parseInt(matcher.group(2));
        int endRow = 8 - Integer.parseInt(matcher.group(4));

        // piece actually exists
        Piece pieceToMove = board.getPiece(startRow, startCol);
        if (pieceToMove == null) {
            System.out.println("Invalid move: No piece exists at the starting square.");
            return false;
        }

        // Check if the specific piece is allowed to make this move
        if (pieceToMove.isValidMove(board, startRow, startCol, endRow, endCol)) {
            
            board.setPiece(endRow, endCol, pieceToMove);
            board.setPiece(startRow, startCol, null);
            
            System.out.println("Move successful!");
            return true;
        } else {
            System.out.println("Invalid move: That piece cannot move like that.");
            return false;
        }
    }
}