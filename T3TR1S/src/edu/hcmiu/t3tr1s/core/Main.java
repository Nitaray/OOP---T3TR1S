package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.Client;
import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Main class of the program, starting point of the entire program.
 */

public class Main implements Runnable{

    private boolean running = false;
    private Thread thread;
    private Renderer renderer;
    private Client client;

    private void start() {
        running = true;
        thread = new Thread(this, "T3TR1S Thread");
        renderer = Renderer.getInstance();
        client = Client.getClient(renderer);
        thread.start();
    }

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        Window.create("T3TR1S");

        Window.show();

        ShaderManager.init();

        ShapeDataManager.init();
    }

    public void run() {

        init();
        client.test();

        while (running) {
            update();
            renderer.render();

            if (Window.shouldClose())
                running = false;
        }

        Window.destroy();

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void update() {
        glfwPollEvents();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
