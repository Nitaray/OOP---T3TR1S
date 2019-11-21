package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * The client to script and update the game.
 * TODO: By Luu Minh Long
 */

public class client {
    private client() {}

    public static void test() {
        Rectangle r = new Rectangle(new Vector3f(0, 100.0f * 9.0f / 16.0f, 0.0f), 100.0f, 100.0f * 9.0f / 16.0f, "BACKGROUND");
        Renderer.addOnScreenObject(r);
    }
}
