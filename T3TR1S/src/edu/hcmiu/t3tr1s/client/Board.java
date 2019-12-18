package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.blocks.Shape;
import edu.hcmiu.t3tr1s.core.ShapeDataManager;

import java.util.ArrayList;

public class Board {

    protected int WIDTH, HEIGHT;

    protected ArrayList<Block> blocks = new ArrayList<>();

    protected boolean Grid[][];

    protected Board() {
        WIDTH = 10;
        HEIGHT = 23;
        Grid = new boolean[23][10];
    }

    protected Board(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        Grid = new boolean[HEIGHT][WIDTH];
    }

    private boolean isFreeSpace(Shape shape, int x, int y) {
        boolean isFree = true;

        boolean[][] shapeData = ShapeDataManager.getStateData(shape);
        for (int i = x; i < x + shapeData.length && isFree; i++)
            for (int j = y; j < y + shapeData[i].length && isFree; j++)
                if (Grid[i][j] && shapeData[i - x][j - y])
                    isFree = false;

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

    public boolean ValidPosition(Shape shape, int x, int y){
        return isFreeSpace(shape,x,y);
    }

//    public boolean freeToRotate(Shape shape, Direction direction) {
//        switch (direction) {
//            case CLOCKWISE:
//        }
//    }

    public void show() {
        blocks.forEach(Block -> show());
    }
}
