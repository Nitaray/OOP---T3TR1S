package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.client.client;
import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.GLFW.*;

public class Main implements  Runnable{

    private boolean running = false;
    private Thread thread;


    public void start() {
        running = true;
        thread = new Thread(this, "Game thread");
        thread.start();
    }

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        Window.create("T3TR1S");

        Window.show();

        ShaderManager.loadAll();

        ShaderManager.setUniformAll();

    }

    public void run() {

        init();
        client.test();

        while (running) {
            update();
            Renderer.render();

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
