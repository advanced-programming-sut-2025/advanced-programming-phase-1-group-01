package models.building;

import models.Game;
import models.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Maps {
    protected List<List<Tile>> tiles;

    public Maps(List<List<Tile>> tiles) {
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

    public Tile getTile(Position position) {
        return tiles.get(position.y()).get(position.x());
    }

    public List<List<Tile>> getTiles() {
        return tiles;
    }

    public String printMap(int x, int y, int size, Game game) {
        if (x + size > tiles.size() || y + size > tiles.get(x).size()) {
            return "invalid map";
        }

        List<List<String>> chars = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            chars.add(new ArrayList<>());
        }

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (game.isAnyoneHere(i, j)) {
                    chars.get(i-x).set(j-y, game.returnSymbol(i, j));
                } else if (tiles.get(i).get(j).getObject() != null) {
                    chars.get(i-x).set(j-y, tiles.get(i).get(j).getObject().getSymbol());
                } else {
                    chars.get(i-x).set(j-y, tiles.get(i).get(j).getType().getSymbol());
                }
            }
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < chars.size(); i++) {
            for (int j = 0; j < chars.get(i).size(); j++) {
                output.append(chars.get(i).get(j));
            }
            output.append("\n");
        }

        return output.toString();

//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                Tile tile = chars.get(i + x).get(j + y);
//                if (tile.getObject() == null) {
//                    output.append(tile.getType().getSymbol());
//                } else {
//                    output.append(tile.getObject().getSymbol());
//                }
//            }
//            output.append("\n");
//        }
//        return output.toString();
    }
}