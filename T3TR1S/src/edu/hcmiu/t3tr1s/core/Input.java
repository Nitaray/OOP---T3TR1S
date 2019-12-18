package edu.hcmiu.t3tr1s.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input {
    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST]; // store info on keyboard, whether it is pressed or not
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST]; // store info on mouse button, whether it is pressed or not
    private static double mouseX, mouseY; // store info on the coordinate of mouse pointer on the screen

    private GLFWKeyCallback keyboard; // keyboard callbacks
    private GLFWCursorPosCallback mouseMove; // mouse coordinate callback
    private GLFWMouseButtonCallback mouseButton; // mouse button callback

    // constructor
    public Input(){
        keyboard = new GLFWKeyCallback() {
            @Override
            // every time a button on keyboard is pressed, this function is executed
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE); // check whether a key is pressed;
            }
        };

        mouseButton = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseMove = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };
    }

    public static boolean isKeyDown(int key){
        return keys[key];
    }

    public static boolean isButtonDown(int button){
        return buttons[button];
    }

    public static double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }

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


    // destroy callback after use
    public void destroy(){
        keyboard.free();
        mouseMove.free();
        mouseButton.free();
    }


}
