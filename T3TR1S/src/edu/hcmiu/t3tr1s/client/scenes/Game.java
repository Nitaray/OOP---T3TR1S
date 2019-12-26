package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class Game extends Scene {
    public Game(String name, ShaderManager shaderManager) {
        super(name, shaderManager);

        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND", shaderManager);
        setBackground(background);


    }

    @Override
    public void show(Renderer renderer) {
        background.show(renderer);
    }

    @Override
    public void hide(Renderer renderer) {

    }

    @Override
    public void update(Client client) {

    }
}
