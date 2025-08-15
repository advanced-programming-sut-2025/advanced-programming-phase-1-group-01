package com.stardew_valley.models.building;

import com.stardew_valley.controllers.LobbyController;
import com.stardew_valley.models.LobbyData;
import com.stardew_valley.models.Position;
import com.stardew_valley.models.character.NPC.NPC;
import com.stardew_valley.models.data.Repository;
import com.stardew_valley.models.initializer.FarmInitializer;
import com.stardew_valley.network.GameClient;
import com.stardew_valley.network.Network;

public class Tile {
    private final Position position;
    private TileType type;
    private boolean isPlowed;
    private boolean isMovable;
    private Building building;
    private TileObject object;


    //boolean isPlowed    -> boolean
    //boolean isMovable   -> boolean
    //TileObject object   -> m/c/s name
    //TileType type       -> name
    //Position position   -> x and y
    public Tile(Builder builder) {
        this.position = builder.getPosition();
        this.type = builder.getType();
        this.isMovable = builder.isMovable();
        this.building = builder.getBuilding();
        this.object = builder.getObject();
    }

    public Position getPosition() {
        return position;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
        Network.SetTileTypeRequest req = new Network.SetTileTypeRequest();
        req.x = position.x();
        req.y = position.y();
        req.typeNum = type.ordinal();
        GameClient.getInstance().sendTileType(req);
    }

    public void setTypeC(TileType type) {
        this.type = type;
    }

    public boolean isMovable() {
        return isMovable;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public boolean plow() {
        if (type != TileType.RIVER && isEmpty()) {
            isPlowed = true;
            Network.SetTilePlowedRequest plowedReq = new Network.SetTilePlowedRequest();
            plowedReq.x = position.x();
            plowedReq.y = position.y();
            plowedReq.plowed = true;
            GameClient.getInstance().sendTilePlowed(plowedReq);
            return true;
        }
        return false;
    }

    public void unPlow() {
        isPlowed = false;
        Network.SetTilePlowedRequest plowedReq = new Network.SetTilePlowedRequest();
        plowedReq.x = position.x();
        plowedReq.y = position.y();
        plowedReq.plowed = false;
        GameClient.getInstance().sendTilePlowed(plowedReq);
    }

    public void setPlowedC(boolean plowed) {
        isPlowed = plowed;
    }

    public void setMovable(boolean movable) {
        isMovable = movable;
        Network.SetTileMovableRequest movableReq = new Network.SetTileMovableRequest();
        movableReq.x = position.x();
        movableReq.y = position.y();
        movableReq.movable = movable;
        GameClient.getInstance().sendTileMovable(movableReq);
    }

    public void setMovableC(boolean movable) {
        isMovable = movable;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public TileObject getObject() {
        return object;
    }

    public void setObject(TileObject object) {
        this.object = object;
        Network.SetObjectRequest req = new Network.SetObjectRequest();
        req.x = position.x();
        req.y = position.y();
        req.object = FarmInitializer.getNumberFromTileObject(object);
        GameClient.getInstance().sendTileObject(req);
    }

    public void setObjectC(TileObject object) {
        this.object = object;
    }

    public void removeObject() {
        this.object = null;
        isMovable = true;
        Network.SetTileMovableRequest movableReq = new Network.SetTileMovableRequest();
        movableReq.x = position.x();
        movableReq.y = position.y();
        movableReq.movable = true;
        GameClient.getInstance().sendTileMovable(movableReq);
    }

    public boolean isEmpty() {
        return object == null;
    }

    public static class Builder {
        private Position position;
        private TileType type;
        private boolean isMovable;
        private Building building;
        private TileObject object;

        public Builder setPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder setType(TileType type) {
            this.type = type;
            Network.SetTileTypeRequest req = new Network.SetTileTypeRequest();
            req.x = position.x();
            req.y = position.y();
            req.typeNum = type.ordinal();
            GameClient.getInstance().sendTileType(req);
            return this;
        }

        public Builder setMovable(boolean movable) {
            isMovable = movable;
            Network.SetTileMovableRequest movableReq = new Network.SetTileMovableRequest();
            movableReq.x = position.x();
            movableReq.y = position.y();
            movableReq.movable = movable;
            GameClient.getInstance().sendTileMovable(movableReq);
            return this;
        }

        public Builder setBuilding(Building building) {
            this.building = building;
            return this;
        }

        public Position getPosition() {
            return position;
        }

        public TileType getType() {
            return type;
        }

        public boolean isMovable() {
            return isMovable;
        }

        public Building getBuilding() {
            return building;
        }

        public Tile build() {
            return new Tile(this);
        }

        public TileObject getObject() {
            return object;
        }

        public Builder setObject(TileObject object) {
            this.object = object;
            Network.SetObjectRequest req = new Network.SetObjectRequest();
            req.x = position.x();
            req.y = position.y();
            req.object = FarmInitializer.getNumberFromTileObject(object);
            //System.out.println("Object: T" + req.object);
            GameClient.getInstance().sendTileObject(req);
            return this;
        }
    }
}
