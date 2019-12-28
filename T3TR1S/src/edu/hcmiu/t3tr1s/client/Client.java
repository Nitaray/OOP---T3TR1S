package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.client.scenes.DisplayBoard;
import edu.hcmiu.t3tr1s.client.scenes.MainMenu;
import edu.hcmiu.t3tr1s.client.scenes.Scene;
import edu.hcmiu.t3tr1s.core.Renderer;

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

    private LogicBoard logicBoard;

    public void init() {
        running = true;

        this.renderer = Renderer.getInstance();

        logicBoard = new LogicBoard();

        Scene mainMenu = new MainMenu("MENU");

        Scene gameScene = new DisplayBoard("GAME", logicBoard);
        // TEST MOVING

        currentScene = mainMenu;

        scenes = new ArrayList<>();
        sceneID = new HashMap<>();

        addScene(mainMenu);
        addScene(gameScene);

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

    /**
     * Show the client's graphic processes on the given renderer
     */

    public void show() {
        currentScene.show();
    }

    /**
     * Hide the client's graphic processes on the given renderer.
     */

    public void hide() {
        scenes.forEach(scene -> scene.hide());
    }

    /**
     * Switch the current scene.
     * @param sceneName the name of the next scene to switch to.
     */

    public void switchScene(String sceneName) {
        if (sceneID.containsKey(sceneName)) {
            currentScene.hide();
            currentScene = scenes.get(sceneID.get(sceneName));
            currentScene.show();
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

    public void stop() {
        running = false;
    }
}
