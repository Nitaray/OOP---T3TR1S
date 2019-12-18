package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public class Pause extends Scene{
    private ArrayList<Button> buttons;
    private int currentButtonSelection;

    Pause(String name, Rectangle background) {
        super(name, background);

        buttons = new ArrayList<>();
        currentButtonSelection = 0;
    }
    public void addButton(Button button){
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
    void hide(Renderer renderer) {
        background.hide(renderer);
        buttons.forEach(button -> button.hide(renderer));
    }

    @Override
    void update(Client client) {
        updateSelection();
        buttons.forEach(Button::update);
        if (Input.isKeyPress(GLFW_KEY_ENTER))
            handleSelection(client);
    }

    private void updateSelection() {
        if (Input.isKeyPress(GLFW_KEY_DOWN) && currentButtonSelection < buttons.size() - 1) {
            deSelectCurrentButton(currentButtonSelection);
            currentButtonSelection++;
            selectCurrentButton(currentButtonSelection);
        }
        if (Input.isKeyPress(GLFW_KEY_UP) && currentButtonSelection > 0) {
            deSelectCurrentButton(currentButtonSelection);
            currentButtonSelection--;
            selectCurrentButton(currentButtonSelection);
        }
    }

    private void handleSelection(Client client) {
        switch (currentButtonSelection) {
            case 0:
                client.switchScene("GAME");
                break;
            case 1:
                client.switchScene("MENU");
                break;
            default:
                break;
        }
    }
    private void selectCurrentButton(int buttonID) {
        Button selectedButton = buttons.get(buttonID);
        selectedButton.select();
    }

    private void deSelectCurrentButton(int buttonID) {
        Button selectedButton = buttons.get(buttonID);
        selectedButton.deSelect();
    }
}
