package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.block.Rectangle;

import java.util.ArrayList;

public class Renderer {

    private static ArrayList<Rectangle> onScreen = new ArrayList<>();

    private Renderer() {}

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
