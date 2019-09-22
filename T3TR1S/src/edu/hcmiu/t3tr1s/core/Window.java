package edu.hcmiu.t3tr1s.core;

import org.lwjgl.glfw.*;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private int WIDTH = 1280;
    private int HEIGHT = 720;

    private long window;

    private boolean created = false;

    /**
     * Default constructor for the class Window.
     */

    public Window() {

        WIDTH = 1280;

        HEIGHT = 720;
    }

    /**
     * The constructor for the class Window.
     * @param width the window's width.
     * @param height the window's height.
     */

    public Window(int width, int height) {

        this.WIDTH = width;

        this.HEIGHT = height;
    }

    /**
     * This method creates the window for using. This needs to be called before calling other methods.
     * @param windowName name of the window.
     */

    public void create(String windowName) {

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        this.window = glfwCreateWindow(this.WIDTH, this.HEIGHT, windowName, NULL, NULL);

        if (this.window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(
                this.window,
                (vidMode.width() - WIDTH) / 2,
                (vidMode.height() - HEIGHT) / 2
        );

        this.created = true;
    }

    /**
     * Destroy the window after use.
     */

    public void destroy() {

        if (!this.created)
            throw new IllegalStateException("Window is not created");

        glfwFreeCallbacks(this.window);

        glfwDestroyWindow(this.window);

        this.created = false;
    }

    /**
     * Make the window the current context and show the window.
     */

    public void show() {

        if (!this.created)
            throw new IllegalStateException("Window is not created");

        glfwMakeContextCurrent(window);

        glfwShowWindow(window);
    }

    /**
     * Render the window onto the screen.
     */

    public void render() {

        if (!this.created)
            throw new IllegalStateException("Window is not created");

        glfwSwapBuffers(this.window);
    }

    /**
     * Check if the window should be closed or not.
     * @return true if the window should be close, false otherwise.
     */

    public boolean shouldClose() {
        return glfwWindowShouldClose(this.window);
    }
}
