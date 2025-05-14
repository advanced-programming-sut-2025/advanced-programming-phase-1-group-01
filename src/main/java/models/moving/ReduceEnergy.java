package models.moving;

import models.Position;
import models.character.player.Player;

import java.util.List;

public class ReduceEnergy {
    public static void movePlayer(Player player, Position end) {
        List<Position> path = PathFinding.findPath(player.getPosition(), end, player.getFarm().getTiles());
        Position realEnd = FindRealEnd(path, player);
        player.setPosition(realEnd);
    }

    public static Position FindRealEnd(List<Position> path, Player player) {
        if (path.isEmpty()) return player.getPosition();

        Position end = path.get(0);

        for (int i = 0; i < path.size(); i++) {
            if (player.getEnergy().getAmount() >= 1) {
                player.getEnergy().consume(0.05);
                end = path.get(i);
                if (i >= 2) {
                    Position secondPos = path.get(i);
                    Position firstPos = path.get(i - 2);

                    if ((Math.abs(firstPos.x() - secondPos.x()) + Math.abs(firstPos.y() - secondPos.y()) > 1)) {
                        if (player.getEnergy().getAmount() >= 10) {
                            player.getEnergy().consume(0.5);
                            end = path.get(i);
                        } else {
                            return end;
                        }
                    }
                }
            } else {
                return end;
            }
        }
        return end;
    }

    public static int  calculateEnergy(Player player, Position end) {
        List<Position> path = PathFinding.findPath(player.getPosition(), end, player.getFarm().getTiles());

        int energy = 0;

        for (int i = 0; i < path.size(); i++) {
            energy++;
            if (i >= 2) {
                Position secondPos = path.get(i);
                Position firstPos = path.get(i - 2);

                if (Math.abs(firstPos.x() - end.x()) + Math.abs(firstPos.y() - end.y()) > 1) {
                    energy += 10;
                }
            }
        }
        return energy / 20;
    }
}
