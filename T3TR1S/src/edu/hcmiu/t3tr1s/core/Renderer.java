package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.exceptions.InvalidObjectException;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

import java.util.ArrayList;

/**
 * A Renderer class to handle all renders in the program.
 */

public class Renderer {

    private static Renderer renderer = new Renderer();

    public static Renderer getInstance() {
        return renderer;
    }
    private static ArrayList<Rectangle> onScreen = new ArrayList<>();

    private Renderer() {}

    /**
     * Add a rectangle to the Renderer to be rendered on screen.
     * @param r An initialized rectangle to be added.
     */

    public void addOnScreenObject(Rectangle r) {
        if (r != null) {
            if (!onScreen.contains(r))
                onScreen.add(r);
        }
        else
            throw new NullPointerException("Invalid object, suspecting uninitialized object");
    }

    public void removeOnScreenObject(Rectangle r) throws InvalidObjectException {
        if (r != null) {
            if (onScreen.contains(r))
                onScreen.remove(r);
            else 
                throw new InvalidObjectException("Object not in rendering!");
        }
        else 
            throw new NullPointerException("Invalid object, suspecting uninitialized object");
    }

    /**
     * Render all rendering processes.
     */

    void render() {
        Window.render();

        onScreen.forEach(Rectangle::render);
    }

}
