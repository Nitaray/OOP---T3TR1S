package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.graphics.Shader;
import edu.hcmiu.t3tr1s.math.Matrix4f;
import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Main class of the program, starting point of the entire program.
 */

public class Main implements Runnable{

    private boolean running = false;
    private Thread thread;
    private Renderer renderer;
    private ShaderManager shaderManager;
    private Client client;
    private Updater updater;

    private void start() {
        running = true;
        thread = new Thread(this, "T3TR1S Thread");
        renderer = Renderer.getInstance();
        shaderManager = ShaderManager.getInstance(Matrix4f.orthographic(0, 100.0f, 0, 100.f * 9.0f / 16.0f, -1.0f, 1.0f));
        client = Client.getInstance(renderer, shaderManager);
        updater = Updater.getInstance(60, client);
        thread.start();
    }

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        Window.create("T3TR1S");

        Window.show();

        shaderManager.init();

        ShapeDataManager.init();
    }

    public void run() {

        init();
        client.test();

        while (running) {
            update();
            renderer.render();

            if (Window.shouldClose() || client.shouldQuit() || (Input.isKeyDown(GLFW_KEY_LEFT_ALT) && Input.isKeyDown(GLFW_KEY_F4)))
                running = false;
        }

        Window.destroy();

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void update() {
        glfwPollEvents();
        updater.update_prompt();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
