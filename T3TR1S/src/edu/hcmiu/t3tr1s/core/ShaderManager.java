package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.graphics.Shader;
import edu.hcmiu.t3tr1s.math.Matrix4f;
import edu.hcmiu.t3tr1s.utils.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShaderManager {

    private static Matrix4f projection_matrix = Matrix4f.orthographic(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f);

    private static ArrayList<Shader> shaders = new ArrayList<>();

    private static HashMap<String, Integer> ID = new HashMap<>();

    private ShaderManager() {}

    static void addShader(String vertexPath, String fragmentPath) {
        shaders.add(new Shader(vertexPath, fragmentPath));
    }

    static void loadAll() {
        String cfg = FileUtils.loadAsString("T3TR1S/config/shader.cfg");
        Scanner s = new Scanner(cfg);
        Scanner lineScanner;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            lineScanner = new Scanner(line);
            System.out.println(line);
            String name = lineScanner.next();
            lineScanner.next();
            String vertPath = lineScanner.next();
            String fragPath = lineScanner.next();
            ID.put(name, shaders.size());
            addShader(vertPath, fragPath);
        }
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

    public static int getID(String name) {
        if (ID.get(name) != null)
            return ID.get(name);
        System.err.println("Invalid shader name");
        return 0;
    }
}
