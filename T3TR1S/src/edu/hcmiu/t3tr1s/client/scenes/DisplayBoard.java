package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.blocks.display.DisplayShape;
import edu.hcmiu.t3tr1s.blocks.logic.LogicShape;
import edu.hcmiu.t3tr1s.blocks.logic.TLogicShape;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DisplayBoard extends Scene {
    private LogicBoard logicBoard;

    private Vector3f topLeft;

    private LogicShape logicShape;
    private DisplayShape displayShape;


    public DisplayBoard(String name, LogicBoard logicBoard) {
        super(name);
        this.logicBoard = logicBoard;
        Rectangle background = new Rectangle(new Vector3f(-18.5f, 40.0f, 0.1f), 35.0f, 80.5f,
                "REGULAR_RECTANGLE", "GAME_DISPLAYBOARD_BACKGROUND");
        setBackground(background);
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
    public void show() {
        background.show();
        logicShape = new TLogicShape(0, 22, logicBoard);
        displayShape = new DisplayShape(logicShape, new Vector3f(topLeft.x, topLeft.y, 0.5f), 3.5f);
        displayShape.show();
    }

    @Override
    public void hide() {
        background.hide();
    }

    public void update() {
        if(Input.isKeyDown(GLFW.GLFW_KEY_RIGHT) && keyCooled(100 * MILLISECONDS)){
            displayShape.move(Direction.RIGHT);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_LEFT) && keyCooled(100 * MILLISECONDS)){
            displayShape.move(Direction.LEFT);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_UP) && keyCooled(100 * MILLISECONDS)){
            displayShape.move(Direction.UP);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_DOWN) && keyCooled(100 * MILLISECONDS)){
            displayShape.move(Direction.DOWN);
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_R) && keyCooled(100 * MILLISECONDS))
            displayShape.rotate(Direction.CLOCKWISE);
        if (Input.isKeyDown(GLFW.GLFW_KEY_Q) && keyCooled(100 * MILLISECONDS))
            displayShape.rotate(Direction.COUNTER_CLOCKWISE);
    }
}
