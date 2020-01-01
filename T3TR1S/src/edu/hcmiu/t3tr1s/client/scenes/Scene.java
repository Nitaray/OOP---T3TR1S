package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.graphics.Showable;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;

public abstract class Scene implements Showable {
    protected Rectangle background;
    protected String name;
    protected ArrayList<Button> buttons;
    protected int currentButtonSelection;
    protected final long SECOND = 1000000000;
    protected final long MILLISECONDS = 1000000;

    private long lastKeyPress;
    private long[] lastKeysPress = new long[GLFW_KEY_LAST];

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


    public String getName() {
        return name;
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

    protected void handleSelection() {
        buttons.get(currentButtonSelection).action();
    }

    protected boolean keyCooled(long keyCoolDown) {
        long now = System.nanoTime();
        if (now - lastKeyPress > keyCoolDown) {
            lastKeyPress = now;
            return true;
        }
        return false;
    }

    protected boolean keyCooled(long keyCoolDown, int key) {
        long now = System.nanoTime();
        if (now - lastKeysPress[key] > keyCoolDown) {
            lastKeysPress[key] = now;
            return true;
        }
        return false;
    }

    protected boolean keyTriggered(int key, long keyCoolDown) {
        return (Input.isKeyDown(key) && keyCooled(keyCoolDown, key));
    }

    protected abstract void handleKeyPress();

    public abstract void show();

    public abstract void hide();

    public abstract void update();
}
