package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.graphics.Shader;
import edu.hcmiu.t3tr1s.math.Matrix4f;

import java.util.ArrayList;

public class ShaderManager {

    private static Matrix4f projection_matrix = Matrix4f.orthographic(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f);

    private static ArrayList<Shader> shaders = new ArrayList<>();

    private  ShaderManager() {}

    static void loadAll() {
        Shader BACKGROUND = new Shader("shaders/bg.vert" , "shaders/bg.frag");
        shaders.add(BACKGROUND);
    }

    static void setUniformAll() {
        shaders.forEach(shader -> shader.enable());
        shaders.forEach(shader -> shader.setUniformMat4f("pr_matrix", projection_matrix));
        shaders.forEach(shader -> shader.disable());
    }

    public static void enableShader(int ID) {
        if (ID < shaders.size()) {
            Shader shader = shaders.get(ID);
            shader.enable();
        }
        else
            System.err.println("Shader ID could not be found");
    }

    public static void disableShader(int ID) {
        if (ID < shaders.size()) {
            Shader shader = shaders.get(ID);
            shader.disable();
        }
        else
            System.err.println("Shader ID could not be found");
    }

}
