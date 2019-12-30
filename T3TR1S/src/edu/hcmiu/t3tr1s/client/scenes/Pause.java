package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.client.buttons.MenuQuitButton;
import edu.hcmiu.t3tr1s.client.buttons.MenuStartButton;
import edu.hcmiu.t3tr1s.client.buttons.ReturnToMenuButton;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class Pause extends Scene{
    private Button continueGameButton;
    private Button quitGameButton;

    public Pause(String name) {
        super(name);

        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND");
        setBackground(background);

        continueGameButton = new MenuStartButton(new Vector3f(120.0f, 35.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "START_BUTTON", "START_BUTTON_SELECTED", true);
        quitGameButton = new ReturnToMenuButton(new Vector3f(120.0f, 25.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "QUIT_BUTTON", "QUIT_BUTTON_SELECTED", false);
    }
    public void addButton(Button button){
        if (button != null)
            buttons.add(button);
        else
            throw new NullPointerException("Null button encountered!");
    }

    @Override
    public void show() {
        background.show();
        buttons.forEach(button -> button.show());
    }

    @Override
    public void hide() {
        background.hide();
        buttons.forEach(button -> button.hide());
    }

    @Override
    public void update() {
        updateSelection();
        buttons.forEach(Button::update);
        if (Input.isKeyDown(GLFW_KEY_ENTER))
            handleSelection();
    }
}
