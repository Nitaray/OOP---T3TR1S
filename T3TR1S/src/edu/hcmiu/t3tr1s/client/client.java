package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.block.Rectangle;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class client {
    private client() {}

    public static void test() {
        Rectangle r = new Rectangle(new Vector3f(-16.0f, -9.0f, 0.0f),
                                    new Vector3f(-16.0f, +9.0f, 0.0f),
                                    new Vector3f(+16.0f, +9.0f, 0.0f),
                                    new Vector3f(+16.0f, -9.0f, 0.0f));
        Renderer.addOnScreenObject(r);
    }
}
