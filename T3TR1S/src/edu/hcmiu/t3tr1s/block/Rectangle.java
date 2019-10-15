package edu.hcmiu.t3tr1s.block;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.VertexArray;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class Rectangle {

    private VertexArray vertexArray;

    public Rectangle(Vector3f bottomLeft, Vector3f topLeft, Vector3f topRight, Vector3f bottomRight) {
        float[] vertices = new float[] {
                bottomLeft.x, bottomLeft.y, bottomLeft.z,
                topLeft.x,  topLeft.y, topLeft.z,
                topRight.x,  topRight.y, topRight.z,
                bottomRight.x, bottomRight.y, bottomRight.z
        };

        byte[] indices = new byte[] {
                0, 1, 2,
                2, 3, 0
        };

        float[] tc = new float[] {
                0, 1,
                0, 0,
                1, 0,
                1, 1
        };

        vertexArray = new VertexArray(vertices, indices, tc);
    }

    public void render() {
        ShaderManager.enableShader(0);
        vertexArray.render();
        ShaderManager.disableShader(0);
    }
}



