package chess;

public abstract class Piece {
    private Color color;
    private boolean isKilled = false;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isKilled() {
        return this.isKilled;
    }

    public void setKilled(boolean killed) {
        this.isKilled = killed;
    }

    // Every piece will have to define its own movement rules
    public abstract boolean isValidMove(Board board, int startRow, int startCol, int endRow, int endCol);
}