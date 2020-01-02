package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.blocks.display.DisplayShape;
import edu.hcmiu.t3tr1s.blocks.logic.*;
import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.client.logic.ShapeRandomizer;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.enums.ShapeType;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class GameBoard extends Scene {
    private Rectangle backplate;

    private Vector3f topLeftBackGround = new Vector3f(-18.5f, 40.0f, 0.2f);

    private final float blockSize = 3.5f;

    private float frameThickness = 0.75f;
    private float backGroundWidth;
    private float backGroundHeight;


    private LogicBoard logicBoard;
    private ShapeRandomizer shapeRandomizer;

    private DisplayShape currentShape;
    private DisplayShape currentGhostShape;

    private Block[][] blocks;

    private int updateReceived = 0;
    private final int normalDropSpeed = 64;


    public GameBoard(String name, LogicBoard logicBoard) {
        super(name);

        this.logicBoard = logicBoard;
        backGroundHeight = logicBoard.getHEIGHT() * blockSize;
        backGroundWidth = logicBoard.getWIDTH() * blockSize;

        Rectangle background = new Rectangle(topLeftBackGround, backGroundWidth, backGroundHeight,
                "REGULAR_RECTANGLE", "GAME_DISPLAYBOARD_BACKGROUND");
        setBackground(background);

        backplate = new Rectangle(new Vector3f(topLeftBackGround.x - frameThickness,
                topLeftBackGround.y + frameThickness, topLeftBackGround.z - 0.1f),
                backGroundWidth + 2 * frameThickness, backGroundHeight + 2 * frameThickness, "REGULAR_RECTANGLE",
                "BACKPLATE");

        shapeRandomizer = new ShapeRandomizer();
        shapeRandomizer.addToBag(ShapeType.I);
        shapeRandomizer.addToBag(ShapeType.O);
        shapeRandomizer.addToBag(ShapeType.T);
        shapeRandomizer.addToBag(ShapeType.J);
        shapeRandomizer.addToBag(ShapeType.L);
        shapeRandomizer.addToBag(ShapeType.Z);
        shapeRandomizer.addToBag(ShapeType.S);

        blocks = new Block[logicBoard.getHEIGHT()][logicBoard.getWIDTH()];
    }

    public void renderLogicBoard() {
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        int linesCleared = logicBoard.lineClearUpdate();

        ShapeType[][] grid = logicBoard.getGrid();

        for (Block[] line : blocks)
            for (int x = 0; x < line.length; x++)
                if (line[x] != null) {
                    line[x].hide();
                    line[x] = null;
                }

        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] != null) {
                    Vector3f position = new Vector3f(topLeftBackGround);
                    position.x += x * blockSize;
                    position.y -= (logicBoard.getHEIGHT() - 1 - y) * blockSize;
                    position.z += 0.1f;
                    Block block = new Block(position, blockSize, shapeDataManager.getBlockTextureName(grid[y][x]));
                    blocks[y][x] = block;
                    blocks[y][x].show();
                }
            }
    }

    private boolean forceMoveShape(Direction direction) {
        boolean moveSucceed = currentShape.move(direction);
        if (moveSucceed)
            updateReceived -= normalDropSpeed;
        return moveSucceed;
    }

    private void forceSolidShape() {
        logicBoard.solidifyShape(currentShape.getLogicShape());
        currentGhostShape.hide();
        currentShape.hide();
    }

    private void softDrop(Direction direction) {
        if (currentShape.move(direction))
            updateReceived = normalDropSpeed / 2;
    }

    private void hardDrop(Direction direction) {
        updateReceived = normalDropSpeed * 23;
        while (updateReceived > 0) {
            if (!forceMoveShape(direction)) {
                updateReceived = (int)(normalDropSpeed * 1.5);
                break;
            }
        }
    }

    private void spawnGhost(int x, int y, LogicShape logicShape) {
        currentGhostShape = new DisplayShape(logicShape.clone(), new Vector3f(topLeftBackGround.x + x * blockSize,
                topLeftBackGround.y - (logicBoard.getHEIGHT() - 1 - y) * blockSize,
                topLeftBackGround.z + 0.1f), blockSize, true);
        int state = logicShape.getState();
        while (state > 0) {
            currentGhostShape.rotateShapeOnly(Direction.CLOCKWISE);
            state--;
        }
        currentGhostShape.show();
    }

    private void killGhost() {
        currentGhostShape.hide();
    }

    private void hardDropGhost(Direction direction) {
        for (int i = 0; i < logicBoard.getHEIGHT(); i++) {
            currentGhostShape.move(direction);
        }
    }

    private void updateGhostShape() {
        if (currentGhostShape != null)
            killGhost();
        int x = currentShape.getLogicShape().getX();
        int y = currentShape.getLogicShape().getY();
        spawnGhost(x, y, currentShape.getLogicShape());
        hardDropGhost(Direction.DOWN);
    }

    private void moveShape(Direction direction) {
        currentShape.move(direction);
        updateGhostShape();
    }

    private void rotateShape(Direction direction) {
        currentShape.rotate(direction);
        updateGhostShape();
    }

    private boolean spawnNewShape(int x, int y) {
        LogicShape logicShape = new LogicShape(x, y, logicBoard, shapeRandomizer.getRandom());
        if  (logicBoard.isFreeSpace(logicShape, x, y)) {
            currentShape = new DisplayShape(logicShape, new Vector3f(topLeftBackGround.x + x * blockSize,
                    topLeftBackGround.y - (logicBoard.getHEIGHT() - 1 - y) * blockSize,
                    topLeftBackGround.z + 0.1f), blockSize, false);
            currentShape.show();
            spawnGhost(x, y, logicShape);
            return true;
        }
        return false;
    }

    private void processGame() {
        updateGhostShape();
        if (updateReceived >= normalDropSpeed) {
            if (!forceMoveShape(Direction.DOWN)) {
                if (updateReceived >= 1.5 * normalDropSpeed) {
                    forceSolidShape();
                    if (!spawnNewShape(logicBoard.getWIDTH() / 2 - 2, logicBoard.getHEIGHT() - 1)) {
                        endGame();
                    }
                    renderLogicBoard();
                    updateReceived = 0;
                }
            }
        }
    }

    private void startGame() {
        shapeRandomizer.randomizeBag();
        spawnNewShape(logicBoard.getWIDTH() / 2 - 2, logicBoard.getHEIGHT() - 1);
    }

    private void endGame() {
        logicBoard.resetBoard();
        Client client = Client.getInstance();
        client.switchScene("MENU");
    }

    @Override
    public void update() {
        updateReceived++;
        handleKeyPress();
        processGame();
    }

    @Override
    public void show() {
        background.show();
        backplate.show();
        startGame();
    }

    @Override
    public void hide() {
        background.hide();
        backplate.hide();
    }

    @Override
    protected void handleKeyPress() {
        if (keyTriggered(GLFW.GLFW_KEY_RIGHT, 100 * MILLISECONDS)) {
            moveShape(Direction.RIGHT);
        }
        if (keyTriggered(GLFW.GLFW_KEY_LEFT, 100 * MILLISECONDS)) {
            moveShape(Direction.LEFT);
        }
        if (keyTriggered(GLFW.GLFW_KEY_DOWN, 50 * MILLISECONDS)) {
            softDrop(Direction.DOWN);
        }
        if (keyTriggered(GLFW.GLFW_KEY_SPACE, 200 * MILLISECONDS)) {
            hardDrop(Direction.DOWN);
        }
        if (keyTriggered(GLFW.GLFW_KEY_R, 200 * MILLISECONDS)) {
            rotateShape(Direction.CLOCKWISE);
        }
        if (keyTriggered(GLFW.GLFW_KEY_E, 200 * MILLISECONDS)) {
            rotateShape(Direction.COUNTER_CLOCKWISE);
        }
        if (keyTriggered(GLFW.GLFW_KEY_T, 200 * MILLISECONDS)) {
            logicBoard.solidifyShape(currentShape.getLogicShape()); // For debug purposes.
        }
    }
}
