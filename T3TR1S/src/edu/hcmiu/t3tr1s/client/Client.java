package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Button;
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

    public static Client getInstance(Renderer renderer, ShaderManager shaderManager) {
        instance.renderer = renderer;
        instance.shaderManager = shaderManager;
        return instance;
    }

    private Renderer renderer;

    private ShaderManager shaderManager;

    private Block block;

    private int key_pressed;
    private Button ra;
    private boolean quit = false;


    public void test() {
        Rectangle r = new Rectangle(new Vector3f(0, 100.0f * 9.0f / 16.0f, 0.0f), 100.0f, 100.0f * 9.0f / 16.0f, "REGULAR_RECTANGLE", "BACKGROUND", shaderManager);
        Rectangle rt = new Rectangle(new Vector3f(30, 90.0f * 9.0f / 16.0f, 0.1f), 40.0f, 40.0f * 285.0f / 412.0f, "REGULAR_RECTANGLE", "TETRIS", shaderManager);
        block = new Block(new Vector3f(50.0f, 50.0f, 1.0f), "SET1_BLUE", shaderManager);
        Rectangle rs = new Rectangle(new Vector3f(40, 25.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 209.0f / 1563.0f, "REGULAR_RECTANGLE", "START", shaderManager);
        Rectangle rq = new Rectangle(new Vector3f(40, 15.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 255.0f / 1431.0f, "REGULAR_RECTANGLE", "QUIT", shaderManager);
        ra = new Button(new Vector3f(35, 25.0f * 9.0f / 16.0f, 0.1f), 2.67f, 2.67f * 265.0f / 252.0f, "REGULAR_RECTANGLE", "ARROW", shaderManager,0);
        renderer.addOnScreenObject(ra);
        renderer.addOnScreenObject(rt);
        renderer.addOnScreenObject(rs);
        renderer.addOnScreenObject(rq);
        renderer.addOnScreenObject(r);
        block.show(renderer);
        key_pressed = GLFW_KEY_UP; // To prevent user moves arrow up when it is pointing at Start Game

    }

    public void update() {
        Vector3f v = new Vector3f(0,6.0f,0.0f);
        // Move arrow down
        if((Input.isKeyDown(GLFW_KEY_DOWN)) && (key_pressed != GLFW_KEY_DOWN)){
            ra.translate(v);
            key_pressed = GLFW_KEY_DOWN;
        }
        // Move arrow up
        else if((Input.isKeyDown(GLFW_KEY_UP)) && (key_pressed != GLFW_KEY_UP)){
            ra.translate(v);
            key_pressed = GLFW_KEY_UP;
        }
        // Quit if user presses Enter when choosing Quit
        if((Input.isKeyDown(GLFW_KEY_ENTER)) && ra.getSelection()==1){
            quit = true;
        }

//        if (Input.isKeyDown(GLFW_KEY_DOWN) && key_pressed != GLFW_KEY_DOWN) {
//            block.move(Direction.DOWN);
//            key_pressed = GLFW_KEY_DOWN;
//        }
//        else if (Input.isKeyDown(GLFW_KEY_UP) && key_pressed != GLFW_KEY_UP) {
//            block.move(Direction.UP);
//            key_pressed = GLFW_KEY_UP;
//        }
//        else if (Input.isKeyDown(GLFW_KEY_LEFT) && key_pressed != GLFW_KEY_LEFT) {
//            block.move(Direction.LEFT);
//            key_pressed = GLFW_KEY_LEFT;
//        }
//        else if (Input.isKeyDown(GLFW_KEY_RIGHT) && key_pressed != GLFW_KEY_RIGHT) {
//            block.move(Direction.RIGHT);
//            key_pressed = GLFW_KEY_RIGHT;
//        }
//        else
//            key_pressed = 0;
    }
    public boolean shouldQuit(){
        return quit;
    }
}
