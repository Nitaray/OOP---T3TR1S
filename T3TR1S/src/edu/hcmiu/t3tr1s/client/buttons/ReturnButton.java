package edu.hcmiu.t3tr1s.client.buttons;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class ReturnButton extends Button{
    /**
     * Constructor for the button class
     *
     * @param topLeft             The 3D-coordinates for the top-left of the rectangle.
     * @param width               The width of the rectangle.
     * @param height              The height of the rectangle.
     * @param shaderName          The name of the shader to draw in this rectangle.
     * @param normalTextureName   The name of the normal texture.
     * @param selectedTextureName The name of the selected texture.
     * @param shaderManager       The shader manager to handle shaders' names.
     * @param initialSelection    The initial selected value of this button.
     */
    public ReturnButton(Vector3f topLeft, float width, float height, String shaderName, String normalTextureName, String selectedTextureName, ShaderManager shaderManager, boolean initialSelection) {
        super(topLeft, width, height, shaderName, normalTextureName, selectedTextureName, shaderManager, initialSelection);
    }

    @Override
    public void action(Client client) {
        client.switchScene("MENU");
    }
}
