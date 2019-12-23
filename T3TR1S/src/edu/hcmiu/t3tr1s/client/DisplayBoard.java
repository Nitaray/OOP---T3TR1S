package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;

public class DisplayBoard extends Scene {
    private LogicBoard logicBoard;
    private float logicX, logicY, logicWidth, logicHeight;

    DisplayBoard(String name, Rectangle background, LogicBoard logicBoard) {
        super(name, background);
        this.logicBoard = logicBoard;
   //     logicX = background.
    }

    void drawLogicBoard() {

    }

    @Override
    void show(Renderer renderer) {
        background.show(renderer);
    }

    @Override
    void hide(Renderer renderer) {

    }

    @Override
    void update(Client client) {

    }
}
