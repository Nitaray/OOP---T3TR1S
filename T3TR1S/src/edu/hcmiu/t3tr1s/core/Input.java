package edu.hcmiu.t3tr1s.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input {
    // callbacks
    private GLFWKeyCallback keyboard;
    private GLFWCursorPosCallback mouseMove;
    private GLFWMouseButtonCallback mouseButton;

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST]; // store info on keyboard, whether it is pressed or not
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST]; // store info on mouse button, whether it is pressed or not
    private static double mouseX, mouseY; // store info on the coordinate of mouse pointer on the screen
    public Input(){
        // every time a button on keyboard is pressed, this function is executed
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action!=GLFW.GLFW_RELEASE);
            }
        };
        // record the coordinate of mouse pointer
        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        // every time a button on mouse is pressed, this function is executed
        mouseButton = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action!=GLFW.GLFW_RELEASE);
            }
        };
    }

    // getters and setters
    public GLFWKeyCallback getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(GLFWKeyCallback keyboard) {
        this.keyboard = keyboard;
    }

    public GLFWCursorPosCallback getMouseMove() {
        return mouseMove;
    }

    public void setMouseMove(GLFWCursorPosCallback mouseMove) {
        this.mouseMove = mouseMove;
    }

    public GLFWMouseButtonCallback getMouseButton() {
        return mouseButton;
    }

    public void setMouseButton(GLFWMouseButtonCallback mouseButton) {
        this.mouseButton = mouseButton;
    }

    public static double getMouseX() {
        return mouseX;
    }

    public static void setMouseX(double mouseX) {
        Input.mouseX = mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static void setMouseY(double mouseY) {
        Input.mouseY = mouseY;
    }

    // free memory after use
    public void destroy(){
        keyboard.free();
        mouseButton.free();
        mouseMove.free();
    }

    // check whether a key on keyboard is pressed
    public static boolean isKeyPress(int key){
        return keys[key];
    }

    // check whether a button on mouse is pressed
    public static boolean isMouseButtonPress(int button){
        return buttons[button];
    }
}