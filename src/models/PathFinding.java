package models;

import models.building.Tile;
import java.util.*;

public class PathFinding {
    private static final int MAX_ITERATIONS = 10000;

    public static List<Position> findPath(Position start, Position goal, List<List<Tile>> grid) {
        if (checkValid(start, grid) || checkValid(goal, grid) || !grid.get(goal.getX()).get(goal.getY()).isMovable()) {
            return Collections.emptyList();
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fScore));
        Set<Position> closedSet = new HashSet<>();
        Map<Position, Position> cameFrom = new HashMap<>();
        Map<Position, Integer> gScore = new HashMap<>();
        Map<Position, Integer> fScoreMap = new HashMap<>();

        gScore.put(start, 0);
        int startFScore = heuristic(start, goal);
        fScoreMap.put(start, startFScore);
        openSet.add(new Node(start, startFScore));

        int iterations = 0;

        while (!openSet.isEmpty() && iterations++ < MAX_ITERATIONS) {
            //System.out.println(openSet.size());
            Node currentNode = openSet.poll();
            Position current = currentNode.position;


            if (current.getX() == goal.getX() && current.getY() == goal.getY()) {
                return reconstructPath(cameFrom, current);
            }

            closedSet.add(current);

            for (Position neighbor : getNeighbors(current, grid)) {
                if (closedSet.contains(neighbor)) continue;

                int tentativeG = gScore.getOrDefault(current, Integer.MAX_VALUE) + getMoveCost(current, neighbor);

                if (tentativeG < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeG);
                    int fScore = tentativeG + heuristic(neighbor, goal);

                    // فقط اگر نود با fScore بهتر بود، به صف اضافه کن
                    if (!fScoreMap.containsKey(neighbor) || fScore < fScoreMap.get(neighbor)) {
                        fScoreMap.put(neighbor, fScore);
                        openSet.add(new Node(neighbor, fScore));
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    private static class Node {
        Position position;
        int fScore;

        Node(Position position, int fScore) {
            this.position = position;
            this.fScore = fScore;
        }
    }

    private static int heuristic(Position a, Position b) {
        int dx = Math.abs(a.getX() - b.getX());
        int dy = Math.abs(a.getY() - b.getY());
        return 10 * (dx + dy) + (4 * Math.min(dx, dy));
    }

    private static List<Position> reconstructPath(Map<Position, Position> cameFrom, Position current) {
        List<Position> path = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            path.add(0, current);
            current = cameFrom.get(current);
        }
        path.add(0, current);
        return path;
    }

    private static List<Position> getNeighbors(Position pos, List<List<Tile>> grid) {
        List<Position> neighbors = new ArrayList<>();
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        for (int[] dir : directions) {
            int nx = pos.getX() + dir[0];
            int ny = pos.getY() + dir[1];
            Position neighbor = new Position(nx, ny);

            if (checkValid(neighbor, grid)) continue;
            Tile tile = grid.get(nx).get(ny);
            if (!tile.isMovable()) continue;

            // جلوگیری از عبور قطری بین دو مانع
            if (isDiagonal(dir)) {
                Position adj1 = new Position(pos.getX() + dir[0], pos.getY());
                Position adj2 = new Position(pos.getX(), pos.getY() + dir[1]);
                if (checkValid(adj1, grid) || !grid.get(adj1.getX()).get(adj1.getY()).isMovable()) continue;
                if (checkValid(adj2, grid) || !grid.get(adj2.getX()).get(adj2.getY()).isMovable()) continue;
            }

            neighbors.add(neighbor);
        }
        return neighbors;
    }

    private static boolean isDiagonal(int[] dir) {
        return Math.abs(dir[0]) + Math.abs(dir[1]) == 2;
    }

    private static boolean checkValid(Position pos, List<List<Tile>> grid) {
        return pos.getX() < 0 || pos.getX() >= grid.size() ||
                pos.getY() < 0 || pos.getY() >= grid.get(pos.getX()).size();
    }

    private static int getMoveCost(Position current, Position neighbor) {
        return (Math.abs(current.getX() - neighbor.getX()) +
                Math.abs(current.getY() - neighbor.getY()) == 2) ? 14 : 10;
    }
}
