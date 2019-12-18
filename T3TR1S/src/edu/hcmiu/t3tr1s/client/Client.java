package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.math.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The client to script and update the game.
 * TODO: By Luu Minh Long
 */

public class Client {
    private Client() {}
    private static Client instance = new Client();
    public static Client getInstance() {
        return instance;
    }

    private boolean running;
    private Renderer renderer;

    private Scene currentScene;

    private ArrayList<Scene> scenes;
    private HashMap<String, Integer> sceneID;

    public void init(Renderer renderer, ShaderManager shaderManager) {
        running = true;
        this.renderer = renderer;

        Scene mainMenu = initMainMenu(shaderManager);

        currentScene = mainMenu;

        scenes = new ArrayList<>();
        sceneID = new HashMap<>();

        addScene(mainMenu);

        show();
    }

    private void addScene(Scene scene) {
        if (scene != null) {
            sceneID.put(scene.getName(), scenes.size());
            scenes.add(scene);
        }
        else
            throw new NullPointerException("Null scene encountered!");
    }

    private Scene initMainMenu(ShaderManager shaderManager) {
        Button startGameButton = new Button(new Vector3f(120.0f, 35.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "START_BUTTON", "START_BUTTON_SELECTED", shaderManager, true);
        Button quitGameButton = new Button(new Vector3f(120.0f, 25.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "QUIT_BUTTON", "QUIT_BUTTON_SELECTED", shaderManager, false);

        Rectangle main_menu_background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND", shaderManager);

        MainMenu mainMenu = new MainMenu("MENU", main_menu_background);

        mainMenu.addButton(startGameButton);
        mainMenu.addButton(quitGameButton);

        return  mainMenu;
    }

    /**
     * Show the client's graphic processes on the given renderer
     */

    public void show() {
        currentScene.show(renderer);
    }

    /**
     * Hide the client's graphic processes on the given renderer.
     */

    public void hide() {
        scenes.forEach(scene -> scene.hide(renderer));
    }

    /**
     * Switch the current scene.
     * @param sceneName the name of the next scene to switch to.
     */

    void switchScene(String sceneName) {
        if (sceneID.containsKey(sceneName)) {
            currentScene.hide(renderer);
            currentScene = scenes.get(sceneID.get(sceneName));
            currentScene.show(renderer);
        }
        else
            System.err.println("Scene not found! Please check scene name!");
    }

    /**
     * Update the client.
     */

    public void update() {
        currentScene.update(this);
    }

    /**
     * Check if the client is currently running or not.
     * @return whether the client is currently running.
     */

    public boolean shouldQuit() {
        return !running;
    }

    /**
     * Stop the client. Kill all client's processes.
     */

    void stop() {
        running = false;
    }
}
