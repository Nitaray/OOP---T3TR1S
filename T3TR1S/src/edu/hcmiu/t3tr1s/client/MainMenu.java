package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

class MainMenu extends Scene {
    private Rectangle logo;

    private ArrayList<Button> buttons;

    private int currentButtonSelection;

    MainMenu(Rectangle background, Rectangle logo) {
        this.background = background;
        this.logo = logo;
        buttons = new ArrayList<>();
        currentButtonSelection = 0;
    }

    public void addButton(Button button) {
        if (button != null)
            buttons.add(button);
        else
            throw new NullPointerException("Null button encountered!");
    }

    public void setLogo(Rectangle logo) {
        if (logo != null)
            this.logo = logo;
        else
            throw new NullPointerException("Null logo encountered!");
    }

    @Override
    public void show(Renderer renderer) {
        background.show(renderer);
        logo.show(renderer);

        buttons.forEach(button -> button.show(renderer));
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

    @Override
    public void update() {
        updateSelection();
        buttons.forEach(Button::update);
    }
}
