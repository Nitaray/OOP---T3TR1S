package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class Game extends Scene {
    public Game(String name) {
        super(name);

        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND");
        setBackground(background);


    }

    @Override
    public void show() {
        background.show();
    }

    @Override
    public void hide() {

    }

    @Override
    public void update(Client client) {

    }
}
