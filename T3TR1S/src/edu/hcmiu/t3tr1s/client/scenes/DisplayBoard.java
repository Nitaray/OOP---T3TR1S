package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.blocks.Shape;
import edu.hcmiu.t3tr1s.blocks.TShape;
import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DisplayBoard extends Scene {
    private LogicBoard logicBoard;

    private Vector3f topLeft;
    private Vector3f offSet;

    private Shape shape;


    public DisplayBoard(String name, LogicBoard logicBoard) {
        super(name);
        this.logicBoard = logicBoard;
        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND");
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
        shape = new TShape(logicBoard);
        shape.show();
    }

    @Override
    public void hide() {
        background.hide();
        shape.hide();
    }

    @Override
    public void update(Client client) {
        if(Input.isKeyDown(GLFW.GLFW_KEY_RIGHT) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.RIGHT);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_LEFT) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.LEFT);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_UP) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.UP);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_DOWN) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.DOWN);
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_R) && keyCooled(300 * MILLISECONDS))
            shape.rotate(Direction.CLOCKWISE, true);
    }
}
