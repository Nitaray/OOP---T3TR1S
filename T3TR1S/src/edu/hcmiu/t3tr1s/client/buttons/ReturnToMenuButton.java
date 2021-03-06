package edu.hcmiu.t3tr1s.client.buttons;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class ReturnToMenuButton extends Button {
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
    public ReturnToMenuButton(Vector3f topLeft, float width, float height, String shaderName, String normalTextureName, String selectedTextureName, boolean initialSelection) {
        super(topLeft, width, height, shaderName, normalTextureName, selectedTextureName, initialSelection);
    }

    @Override
    public void action() {
        Client client = Client.getInstance();
        client.switchScene("MENU");
    }
}
