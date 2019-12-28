package edu.hcmiu.t3tr1s.client.buttons;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

public abstract class Button extends Rectangle {

    private boolean isSelected;

    private String normalTextureName;
    private String selectedTextureName;

    /**
     * Constructor for the button class
     *  @param topLeft             The 3D-coordinates for the top-left of the rectangle.
     * @param width               The width of the rectangle.
     * @param height              The height of the rectangle.
     * @param shaderName          The name of the shader to draw in this rectangle.
     * @param normalTextureName   The name of the normal texture.
     * @param selectedTextureName The name of the selected texture.
     * @param initialSelection    The initial selected value of this button.
     */


    public Button(Vector3f topLeft, float width, float height, String shaderName, String normalTextureName, String selectedTextureName, boolean initialSelection) {
        super(topLeft, width, height, shaderName, normalTextureName);
        this.normalTextureName = normalTextureName;
        this.selectedTextureName = selectedTextureName;
        this.isSelected = initialSelection;
    }

    /**
     * Set the button's selected value to true.
     */

    public void select() {
        this.isSelected = true;
    }

    /**
     * Set the button's selected value to false.
     */

    public void deSelect() {
        this.isSelected = false;
    }

    /**
     * Check if the button is selected or not.
     * @return whether the button is selected or not.
     */

    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Set the button's selected value to the given value.
     * @param selected the designated selected value.
     */

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Update the button graphics and (possibly) more.
     */

    public void update() {
        if (isSelected) {
            super.setTexture(selectedTextureName);
        }
        else {
            super.setTexture(normalTextureName);
        }
    }

    public abstract void action(Client client);
}
