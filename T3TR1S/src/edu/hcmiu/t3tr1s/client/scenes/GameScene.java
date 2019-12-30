package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class GameScene extends Scene {
    private DisplayBoard displayBoard;
    private LogicBoard logicBoard;

    public GameScene(String name) {
        super(name);

        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND");
        setBackground(background);

        logicBoard = new LogicBoard();

        displayBoard = new DisplayBoard("BOARD", logicBoard);
    }

    @Override
    public void show() {
        background.show();
        displayBoard.show();
    }

    @Override
    public void hide() {
        background.hide();
        displayBoard.hide();
    }

    @Override
    public void update() {
        displayBoard.update();
    }
}
