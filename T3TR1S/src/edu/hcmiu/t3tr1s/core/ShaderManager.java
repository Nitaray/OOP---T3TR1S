package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.graphics.Shader;
import edu.hcmiu.t3tr1s.math.Matrix4f;

class ShaderManager {

    static Shader BACKGROUND;

    private static Matrix4f projection_matrix = Matrix4f.orthographic(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f);

    private  ShaderManager() {}

    static void loadAll() {
        BACKGROUND = new Shader("shaders/bg.vert" , "shaders/bg.frag");
    }

    static void setUniformAll() {
        BACKGROUND.enable();
        BACKGROUND.setUniformMat4f("pr_matrix", projection_matrix);
        BACKGROUND.disable();
    }

}
