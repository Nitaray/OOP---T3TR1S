package edu.hcmiu.t3tr1s.graphics;

import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.math.Matrix4f;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * A rectangle that can be rendered on screen.
 */

public class Rectangle implements Showable{

    private VertexArray vertexArray;

    private ShaderManager shaderManager;

    private int shaderID;
    private int textureID;

    private float WIDTH;
    private float HEIGHT;

    private float[] vertices, tc;
    private byte[] indices;

    private Vector3f topLeft;

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

    public Rectangle(Vector3f topLeft, float width, float height, String shaderName, String textureName) {

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

        WIDTH = width;
        HEIGHT = height;

        this.topLeft = topLeft;

        model_mat = Matrix4f.identity();
        position_mat = Matrix4f.translate(new Vector3f(0, 0, 0));
        rotation_mat = Matrix4f.rotate(0);

        this.shaderManager = ShaderManager.getInstance();

        vertexArray = new VertexArray(vertices, indices, tc);
        shaderID = this.shaderManager.getShaderID(shaderName);
        textureID = this.shaderManager.getTextureID(textureName);
    }

    public Vector3f getTopLeft() {
        return topLeft;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public float getWIDTH() {
        return WIDTH;
    }

    /**
     * Move the rectangle using a directional vector
     * @param v a vector of floats containing the direction
     */

    protected void translate(Vector3f v) {
        position_mat.add_translation(v);
    }

    /**
     * Set the rectangle to a new topLeft position.
     * @param newLocation the new position of the top-left corner of the rectangle.
     */

    protected void teleport(Vector3f newLocation) {
        Vector3f currentLocation = new Vector3f(position_mat.elements[0 + 3 * 4], position_mat.elements[1 + 3 * 4], position_mat.elements[2 + 3 * 4]);
        Vector3f differenceVector = newLocation.subtract(currentLocation);
        position_mat.add_translation(differenceVector);
    }

    /**
     * Set a new texture to the rectangle.
     * @param textureName the name of the texture to be set.
     */

    protected void setTexture(String textureName) {
        textureID = shaderManager.getTextureID(textureName);
    }

    /**
     * Update the model matrix of the rectangle.
     */

    protected void updateModel() {
        model_mat = position_mat.multiply(rotation_mat);
    }

    /**
     * Renders the rectangle on screen.
     */

    public void render() {
        shaderManager.enableShader(shaderID, textureID);
        updateModel();
        shaderManager.setUniformMat4f(shaderID, "model_matrix", model_mat);
        vertexArray.render();
        shaderManager.disableShader(shaderID, textureID);
    }

    public void show() {
        Renderer renderer = Renderer.getInstance();
        renderer.addOnScreenObject(this);
    }

    public void hide() {
        Renderer renderer = Renderer.getInstance();
        try {
            renderer.removeOnScreenObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



