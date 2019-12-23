package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.client.buttons.QuitButton;
import edu.hcmiu.t3tr1s.client.buttons.StartButton;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class MainMenu extends Scene {
    private Button startButton;
    private Button quitButton;

    public MainMenu(String name, Rectangle background, ShaderManager shaderManager) {
        super(name, background);
        startButton = new StartButton(new Vector3f(120.0f, 35.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "START_BUTTON", "START_BUTTON_SELECTED", shaderManager, true);
        quitButton = new QuitButton(new Vector3f(120.0f, 25.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "QUIT_BUTTON", "QUIT_BUTTON_SELECTED", shaderManager, false);

        buttons.add(startButton);
        buttons.add(quitButton);
    }

    @Override
    public void show(Renderer renderer) {
        background.show(renderer);

        buttons.forEach(button -> button.show(renderer));
    }

    @Override
    public void hide(Renderer renderer) {
        background.hide(renderer);

        buttons.forEach(button -> button.hide(renderer));
    }


    @Override
    public void update(Client client) {
        updateSelection();
        buttons.forEach(Button::update);

        if (Input.isKeyDown(GLFW_KEY_ENTER) && keyCooled(300 * MILLISECONDS))
            handleSelection(client);
    }
}
