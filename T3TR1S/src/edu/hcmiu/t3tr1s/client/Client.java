package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * The client to script and update the game.
 * TODO: By Luu Minh Long
 */

public class Client {
    private Client() {}

    private static Client instance = new Client();

    public static Client getInstance(Renderer renderer) {
        instance.renderer = renderer;
        return instance;
    }

    private Renderer renderer;

    public void test() {
        renderer = Renderer.getInstance();
        Rectangle r = new Rectangle(new Vector3f(0, 100.0f * 9.0f / 16.0f, 0.0f), 100.0f, 100.0f * 9.0f / 16.0f, "BACKGROUND");
        Rectangle rt = new Rectangle(new Vector3f(30, 90.0f * 9.0f / 16.0f, 0.1f), 40.0f, 40.0f * 285.0f / 412.0f, "TETRIS");
        renderer.addOnScreenObject(rt);
        renderer.addOnScreenObject(r);
        /**
         * Menu: Tetris title, Start Game, High Score, Quit Game, and Selecting arrow (SA) images
         * Initialize @param choose
         * If UP/DOWN button is pressed, remove SA and render it again at the selected image. @param choose++ or --
         * Start game => Remove all images, then:
         * Render board, blocks, score, and Pause, Quit with SA
         * If ESC is pressed => Pause game, render Continue, Quit
         * If user choose Continue => Continue game
         * If user choose Quit => Quit game, remove all, re-render main menu (no need to ask for Are you sure I think)
         * 
         */

    }
}
