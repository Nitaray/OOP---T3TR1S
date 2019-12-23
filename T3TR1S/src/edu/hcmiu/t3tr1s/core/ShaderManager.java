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

    private static ShaderManager instance = new ShaderManager();

    public static ShaderManager getInstance(Matrix4f projection_matrix) {
        instance.projection_matrix = projection_matrix;
        return instance;
    }

    public static ShaderManager getInstance(){
        return instance;
    }

    private Matrix4f projection_matrix = Matrix4f.orthographic(0, 100.0f, 0, 100.f * 9.0f / 16.0f, -1.0f, 1.0f);

    private ArrayList<Shader> shaders = new ArrayList<>();

    private ArrayList<Texture> textures = new ArrayList<>();

    private HashMap<String, Integer> shadersID = new HashMap<>();

    private HashMap<String, Integer> texturesID = new HashMap<>();

    private ShaderManager() {}

    /**
     * Add a shader to the current list of available shaders.
     * @param vertexPath a path of the vertex shader source code.
     * @param fragmentPath a path of the fragment shader source code.
     */

    private void addShader(String vertexPath, String fragmentPath) {
        shaders.add(new Shader(vertexPath, fragmentPath));
    }

    /**
     * Add a texture to the current list of available textures.
     * @param texturePath a path of the texture.
     */

    private void addTexture(String texturePath) {
        textures.add(new Texture(texturePath));
    }

    /**
     * Load all the shaders from the shader config file.
     */

    void loadAllShader() {
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
            shadersID.put(name, shaders.size());
            addShader(vertPath, fragPath);
        }
    }

    /**
     * Load all the textures from the texture config file.
     */

    void loadAllTexture() {
        String cfg = FileUtils.loadAsString("config/texture.cfg");
        Scanner scanner = new Scanner(cfg);
        Scanner lineScanner;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lineScanner = new Scanner(line);
            String name = lineScanner.next();
            lineScanner.next();
            String texturePath = lineScanner.next();
            texturesID.put(name, textures.size());
            addTexture(texturePath);
        }
    }

    /**
     * Set the value for the uniform projection matrix in the shader source code.
     */

    void setUniformAll() {
        shaders.forEach(shader -> shader.setUniformMat4f("pr_matrix", projection_matrix));
        shaders.forEach(shader -> shader.setUniform1i("tex", 0));
    }

    public void setUniformMat4f(int shaderID, String uniformMatrixName, Matrix4f matrix) {
        if (shaderID >= 0 && shaderID < shaders.size())
            shaders.get(shaderID).setUniformMat4f(uniformMatrixName, matrix);
        else
            System.err.println("Shader" +  shaderID + "could not be found.");
    }

    /**
     * Initialize the ShaderManager
     */

    void init() {
        loadAllShader();
        loadAllTexture();
        setUniformAll();
    }

    /**
     * Enable a shader using its shadersID (index in the shaders list).
     * @param shaderID the shadersID of the shader to be enabled.
     */

    public void enableShader(int shaderID, int textureID) {
        if (shaderID >= 0 && shaderID < shaders.size()) {
            Shader shader = shaders.get(shaderID);
            Texture texture = textures.get(textureID);
            shader.enable();
            texture.bind();
        }
        else
            System.err.println("Shader shadersID could not be found.");
    }

    /**
     * Disable a shader using its shadersID (index in the shaders list).
     * @param shaderID the shadersID of the shader to be disabled.
     */

    public void disableShader(int shaderID, int textureID) {
        if (shaderID >= 0 && shaderID < shaders.size()) {
            Shader shader = shaders.get(shaderID);
            Texture texture = textures.get(textureID);
            shader.disable();
            texture.unbind();
        }
        else
            System.err.println("Shader shadersID could not be found");
    }

    /**
     * Get the shadersID of a shader using its name.
     * @param name name of the shader.
     * @return the shadersID of the shader.
     */

    public int getShaderID(String name) {
        if (shadersID.get(name) != null)
            return shadersID.get(name);
        System.err.println("Invalid shader name.");
        return 0;
    }

    public int getTextureID(String name) {
        if (texturesID.get(name) != null)
            return texturesID.get(name);
        System.err.println("Invalid texture name.");
        return 0;
    }
}
