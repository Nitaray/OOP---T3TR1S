package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.graphics.VertexArray;

class Level {

    private static VertexArray background;

    private Level() {}

    static void init() {
        float[] vertices = new float[] {
                -16.0f, -9.0f, 0.0f,
                -16.0f,  9.0f, 0.0f,
                 16.0f,  9.0f, 0.0f,
                 16.0f, -9.0f, 0.0f
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

        background = new VertexArray(vertices, indices, tc);
    }

    static void render() {
        ShaderManager.BACKGROUND.enable();
        background.render();
        ShaderManager.BACKGROUND.disable();
    }
}



