package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.ShaderManager;
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

    private boolean running;
    private int key_pressed;

    private Button startGame;
    private Button quitGame;

    private Rectangle background;
    private Rectangle logo;

    public void init(Renderer renderer, ShaderManager shaderManager) {
        running = true;

        startGame = new Button(new Vector3f(40, 25.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 209.0f / 1563.0f,
                "REGULAR_RECTANGLE", "START", "START", shaderManager, false);
        quitGame = new Button(new Vector3f(40, 15.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 255.0f / 1431.0f,
                "REGULAR_RECTANGLE", "QUIT", "QUIT", shaderManager, false);

        background = new Rectangle(new Vector3f(0, 100.0f * 9.0f / 16.0f, 0.0f), 100.0f, 100.0f * 9.0f / 16.0f,
                "REGULAR_RECTANGLE", "BACKGROUND", shaderManager);
        logo = new Rectangle(new Vector3f(30, 90.0f * 9.0f / 16.0f, 0.1f), 40.0f, 40.0f * 285.0f / 412.0f,
                "REGULAR_RECTANGLE", "TETRIS", shaderManager);

        show(renderer);
    }

    /**
     * Show the client's graphic processes on the given renderer
     * @param renderer the renderer to draw the client's graphic processes.
     */

    public void show(Renderer renderer) {
        background.show(renderer);
        logo.show(renderer);

        startGame.show(renderer);
        quitGame.show(renderer);
    }

    /**
     * Hide the client's graphic processes on the given renderer.
     * @param renderer the renderer to hide the client's graphic processes.
     */

    public void hide(Renderer renderer) {
        background.hide(renderer);
        logo.hide(renderer);

        startGame.hide(renderer);
        quitGame.hide(renderer);
    }

    public void update() {
//        Vector3f v = new Vector3f(0,6.0f,0.0f);
//        // Move arrow down
//        if((Input.isKeyDown(GLFW_KEY_DOWN)) && (key_pressed != GLFW_KEY_DOWN)){
//            ra.translate(v);
//            key_pressed = GLFW_KEY_DOWN;
//        }
//        // Move arrow up
//        else if((Input.isKeyDown(GLFW_KEY_UP)) && (key_pressed != GLFW_KEY_UP)){
//            ra.translate(v);
//            key_pressed = GLFW_KEY_UP;
//        }
//        // Quit if user presses Enter when choosing Quit
//        if((Input.isKeyDown(GLFW_KEY_ENTER)) && ra.getSelection()==1){
//            running = false;
//        }
    }

    public boolean shouldQuit() {
        return !running;
    }
}
