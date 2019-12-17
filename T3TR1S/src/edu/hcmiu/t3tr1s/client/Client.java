package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.math.Vector3f;

import java.util.ArrayList;

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

    private Button startGameButton;
    private Button quitGameButton;

    private Rectangle main_menu_background;
    private Rectangle main_menu_logo;

    private Scene currentScene;

    private ArrayList<Scene> scenes;

    public void init(Renderer renderer, ShaderManager shaderManager) {
        running = true;

        startGameButton = new Button(new Vector3f(40, 25.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 209.0f / 1563.0f,
                "REGULAR_RECTANGLE", "START", "QUIT", shaderManager, false);
        quitGameButton = new Button(new Vector3f(40, 15.0f * 9.0f / 16.0f, 0.1f), 20.0f, 20.0f * 255.0f / 1431.0f,
                "REGULAR_RECTANGLE", "QUIT", "START", shaderManager, false);

        main_menu_background = new Rectangle(new Vector3f(0, 100.0f * 9.0f / 16.0f, 0.0f), 100.0f, 100.0f * 9.0f / 16.0f,
                "REGULAR_RECTANGLE", "BACKGROUND", shaderManager);
        main_menu_logo = new Rectangle(new Vector3f(30, 90.0f * 9.0f / 16.0f, 0.1f), 40.0f, 40.0f * 285.0f / 412.0f,
                "REGULAR_RECTANGLE", "TETRIS", shaderManager);

        Scene mainMenu = new MainMenu(main_menu_background, main_menu_logo);

        ((MainMenu) mainMenu).addButton(startGameButton);
        ((MainMenu) mainMenu).addButton(quitGameButton);

        scenes = new ArrayList<>();

        scenes.add(mainMenu);

        currentScene = mainMenu;

        show(renderer);
    }

    /**
     * Show the client's graphic processes on the given renderer
     * @param renderer the renderer to draw the client's graphic processes.
     */

    public void show(Renderer renderer) {
        currentScene.show(renderer);
    }

    /**
     * Hide the client's graphic processes on the given renderer.
     * @param renderer the renderer to hide the client's graphic processes.
     */

    public void hide(Renderer renderer) {
        main_menu_background.hide(renderer);
        main_menu_logo.hide(renderer);

        startGameButton.hide(renderer);
        quitGameButton.hide(renderer);
    }

    public void update() {
        currentScene.update();
    }

    public boolean shouldQuit() {
        return !running;
    }
}
