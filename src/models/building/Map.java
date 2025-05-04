package models.building;

import models.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Map {
    protected List<List<Tile>> tiles;

    public Map(List<List<Tile>> tiles) {
        this.tiles = tiles;
    }

    public void initTiles(int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            tiles.add(new ArrayList<>());
            for (int j = 0; j < cols; j++) {
                tiles.get(i).add(new Tile.Builder()
                        .setPosition(new Position(i, j))
                        .setType(TileType.GROUND)
                        .setMovable(true)
                        .setBuilding(null)
                        .setObject(null)
                        .build());
            }
        }
    }

    private final List<Tile> shuffledTiles = new ArrayList<>();
    private final Random random = new Random();

    public void prepareShuffledTiles() {
        shuffledTiles.clear();
        for (List<Tile> row : tiles) {
            shuffledTiles.addAll(row);
        }
        Collections.shuffle(shuffledTiles, random);
    }

    public Tile getRandomTile() {
        if (shuffledTiles.isEmpty()) {
            prepareShuffledTiles();
        }
        return shuffledTiles.remove(shuffledTiles.size() - 1);
    }

    public Tile getTileByPosition(Position position) {
        return tiles.get(position.getY()).get(position.getX());
    }

    public List<List<Tile>> getTiles() {
        return tiles;
    }
}