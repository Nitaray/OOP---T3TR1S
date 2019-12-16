package edu.hcmiu.t3tr1s.graphics;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.math.Matrix4f;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * A rectangle that can be rendered on screen.
 */

public class Rectangle {

    private VertexArray vertexArray;

    private ShaderManager shaderManager;

    private int shaderID;
    private int textureID;

    private float[] vertices, tc;
    private byte[] indices;
    private Matrix4f model_mat;
    private Matrix4f position_mat;
    private Matrix4f rotation_mat;

    /**
     * Constructor for the rectangle class
     * @param topLeft The 3D-coordinates for the top-left of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param shaderName The name of the shader to draw in this rectangle.
     */

    public Rectangle(Vector3f topLeft, float width, float height, String shaderName, String textureName, ShaderManager shaderManager) {
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

        model_mat = Matrix4f.identity();
        position_mat = Matrix4f.translate(new Vector3f(0, 0, 0));
        rotation_mat = Matrix4f.rotate(0);

        this.shaderManager = shaderManager;

        vertexArray = new VertexArray(vertices, indices, tc);
        shaderID = shaderManager.getShaderID(shaderName);
        textureID = shaderManager.getTextureID(textureName);
    }

    /**
     * Move the rectangle using a directional vector
     * @param v a vector of floats containing the direction
     */

    public void translate(Vector3f v) {
        position_mat.add_translation(v);
    }

    /**
     * Update the model matrix of the rectangle.
     */

    private void update() {
        model_mat = position_mat.multiply(rotation_mat);
    }

    /**
     * Renders the rectangle on screen.
     */

    public void render() {
        shaderManager.enableShader(shaderID, textureID);
        update();
        shaderManager.setUniformMat4f(shaderID, "model_matrix", model_mat);
        vertexArray.render();
        shaderManager.disableShader(shaderID, textureID);
    }
}



