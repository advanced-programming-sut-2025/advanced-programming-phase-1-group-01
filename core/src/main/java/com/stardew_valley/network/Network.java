package com.stardew_valley.network;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
    public static final int PORT = 54555;

    public static void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();

        kryo.register(MovePlayer.class);

    }

    public static class MovePlayer {
        public int playerId;
        public float x;
        public float y;
    }
}
