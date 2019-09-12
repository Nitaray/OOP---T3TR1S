package edu.t3tr1s.core;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main implements  Runnable{

    private int WIDTH = 1280;
    private int HEIGHT = 720;

    private boolean running = false;
    private Thread thread;

    private long window;


    public void start() {
        running = true;
        thread = new Thread(this, "Game thread");
        thread.start();
    }

    private void init() {

        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(WIDTH, HEIGHT, "T3TR1S", NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                window,
                (vidmode.width() - WIDTH) / 2,
                (vidmode.height() - HEIGHT) / 2
        );

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);



    }

    public void run() {
        init();
        while (running) {
            update();
            render();

            if (glfwWindowShouldClose(window))
                running = false;
        }

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void update() {
        glfwPollEvents();
    }

    private void render() {
        glfwSwapBuffers(window);
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
