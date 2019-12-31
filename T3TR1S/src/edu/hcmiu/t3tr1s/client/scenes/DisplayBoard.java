package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.blocks.display.DisplayShape;
import edu.hcmiu.t3tr1s.blocks.logic.*;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
//import edu.hcmiu.t3tr1s.client.logic.ShapeRandomizer;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.enums.ShapeType;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DisplayBoard extends Scene {
    private Rectangle backplate;

    private Vector3f topLeftBackGround = new Vector3f(-18.5f, 40.0f, 0.2f);

    private float frameThickness = 0.75f;
    private float backGroundWidth = 35.0f;
    private float backGroundHeight = 80.5f;


    private LogicBoard logicBoard;
//    private ShapeRandomizer shapeRandomizer;


    private DisplayShape currentShape;


    public DisplayBoard(String name, LogicBoard logicBoard) {
        super(name);

        this.logicBoard = logicBoard;

        Rectangle background = new Rectangle(topLeftBackGround, backGroundWidth, backGroundHeight,
                "REGULAR_RECTANGLE", "GAME_DISPLAYBOARD_BACKGROUND");
        setBackground(background);
        this.topLeftBackGround = background.getTopLeft();

        backplate = new Rectangle(new Vector3f(topLeftBackGround.x - frameThickness,
                topLeftBackGround.y + frameThickness, topLeftBackGround.z - 0.1f),
                backGroundWidth + 2 * frameThickness, backGroundHeight + 2 * frameThickness, "REGULAR_RECTANGLE",
                "BACKPLATE");
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

//    public void spawnNewBlock() {
//        LogicShape logicShape =
//    }

    @Override
    public void show() {
        background.show();
        backplate.show();
        LogicShape logicShape = new LogicShape(0, 22, logicBoard, ShapeType.T);
        currentShape = new DisplayShape(logicShape, new Vector3f(topLeftBackGround.x, topLeftBackGround.y,
                topLeftBackGround.z + 0.1f),
                backGroundWidth / 10);
        currentShape.show();
    }

    @Override
    public void hide() {
        background.hide();
        backplate.hide();
    }

    public void update() {
        if (Input.isKeyDown(GLFW.GLFW_KEY_RIGHT) && keyCooled(100 * MILLISECONDS)) {
            currentShape.move(Direction.RIGHT);
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT) && keyCooled(100 * MILLISECONDS)) {
            currentShape.move(Direction.LEFT);
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_UP) && keyCooled(100 * MILLISECONDS)) {
            currentShape.move(Direction.UP);
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_DOWN) && keyCooled(100 * MILLISECONDS)) {
            currentShape.move(Direction.DOWN);
        }
        if (Input.isKeyDown(GLFW.GLFW_KEY_R) && keyCooled(100 * MILLISECONDS))
            currentShape.rotate(Direction.CLOCKWISE);
        if (Input.isKeyDown(GLFW.GLFW_KEY_E) && keyCooled(100 * MILLISECONDS))
            currentShape.rotate(Direction.COUNTER_CLOCKWISE);
    }
}
