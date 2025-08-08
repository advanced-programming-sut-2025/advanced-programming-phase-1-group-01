package com.stardew_valley.network;

import com.badlogic.gdx.Net;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameServer {
    private Server server;
    private final Map<Integer, Network.MovePlayer> playerPositions = new HashMap<>();

    public GameServer() throws IOException {
        server = new Server();
        Network.register(server);

        server.addListener(new Listener() {
            @Override
            public void received(Connection connection, Object object) {
                if (object instanceof Network.MovePlayer) {
                    Network.MovePlayer movePlayer = (Network.MovePlayer) object;

                    playerPositions.put(connection.getID(), movePlayer);

                    server.sendToAllExceptTCP(connection.getID(), movePlayer);
                }
            }

            @Override
            public void disconnected(Connection connection) {
                playerPositions.remove(connection.getID());
                System.out.println("Disconnected: "+ connection.getID());
            }

            @Override
            public void connected(Connection connection) {
                System.out.println("Connected: "+ connection.getID());
            }
        });

        server.start();
        server.bind(Network.PORT);

        System.out.println("Server started on port: " + Network.PORT);
    }

    public static void main(String[] args) throws IOException {
        try {
            new GameServer();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
