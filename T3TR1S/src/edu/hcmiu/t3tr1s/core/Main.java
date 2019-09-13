package edu.hcmiu.t3tr1s.core;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

public class Main implements  Runnable{

    private boolean running = false;
    private Thread thread;

    private Window window = new Window(1280, 720);


    public void start() {
        running = true;
        thread = new Thread(this, "Game thread");
        thread.start();
    }

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");


        window.create("T3TR1S");

        window.show();
    }

    public void run() {

        init();

        while (running) {
            update();
            window.render();

            if (window.shouldClose())
                running = false;
        }

        window.destroy();

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
