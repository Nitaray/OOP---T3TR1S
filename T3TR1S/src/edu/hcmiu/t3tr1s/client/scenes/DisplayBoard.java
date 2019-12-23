package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class DisplayBoard extends Scene {
    private LogicBoard logicBoard;

    private Vector3f topLeft;
    private Vector3f offSet;


    public DisplayBoard(String name, Rectangle background, LogicBoard logicBoard) {
        super(name, background);
        this.logicBoard = logicBoard;
        this.topLeft = background.getTopLeft();
    }

    public void drawLogicBoard() {

    }

    @Override
    public void show(Renderer renderer) {
        background.show(renderer);
    }

    @Override
    public void hide(Renderer renderer) {
    }

    @Override
    public void update(Client client) {
    }
}
