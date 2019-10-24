package edu.hcmiu.t3tr1s.graphics;

import edu.hcmiu.t3tr1s.utils.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * A class used to draw objects with openGL using array of vertices.
 */

public class VertexArray {

    private int count;

    private int vertexArrayObject, vertexBufferObject, indexBufferObject, textureBufferObject;

    /**
     * Constructor of the VertexArray class.
     * @param vertices An array of floats containing the coordinates for the vertices.
     * @param indices An array of bytes containing the indices of each triangle being drawn.
     * @param textureCoords An array of floats containing the coordinates of the texture corresponding to the vertices.
     */

    public VertexArray(float[] vertices, byte[] indices, float[] textureCoords) {

        count = indices.length;

        vertexArrayObject = glGenVertexArrays();
        glBindVertexArray(vertexArrayObject);

        vertexBufferObject = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.VERTEX_ATTRIBUTE, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(Shader.VERTEX_ATTRIBUTE);

        textureBufferObject = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, textureBufferObject);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoords), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.TEXTURE_COORD_ATTRIBUTE , 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(Shader.TEXTURE_COORD_ATTRIBUTE);

        indexBufferObject = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBufferObject);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private void bind() {
        glBindVertexArray(vertexArrayObject);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBufferObject);
    }

    private void unbind() {
        glBindVertexArray(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    private void draw() {
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
    }

    /**
     * Render the vertex array.
     */

    public void render() {
        bind();
        draw();
        unbind();
    }
}
