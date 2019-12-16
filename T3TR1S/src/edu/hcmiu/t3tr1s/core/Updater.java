package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.client.Client;

public class Updater {
    private static Updater thisInstance = new Updater();

    private static final int NANO = 1000000000;

    private Client client;

    private Updater() {}

    static Updater getInstance(int updatePerSecond, Client client) {
        thisInstance.UPS = updatePerSecond;
        thisInstance.lastTime = System.nanoTime();
        thisInstance.client = client;
        return thisInstance;
    }

    private int UPS;
    private long lastTime;

    void update() {
        client.update();
    }

    void update_prompt() {
        long newTime = System.nanoTime();
        if ((newTime - lastTime) > NANO / UPS) {
            lastTime = newTime;
            update();
        }
    }
}
