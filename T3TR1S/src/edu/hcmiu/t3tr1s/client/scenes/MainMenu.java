package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.client.buttons.MenuQuitButton;
import edu.hcmiu.t3tr1s.client.buttons.MenuStartButton;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class MainMenu extends Scene {
    private Button startButton;
    private Button quitButton;

    public MainMenu(String name) {
        super(name);

        Rectangle background = new Rectangle(new Vector3f(-80.0f, 45.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND");
        setBackground(background);

        startButton = new MenuStartButton(new Vector3f(40.0f, -10.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "START_BUTTON", "START_BUTTON_SELECTED", true);
        quitButton = new MenuQuitButton(new Vector3f(40.0f, -20.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "QUIT_BUTTON", "QUIT_BUTTON_SELECTED", false);

        buttons.add(startButton);
        buttons.add(quitButton);
    }

    @Override
    protected void handleKeyPress() {

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

        if (Input.isKeyDown(GLFW_KEY_ENTER) && keyCooled(300 * MILLISECONDS))
            handleSelection();
    }
}
