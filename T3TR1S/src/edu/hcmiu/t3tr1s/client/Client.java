package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.math.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

/**
 * The client to script and update the game.
 * TODO: By Luu Minh Long
 */

public class Client {
    private Client() {}

    private static Client instance = new Client();

    public static Client getInstance() {
        return instance;
    }

    private Renderer renderer;

    private ShaderManager shaderManager;

    private boolean running;

    public void init(Renderer renderer, ShaderManager shaderManager) {
        this.renderer = renderer;
        this.shaderManager = shaderManager;
    }

    public void test() {
        Rectangle r = new Rectangle(new Vector3f(0, 100.0f * 9.0f / 16.0f, 0.0f), 100.0f, 100.0f * 9.0f / 16.0f, "REGULAR_RECTANGLE", "BACKGROUND", shaderManager);
        Rectangle rt = new Rectangle(new Vector3f(30, 90.0f * 9.0f / 16.0f, 0.1f), 40.0f, 40.0f * 285.0f / 412.0f, "REGULAR_RECTANGLE", "TETRIS", shaderManager);
        renderer.addOnScreenObject(rt);
        renderer.addOnScreenObject(r);
    }

    public void update() {
    }

    public boolean shouldQuit() {
        return !running;
    }
}
