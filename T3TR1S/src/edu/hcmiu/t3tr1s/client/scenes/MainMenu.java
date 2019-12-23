package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class MainMenu extends Scene {
    private ArrayList<Button> buttons;

    private int currentButtonSelection;

    public MainMenu(String name, Rectangle background) {
        super(name, background);
        buttons = new ArrayList<>();
        currentButtonSelection = 0;
    }

    public void addButton(Button button) {
        if (button != null)
            buttons.add(button);
        else
            throw new NullPointerException("Null button encountered!");
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

    private void selectCurrentButton(int buttonID) {
        Button selectedButton = buttons.get(buttonID);
        selectedButton.select();
    }

    private void deSelectCurrentButton(int buttonID) {
        Button selectedButton = buttons.get(buttonID);
        selectedButton.deSelect();
    }

    private void updateSelection() {
        if (Input.isKeyDown(GLFW_KEY_DOWN) && currentButtonSelection < buttons.size() - 1) {
            deSelectCurrentButton(currentButtonSelection);
            currentButtonSelection++;
            selectCurrentButton(currentButtonSelection);
        }
        if (Input.isKeyDown(GLFW_KEY_UP) && currentButtonSelection > 0) {
            deSelectCurrentButton(currentButtonSelection);
            currentButtonSelection--;
            selectCurrentButton(currentButtonSelection);
        }
    }

    private void handleSelection(Client client) {
        buttons.get(currentButtonSelection).action(client);
    }

    @Override
    public void update(Client client) {
        updateSelection();
        buttons.forEach(Button::update);

        if (Input.isKeyDown(GLFW_KEY_ENTER))
            handleSelection(client);
    }
}
