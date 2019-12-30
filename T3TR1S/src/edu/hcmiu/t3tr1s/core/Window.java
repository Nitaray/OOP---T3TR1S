package edu.hcmiu.t3tr1s.core;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

class Window {

    private static Window ourInstance = new Window();

    public static Window getInstance() {
        return ourInstance;
    }

    private static int WIDTH = 1280;
    private static int HEIGHT = 720;

    private static long window;

    private static boolean created = false;

    private static Input input;

    private Window() {}

    /**
     * This method sets the window's size. Default is 1280 x 720
     * @param width width of the window
     * @param height height of the window
     */

    static void setSize(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
    }

    /**
     * This method creates the window for using. This needs to be called before calling other methods.
     * @param windowName name of the window.
     */

    static void create(String windowName) {

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(WIDTH, HEIGHT, windowName, NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        input = new Input();

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                window,
                (vidMode.width() - WIDTH) / 2,
                (vidMode.height() - HEIGHT) / 2
        );

        createCallbacks();

        created = true;
    }

    /**
     * Destroy the window after use.
     */

    static void destroy() {

        if (!created)
            throw new IllegalStateException("Window is not created");

        glfwFreeCallbacks(window);

        glfwDestroyWindow(window);

        created = false;
    }

    /**
     * Make the window the current context and show the window.
     */

    static void show() {

        if (!created)
            throw new IllegalStateException("Window is not created");

        glfwMakeContextCurrent(window);

        GL.createCapabilities();

        glfwShowWindow(window);

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        glEnable(GL_DEPTH_TEST);

        glActiveTexture(GL_TEXTURE0);
    }

    /**
     * Render the window onto the screen.
     */

    static void render() {

        if (!created)
            throw new IllegalStateException("Window is not created");

        glfwSwapBuffers(window);

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Check if the window should be closed or not.
     * @return true if the window should be close, false otherwise.
     */

    static boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    static void createCallbacks(){
        GLFW.glfwSetKeyCallback(window,input.getKeyboard());
        GLFW.glfwSetCursorPosCallback(window,input.getMouseMove());
        GLFW.glfwSetMouseButtonCallback(window,input.getMouseButton());
    }
}
