package edu.hcmiu.t3tr1s.client.logic;

import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.blocks.logic.LogicShape;
import edu.hcmiu.t3tr1s.enums.ShapeType;

public class LogicBoard {

    private int WIDTH, HEIGHT;

    private ShapeType[][] Grid;

    public LogicBoard() {
        WIDTH = 10;
        HEIGHT = 23;
        Grid = new ShapeType[23][10];
    }

    private LogicBoard(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        Grid = new ShapeType[HEIGHT][WIDTH];
    }

    public boolean isFreeSpace(LogicShape logicShape, int x, int y) {
        boolean isFree = true;

        boolean[][] shapeData = ShapeDataManager.getInstance().getStateData(logicShape);
        for (int i = y; i > y - shapeData.length && isFree; i--)
            for (int j = x; j < x + shapeData[y - i].length && isFree; j++) {
                if ((j >= WIDTH) || (i >= HEIGHT) || (i < 0) || (j < 0)) {
                    if (shapeData[y - i][j - x])
                        isFree = false;
                }
                else if (Grid[i][j] != null && shapeData[y - i][j - x])
                    isFree = false;
            }

        return isFree;
    }

    public boolean freeToMove(LogicShape logicShape, Direction direction) {
        switch (direction) {
            case UP:
                return isFreeSpace(logicShape, logicShape.getX(), logicShape.getY() + 1);
            case DOWN:
                return isFreeSpace(logicShape, logicShape.getX(), logicShape.getY() - 1);
            case RIGHT:
                return isFreeSpace(logicShape, logicShape.getX() + 1, logicShape.getY());
            case LEFT:
                return isFreeSpace(logicShape, logicShape.getX() - 1, logicShape.getY());
        }
        return false;
    }

    public void solidifyShape(LogicShape logicShape) {
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        int x = logicShape.getX();
        int y = logicShape.getY();
        boolean[][] shapeData = shapeDataManager.getStateData(logicShape);
        for (int i = y; i > y - shapeData.length; i--)
            for (int j = x; j < x + shapeData[y - i].length; j++) {
                if (shapeData[i - y][j - x])
                    Grid[y][x] = logicShape.getType();
            }
    }

    public ShapeType[][] getGrid() {
        return Grid;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
