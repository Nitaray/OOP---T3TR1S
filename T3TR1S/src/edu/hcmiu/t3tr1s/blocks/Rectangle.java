package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.VertexArray;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class Rectangle {

    private VertexArray vertexArray;

    private int shaderID;

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

    public void render() {
        ShaderManager.enableShader(shaderID);
        vertexArray.render();
        ShaderManager.disableShader(shaderID);
    }
}



