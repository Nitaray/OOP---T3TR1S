package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.client.buttons.ContinueButton;
import edu.hcmiu.t3tr1s.client.buttons.QuitToMainMenuButton;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;

public class Pause extends Scene{
    private Button continueButton;
    private Button quitToMainMenuButton;
    public Pause(String name, ShaderManager shaderManager) {
        super(name, shaderManager);
        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND", shaderManager);
        setBackground(background);

        continueButton = new ContinueButton(new Vector3f(62.0f,45.0f,0.1f),36.0f,12.0f,
                "REGULAR_RECTANGLE", "CONTINUE","CONTINUE", shaderManager,true);
        // TODO: change this continueButton's Texture

        quitToMainMenuButton = new QuitToMainMenuButton(new Vector3f(62.0f,33.0f,0.1f),36.0f,12.0f,
                "REGULAR_RECTANGLE", "QUIT_BUTTON","QUIT_BUTTON_SELECTED", shaderManager,false);
        // TODO: change this button's texture too, "QUIT TO MAIN MENU" is ok
        buttons.add(continueButton);
        buttons.add(quitToMainMenuButton);
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
        // TODO: the MENU scene briefly appears. Is it because of HIDE method in Client?
    }
}
