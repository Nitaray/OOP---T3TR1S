package edu.hcmiu.t3tr1s.graphics;

import edu.hcmiu.t3tr1s.utils.BufferUtils;

import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

    private int count;

    private int vertexArrayObject, vertexBufferObject, indexBufferObject, textureCoords;

    public VertexArray(float[] vertices, byte[] indices, float[] textureCoords) {

        count = indices.length;

        vertexArrayObject = glGenVertexArrays();
        glBindVertexArray(vertexArrayObject);

        vertexBufferObject = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
    }
}
