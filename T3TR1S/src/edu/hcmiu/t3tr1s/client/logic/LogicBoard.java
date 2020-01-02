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

    public ShapeType[][] getGrid() {
        return Grid;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
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
                if (shapeData[y - i][j - x])
                    Grid[i][j] = logicShape.getType();
            }
    }

    public int lineClearUpdate() {
        int linesCleared = 0;

        for (int y = 0; y < HEIGHT; y++) {
            int blockOnLine = 0;
            for (int x = 0; x < WIDTH; x++) {
                if (Grid[y][x] != null)
                    blockOnLine++;
            }
            if (blockOnLine == WIDTH) {
                for (int x = 0; x < WIDTH; x++)
                    Grid[y][x] = null;
                linesCleared++;
            }
        }

        if (linesCleared > 0) {
            int emptyLinesBelow = 0;
            for (int y = 0; y < HEIGHT; y++) {
                boolean lineCleared = true;
                emptyLinesBelow++;

                for (int x = 0; x < WIDTH && lineCleared; x++) {
                    if (Grid[y][x] != null) {
                        lineCleared = false;
                        emptyLinesBelow--;
                    }
                }
//            if (lineCleared && y < HEIGHT - linesCleared) {
//                for (int x = 0; x < WIDTH; x++) {
//                    Grid[y][x] = Grid[y + linesCleared][x];
//                    Grid[y + linesCleared][x] = null;
//                }
//            }
                if (!lineCleared && emptyLinesBelow > 0) {
                    for (int x = 0; x < WIDTH; x++) {
                        Grid[y - emptyLinesBelow][x] = Grid[y][x];
                        Grid[y][x] = null;
                    }
                }
            }
        }
        return linesCleared;
    }

    public void resetBoard() {
        Grid = new ShapeType[HEIGHT][WIDTH];
    }
}
