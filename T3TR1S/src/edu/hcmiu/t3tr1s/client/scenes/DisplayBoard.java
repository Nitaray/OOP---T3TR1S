package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class DisplayBoard extends Scene {
    private LogicBoard logicBoard;

    private Vector3f topLeft;
    private Vector3f offSet;


    public DisplayBoard(String name, LogicBoard logicBoard, ShaderManager shaderManager) {
        super(name, shaderManager);
        this.logicBoard = logicBoard;
        this.topLeft = background.getTopLeft();
    }

//    public void drawLogicBoard() {
//        int[][] grid = logicBoard.getGrid();
//        for (int x = 0; x < grid.length; x++)
//            for (int y = 0; y < grid[x].length; y++) {
//                switch (grid[x][y]) {
//                    case
//                }
//            }
//    }

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
