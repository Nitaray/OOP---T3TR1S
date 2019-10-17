package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.blocks.Rectangle;

import java.util.ArrayList;

public class Renderer {

    private static ArrayList<Rectangle> onScreen = new ArrayList<>();

    private Renderer() {}

    /**
     * Add a rectangle to the Renderer to be rendered on screen.
     * @param r An initialized rectangle to be added.
     */

    public static void addOnScreenObject(Rectangle r) {
        if (r != null)
            onScreen.add(r);
        else
            System.err.println("Invalid object, suspecting uninitialized object");
    }

    static void render() {
        Window.render();

        onScreen.forEach((object) -> object.render());

    }

}
