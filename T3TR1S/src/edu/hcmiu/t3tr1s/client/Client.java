package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.math.Vector3f;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;

/**
 * The client to script and update the game.
 * TODO: By Luu Minh Long
 */

public class Client {
    private Client() {}

    private static Client instance = new Client();

    public static Client getInstance(Renderer renderer, ShaderManager shaderManager) {
        instance.renderer = renderer;
        instance.shaderManager = shaderManager;
        return instance;
    }

    private Renderer renderer;

    private ShaderManager shaderManager;

    public void test() {
        Rectangle r = new Rectangle(new Vector3f(0, 100.0f * 9.0f / 16.0f, 0.0f), 100.0f, 100.0f * 9.0f / 16.0f, "REGULAR_RECTANGLE", "BACKGROUND", shaderManager);
        Rectangle rt = new Rectangle(new Vector3f(30, 90.0f * 9.0f / 16.0f, 0.1f), 40.0f, 40.0f * 285.0f / 412.0f, "REGULAR_RECTANGLE", "TETRIS", shaderManager);
        Rectangle rs = new Rectangle(new Vector3f(40, 25.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 209.0f / 1563.0f, "REGULAR_RECTANGLE", "START", shaderManager);
        Rectangle rq = new Rectangle(new Vector3f(40, 15.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 255.0f / 1431.0f, "REGULAR_RECTANGLE", "QUIT", shaderManager);
        Rectangle ra = new Rectangle(new Vector3f(35, 25.0f * 9.0f / 16.0f, 0.1f), 2.67f, 2.67f * 265.0f / 252.0f, "REGULAR_RECTANGLE", "ARROW", shaderManager);
        renderer.addOnScreenObject(rt);
        renderer.addOnScreenObject(rs);
        renderer.addOnScreenObject(rq);
        renderer.addOnScreenObject(ra);
        renderer.addOnScreenObject(r);
        if (Input.isKeyDown(GLFW_KEY_DOWN)){
                ra.translate(new Vector3f(35, 15.0f * 9.0f / 16.0f, 0.1f));
                ra.update();
        }

        Block block = new Block(new Vector3f(50.0f, 50.0f, 1.0f), "SET1_BLUE", shaderManager);
        block.show(renderer);
        
        /**
         * Menu: Tetris title, Start Game, High Score, Quit Game, and Selecting arrow (SA) images
         * Initialize @param choose
         * If UP/DOWN button is pressed, remove SA and render it again at the selected image. @param choose++ or --
         * Start game => Remove all images, then:
         * Render board, blocks, score, and Pause, Quit with SA
         * If ESC is pressed => Pause game, render Continue, Quit
         * If user choose Continue => Continue game
         * If user choose Quit => Quit game, remove all, re-render main menu (no need to ask for Are you sure I think)
         * 
         */

    }
}
