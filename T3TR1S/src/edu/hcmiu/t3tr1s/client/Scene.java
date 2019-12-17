package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

import java.util.ArrayList;

public abstract class Scene {
    protected Rectangle background;

    public void setBackground(Rectangle background) {
        if (background != null)
            this.background = background;
        else
            throw new NullPointerException("Null background encountered!");
    }

    public abstract void show(Renderer renderer);

    public abstract void update();
}
