package chess;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveValidator {
    
    private static final String MOVE_REGEX = "^([a-h])([1-8])[- ]?([a-h])([1-8])$";
    private static final Pattern MOVE_PATTERN = Pattern.compile(MOVE_REGEX, Pattern.CASE_INSENSITIVE);

    public boolean processMove(Board board, String notation) {
        Matcher matcher = MOVE_PATTERN.matcher(notation.trim());

        if (!matcher.matches()) {
            System.out.println("Invalid notation format.");
            return false;
        }

        int startCol = matcher.group(1).toLowerCase().charAt(0) - 'a';
        int endCol = matcher.group(3).toLowerCase().charAt(0) - 'a';
        int startRow = 8 - Integer.parseInt(matcher.group(2));
        int endRow = 8 - Integer.parseInt(matcher.group(4));

        Piece pieceToMove = board.getPiece(startRow, startCol);
        if (pieceToMove == null) {
            System.out.println("Invalid move: No piece exists at the starting square.");
            return false;
        }

        if (pieceToMove.isValidMove(board, startRow, startCol, endRow, endCol)) {
            
            // --- CASTLING EXECUTION ---
            if (pieceToMove instanceof King && Math.abs(startCol - endCol) == 2) {
                int rookStartCol = (endCol > startCol) ? 7 : 0;
                int rookEndCol = (endCol > startCol) ? endCol - 1 : endCol + 1;
                
                // We know it's a Rook because isValidMove passed
                Rook rook = (Rook) board.getPiece(startRow, rookStartCol);
                
                board.setPiece(startRow, rookEndCol, rook);
                board.setPiece(startRow, rookStartCol, null);
                rook.setHasMoved(true); 
                System.out.println("Castling executed!");
            }

            // Move the primary piece
            board.setPiece(endRow, endCol, pieceToMove);
            board.setPiece(startRow, startCol, null);
            
            // --- FLAG UPDATE FOR CASTLING PIECES ---
            if (pieceToMove instanceof King) {
                ((King) pieceToMove).setHasMoved(true);
            } else if (pieceToMove instanceof Rook) {
                ((Rook) pieceToMove).setHasMoved(true);
            }
            
            System.out.println("Move successful!");
            return true;
        } else {
            System.out.println("Invalid move: That piece cannot move like that.");
            return false;
        }
    }
}