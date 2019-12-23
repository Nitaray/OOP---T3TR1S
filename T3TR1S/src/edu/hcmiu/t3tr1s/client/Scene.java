package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

import java.util.ArrayList;

public abstract class Scene {
    protected Rectangle background;

    protected String name;

    Scene(String name, Rectangle background) {
        this.name = name;
        this.background = background;
    };

    void setBackground(Rectangle background) {
        if (background != null)
            this.background = background;
        else
            throw new NullPointerException("Null background encountered!");
    }

    String getName() {
        return name;
    }

    abstract void show(Renderer renderer);

    abstract void hide(Renderer renderer);

    abstract void update(Client client);
}
