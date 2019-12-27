package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Game extends Scene {
    private LogicBoard logicBoard = new LogicBoard();

    public Game(String name, ShaderManager shaderManager) {
        super(name, shaderManager);

        this.background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND", shaderManager);
    }

    @Override
    public void show(Renderer renderer) {
        background.show(renderer);
    }

    @Override
    public void hide(Renderer renderer) {
        background.hide(renderer);
    }

    @Override
    public void update(Client client) {
        if (Input.isKeyDown(GLFW_KEY_ESCAPE) && keyCooled(300 * MILLISECONDS))
            client.switchScene("PAUSE");

    }
}
