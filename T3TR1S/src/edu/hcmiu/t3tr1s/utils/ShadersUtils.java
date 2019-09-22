package edu.hcmiu.t3tr1s.utils;

import static org.lwjgl.opengl.GL20.*;

public class ShadersUtils {

    private ShadersUtils() {

    }

    public static int load(String vertPath, String fragPath) {

        String vert = FileUtils.loadAsString(vertPath);

        String frag = FileUtils.loadAsString(fragPath);

        return create(vert, frag);
    }

    public static int create(String vert, String frag) {

        int program = glCreateProgram();

        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        glCompileShader(vertID);
        if (glGetShaderi(vertID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.print("Vertex shader failed to compile!");
            return -1;
        }

        glCompileShader(fragID);
        if (glGetShaderi(fragID, GL_COMPILE_STATUS) == GL_FALSE) {
            System.err.print("Fragment shader failed to compile!");
            return -1;
        }

        glAttachShader(program, vertID);
        glAttachShader(program, fragID);

        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertID);
        glDeleteShader(fragID);

        return program;
    }
}
