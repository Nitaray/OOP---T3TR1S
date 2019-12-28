package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;

import java.util.ArrayList;

public abstract class LogicShape {

    protected ArrayList<Block> blocks = new ArrayList<>();

    protected int x, y;
    protected int state;
    protected int ID;
    protected int[][][] offsetTransition;

    protected boolean[][] grid;

    protected float size = 2.0f;

    protected LogicBoard logicBoard;
    protected ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();

    public LogicShape(LogicBoard logicBoard) {
        x = 0;
        y = 0;
        state = 0;
        this.logicBoard = logicBoard;
    }

    public LogicShape(int x, int y, LogicBoard logicBoard) {
        this.x = x;
        this.y = y;
        state = 0;
        this.logicBoard = logicBoard;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getState() {
        return state;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public boolean move(Direction direction) {
        if (logicBoard.freeToMove(this, direction)) {
            System.out.println("Move successfully!");
            switch (direction) {
                case LEFT:
                    x--;
                    return true;
                case DOWN:
                    y--;
                    return true;
                case UP:
                    y++;
                    return true;
                case RIGHT:
                    x++;
                    return true;
            }
        }
        return false;
    }

    protected boolean offset(int oldState, int newState) {
        for (int i = 0; i < offsetTransition[oldState].length; ++i) {
            int transX = offsetTransition[newState][i][0] - offsetTransition[oldState][i][0];
            int transY = offsetTransition[newState][i][1] - offsetTransition[oldState][i][1];
            if (logicBoard.isFreeSpace(this, x + transX, y + transY)) {
                x += transX;
                y += transY;
                return true;
            }
        }
        return false;
    }

    public void rotate(Direction direction) {
        rotate(direction, true);
    }

    public abstract void rotate(Direction direction, boolean shouldOffset);
}
