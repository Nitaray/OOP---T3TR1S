package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.client.Client;

class Updater {
    private static Updater thisInstance = new Updater();

    private static final int NANO = 1000000000;

    private Client client;

    private Updater() {}

    private int UPS;
    private long lastTime;

    static Updater getInstance(Client client) {
        thisInstance.UPS = 60;
        thisInstance.lastTime = System.nanoTime();
        thisInstance.client = client;
        return thisInstance;
    }

    void setUPS(int updatePerSecond) {
        if (UPS > 0)
            this.UPS = updatePerSecond;
        else
            System.err.println("UPS cannot be zero");
    }

    private void update() {
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
