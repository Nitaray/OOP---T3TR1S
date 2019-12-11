package edu.hcmiu.t3tr1s.graphics;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.VertexArray;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * A rectangle that can be rendered on screen.
 */

public class Rectangle {

    private VertexArray vertexArray;

    private int shaderID;

    private float[] vertices, tc;
    private byte[] indices;
    private float[] pos;

    /**
     * Constructor for the rectangle class
     * @param topLeft The 3D-coordinates for the top-left of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param shaderName The name of the shader to draw in this rectangle.
     */

    public Rectangle(Vector3f topLeft, float width, float height, String shaderName) {
        vertices = new float[] {
                topLeft.x, topLeft.y, topLeft.z,
                topLeft.x + width,  topLeft.y, topLeft.z,
                topLeft.x + width,  topLeft.y - height, topLeft.z,
                topLeft.x, topLeft.y - height, topLeft.z
        };

        indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        tc = new float[] {
                0, 0,
                1, 0,
                1, 1,
                0, 1
        };

        pos = new float[] {
                0, 0, 0,
                0, 0, 0,
                0, 0, 0
        };

        vertexArray = new VertexArray(vertices, indices, tc);
        shaderID = ShaderManager.getID(shaderName);
    }

    /**
     * Move the rectangle using a directional vector
     * @param v a vector of floats containing the direction
     */

    protected void translate(Vector3f v) {
        float[] newVertices = vertices.clone(); // return copy of vertices
        for (int i = 0; i < 4; i++) {
            newVertices[i * 3 + 0] += v.x;
            newVertices[i * 3 + 1] += v.y;
            newVertices[i * 3 + 2] += v.z;
        }
        vertexArray = new VertexArray(newVertices, indices, tc);
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



