package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.graphics.Shader;
import edu.hcmiu.t3tr1s.graphics.Texture;
import edu.hcmiu.t3tr1s.math.Matrix4f;
import edu.hcmiu.t3tr1s.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A manager to handle shaders.
 */

public class ShaderManager {

    private static Matrix4f projection_matrix = Matrix4f.orthographic(0, 100.0f, 0, 100.f * 9.0f / 16.0f, -1.0f, 1.0f);

    private static ArrayList<Shader> shaders = new ArrayList<>();

    private static ArrayList<Texture> textures = new ArrayList<>();

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
     * Add a texture to the current list of available textures.
     * @param texturePath a path of the texture.
     */

    private static void addTexture(String texturePath) {
        textures.add(new Texture(texturePath));
    }

    /**
     * Load all the shaders from the shader config file.
     */

    static void loadAllShader() {
        String cfg = FileUtils.loadAsString("config/shader.cfg");
        Scanner s = new Scanner(cfg);
        Scanner lineScanner;
        while (s.hasNextLine()) {
            String line = s.nextLine();
            lineScanner = new Scanner(line);
            String name = lineScanner.next();
            lineScanner.next();
            String vertPath = lineScanner.next();
            String fragPath = lineScanner.next();
            ID.put(name, shaders.size());
            addShader(vertPath, fragPath);
        }
    }

    /**
     * Load all the textures from the texture config file.
     */

    static void loadAllTexture() {
        String cfg = FileUtils.loadAsString("config/texture.cfg");
        Scanner scanner = new Scanner(cfg);
        Scanner lineScanner;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineScanner = new Scanner(line);
            String name = lineScanner.next();
            lineScanner.next();
            String texturePath = lineScanner.next();
            ID.put(name, textures.size());
            addTexture(texturePath);
        }
    }

    /**
     * Set the value for the uniform projection matrix in the shader source code.
     */

    static void setUniformAll() {
        shaders.forEach(shader -> shader.setUniformMat4f("pr_matrix", projection_matrix));
        shaders.forEach(shader -> shader.setUniform1i("tex", 0));
    }

    public static void setUniformMat4f(int shaderID, String uniformMatrixName, Matrix4f matrix) {
        if (shaderID >= 0 && shaderID < shaders.size())
            shaders.get(shaderID).setUniformMat4f(uniformMatrixName, matrix);
        else
            System.err.println("Shader ID could not be found.");
    }

    /**
     * Initialize the ShaderManager
     */

    static void init() {
        loadAllShader();
//        addTexture("T3TR1S/res/background.png"); // This is temporary, it should be added in loadAllTexture
        loadAllTexture();
        setUniformAll();
    }

    /**
     * Enable a shader using its ID (index in the shaders list).
     * @param ID the ID of the shader to be enabled.
     */

    public static void enableShader(int ID) {
        if (ID >= 0 && ID < shaders.size()) {
            Shader shader = shaders.get(ID);
            Texture texture = textures.get(ID);
            shader.enable();
            texture.bind();
        }
        else
            System.err.println("Shader ID could not be found.");
    }

    /**
     * Disable a shader using its ID (index in the shaders list).
     * @param ID the ID of the shader to be disabled.
     */

    public static void disableShader(int ID) {
        if (ID >= 0 && ID < shaders.size()) {
            Shader shader = shaders.get(ID);
            Texture texture = textures.get(ID);
            shader.disable();
            texture.unbind();
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
