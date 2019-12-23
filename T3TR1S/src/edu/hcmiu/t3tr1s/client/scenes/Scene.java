package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

import java.util.ArrayList;

public abstract class Scene {
    protected Rectangle background;

    protected String name;

    public Scene(String name, Rectangle background) {
        this.name = name;
        this.background = background;
    };

    public void setBackground(Rectangle background) {
        if (background != null)
            this.background = background;
        else
            throw new NullPointerException("Null background encountered!");
    }

    public String getName() {
        return name;
    }

    public abstract void show(Renderer renderer);

    public abstract void hide(Renderer renderer);

    public abstract void update(Client client);
}
