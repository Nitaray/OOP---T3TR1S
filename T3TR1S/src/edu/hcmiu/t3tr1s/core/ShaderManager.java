package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.graphics.Shader;
import edu.hcmiu.t3tr1s.math.Matrix4f;
import edu.hcmiu.t3tr1s.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A manager to handle shaders.
 */

public class ShaderManager {

    private static Matrix4f projection_matrix = Matrix4f.orthographic(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f);

    private static ArrayList<Shader> shaders = new ArrayList<>();

    private static HashMap<String, Integer> ID = new HashMap<>();

    private ShaderManager() {}

    /**
     * Add a shader to the current list of available shaders.
     * @param vertexPath a path of the vertex shader source code.
     * @param fragmentPath a path of the fragment shader source code.
     */

    private static void addShader(String vertexPath, String fragmentPath) {
        shaders.add(new Shader(vertexPath, fragmentPath));
    }

    /**
     * Load all the shaders from the shader config file.
     */

    static void loadAll() {
        String cfg = FileUtils.loadAsString("config/shader.cfg");
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

    /**
     * Set the value for the uniform projection matrix in the shader source code.
     */

    static void setUniformAll() {
        shaders.forEach(Shader::enable);
        shaders.forEach(shader -> shader.setUniformMat4f("pr_matrix", projection_matrix));
        shaders.forEach(Shader::disable);
    }

    /**
     * Enable a shader using its ID (index in the shaders list).
     * @param ID the ID of the shader to be enabled.
     */

    public static void enableShader(int ID) {
        if (ID < shaders.size()) {
            Shader shader = shaders.get(ID);
            shader.enable();
        }
        else
            System.err.println("Shader ID could not be found");
    }

    /**
     * Disable a shader using its ID (index in the shaders list).
     * @param ID the ID of the shader to be disabled.
     */

    public static void disableShader(int ID) {
        if (ID < shaders.size()) {
            Shader shader = shaders.get(ID);
            shader.disable();
        }
        else
            System.err.println("Shader ID could not be found");
    }

    /**
     * Get the ID of a shader using its name.
     * @param name name of the shader.
     * @return the ID of the shader.
     */

    public static int getID(String name) {
        if (ID.get(name) != null)
            return ID.get(name);
        System.err.println("Invalid shader name");
        return 0;
    }
}