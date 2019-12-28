package edu.hcmiu.t3tr1s.client.logic;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.blocks.Shape;

public class LogicBoard {

    private int WIDTH, HEIGHT;

    private int[][] Grid;

    public LogicBoard() {
        WIDTH = 10;
        HEIGHT = 23;
        Grid = new int[23][10];
    }

    private LogicBoard(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        Grid = new int[HEIGHT][WIDTH];
    }

    public boolean isFreeSpace(Shape shape, int x, int y) {
        boolean isFree = true;

        boolean[][] shapeData = ShapeDataManager.getStateData(shape);
        for (int i = x; i < x + shapeData.length && isFree; i++)
            for (int j = y; j < y + shapeData[i - x].length && isFree; j++) {
                if ((i >= WIDTH) || (j >= HEIGHT) || (i < 0) || (j < 0)) {
                    if (shapeData[i - x][j - y])
                        isFree = false;
                }
                else if (Grid[i][j] > 0 && shapeData[i - x][j - y])
                    isFree = false;
            }

        return isFree;
    }

    public boolean freeToMove(Shape shape, Direction direction) {
        switch (direction) {
            case UP:
                return isFreeSpace(shape, shape.getX(), shape.getY() + 1);
            case DOWN:
                return isFreeSpace(shape, shape.getX(), shape.getY() - 1);
            case RIGHT:
                return isFreeSpace(shape, shape.getX() + 1, shape.getY());
            case LEFT:
                return isFreeSpace(shape, shape.getX() - 1, shape.getY());
        }
        return false;
    }

    public int[][] getGrid() {
        return Grid;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }
}
