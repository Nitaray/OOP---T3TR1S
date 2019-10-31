package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.VertexArray;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * A rectangle that can be rendered on screen.
 */

public class Rectangle {

    private VertexArray vertexArray;

    private int shaderID;

    /**
     * Constructor for the rectangle class
     * @param topLeft The 3D-coordinates for the top-left of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param shaderName The name of the shader to draw in this rectangle.
     */

    public Rectangle(Vector3f topLeft, float width, float height, String shaderName) {
        float[] vertices = new float[] {
                topLeft.x, topLeft.y, topLeft.z,
                topLeft.x + width,  topLeft.y, topLeft.z,
                topLeft.x + width,  topLeft.y - height, topLeft.z,
                topLeft.x, topLeft.y - height, topLeft.z
        };

        byte[] indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        float[] tc = new float[] {
                0, 0,
                1, 0,
                1, 1,
                0, 1
        };

        vertexArray = new VertexArray(vertices, indices, tc);
        shaderID = ShaderManager.getID(shaderName);
    }

    /**
     * Renders the rectangle on screen.
     */

    public void render() {
        ShaderManager.enableShader(shaderID);
        vertexArray.render();
        ShaderManager.disableShader(shaderID);
    }
}



