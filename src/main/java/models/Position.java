package models;

import models.character.player.Player;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isNearTo(Player p) {
        return Math.abs(p.getPosition().getX() - x) <= 1 && Math.abs(p.getPosition().getY() - y) <= 1;
    }
}
