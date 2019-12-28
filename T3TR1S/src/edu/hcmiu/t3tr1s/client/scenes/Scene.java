package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.graphics.Showable;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_DOWN;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_UP;

public abstract class Scene implements Showable {
    protected Rectangle background;
    protected String name;
    protected ArrayList<Button> buttons;
    protected int currentButtonSelection;
    protected final long SECOND = 1000000000;
    protected final long MILLISECONDS = 1000000;

    private long lastKeyPress;

    public Scene(String name) {
        this.name = name;
        this.buttons = new ArrayList<>();
        this.currentButtonSelection = 0;
        this.lastKeyPress = System.nanoTime();
    };

    public void setBackground(Rectangle background) {
        if (background != null)
            this.background = background;
        else
            throw new NullPointerException("Null background encountered!");
    }


    private void selectCurrentButton(int buttonID) {
        Button selectedButton = buttons.get(buttonID);
        selectedButton.select();
    }

    private void deSelectCurrentButton(int buttonID) {
        Button selectedButton = buttons.get(buttonID);
        selectedButton.deSelect();
    }

    protected void updateSelection() {
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

    protected void handleSelection(Client client) {
        buttons.get(currentButtonSelection).action(client);
    }
    public String getName() {
        return name;
    }

    protected boolean keyCooled(long keyCoolDown) {
        long now = System.nanoTime();
        if (now - lastKeyPress > keyCoolDown) {
            lastKeyPress = now;
            return true;
        }
        return false;
    }

    public abstract void show();

    public abstract void hide();

    public abstract void update(Client client);
}
