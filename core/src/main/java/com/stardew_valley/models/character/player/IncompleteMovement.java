package com.stardew_valley.models.character.player;

import com.badlogic.gdx.math.Vector2;
import com.stardew_valley.models.Position;

public class IncompleteMovement {
    private final int row;
    private final int col;
    private boolean hasIncompleteMovement;

    public IncompleteMovement(int col, int row) {
        this.row = row;
        this.col = col;
        hasIncompleteMovement = true;
    }

    public IncompleteMovement(Position position, int additionalCol, int additionalRow) {
        this.col = (int) position.x() / 16 + additionalCol;
        this.row = (int) position.y() / 16 + additionalRow;
        hasIncompleteMovement = true;
    }

    public boolean isHasIncompleteMovement() {
        return hasIncompleteMovement;
    }

    public void setHasIncompleteMovement(boolean hasIncompleteMovement) {
        this.hasIncompleteMovement = hasIncompleteMovement;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Vector2 getVectorPosition() {
        return new Vector2(getXFloat(), getYFloat());
    }

    private float getXFloat() {
        return 16 * col + 8;
    }

    private float getYFloat() {
        return 16 * row + 8;
    }

    public void checkDestination(Vector2 position) {
        if ((int) position.x / 16 == col && (int) position.y / 16 == row) {
            hasIncompleteMovement = true;
        }
    }

    public static int[] floatPositionToInt(Position position) {
        return new int[]{position.x() / 16, position.y() / 16};
    }

    public static int floatPositionToInt(float k) {
        return (int) (k / 16);
    }

}
