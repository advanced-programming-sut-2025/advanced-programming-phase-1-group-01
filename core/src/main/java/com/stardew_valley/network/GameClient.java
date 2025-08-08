package com.stardew_valley.network;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.stardew_valley.models.character.player.Player;
import com.stardew_valley.views.GameView;

import java.io.IOException;

public class GameClient {

    private Client client;
    private int playerId;
    private final GameView gameView;

    public GameClient(GameView gameView) throws IOException {
        this.gameView = gameView;
        client = new Client();
        Network.register(client);

        client.addListener(new Listener() {
            @Override
            public void connected(com.esotericsoftware.kryonet.Connection connection) {
                playerId = connection.getID();
                System.out.println("Connected to the server with Id: " + playerId);
            }

            @Override
            public void received(com.esotericsoftware.kryonet.Connection connection, Object object) {
                if (object instanceof Network.MovePlayer moveUpdate) {
                    System.out.println("Player moved to: X=" + moveUpdate.x + " Y=" + moveUpdate.y);
                    gameView.receiveUpdate(moveUpdate);
                }
            }

            @Override
            public void disconnected(com.esotericsoftware.kryonet.Connection connection) {
                System.out.println("Disconnected from the server");
            }
        });

        client.start();
    }

    public void connect(String ip) throws IOException {
        client.connect(5000, ip, Network.PORT);
    }

    public void sendMove(float x, float y) {
        Network.MovePlayer moveUpdate = new Network.MovePlayer();
        moveUpdate.x = x;
        moveUpdate.y = y;
        client.sendTCP(moveUpdate);
    }

    public void addListener(Listener listener) {
        client.addListener(listener);
    }

    public int getPlayerId() {
        return playerId;
    }
}
