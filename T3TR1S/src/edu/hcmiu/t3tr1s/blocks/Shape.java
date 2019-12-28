package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Showable;

import java.util.ArrayList;

public abstract class Shape implements Showable {

    protected ArrayList<Block> blocks = new ArrayList<>();

    protected int x, y;
    protected int state;
    protected int[][][] offsetTransition;

    protected boolean[][] grid;

    protected float size = 2.0f;

    protected LogicBoard logicBoard;

    public Shape(LogicBoard logicBoard) {
        x = 0;
        y = 0;
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

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void move(Direction direction) {
//        if (logicBoard.freeToMove(this, direction))
            switch (direction) {
                case LEFT:
                    x--;
                    blocks.forEach(block -> block.move(direction));
                    break;
                case DOWN:
                    y++;
                    blocks.forEach(block -> block.move(direction));
                    break;
                case UP:
                    y--;
                    blocks.forEach(block -> block.move(direction));
                    break;
                case RIGHT:
                    x++;
                    blocks.forEach(block -> block.move(direction));
                    break;
            }
    }

    protected boolean offset(int oldState, int newState) {
        for (int i = 0; i < offsetTransition[oldState].length; ++i) {
            int transX = offsetTransition[newState][i][0] - offsetTransition[oldState][i][0];
            int transY = offsetTransition[newState][i][1] - offsetTransition[oldState][i][1];
            if(logicBoard.isFreeSpace(this, x + transX, y + transY)){
                x += transX;
                y += transY;
                return true;
            }
        }
        return false;
    }

    public abstract void rotate(Direction direction, boolean shouldOffset);

    public void show() {
        blocks.forEach(Block -> Block.show());
    }

    public void hide() {
        blocks.forEach(Block -> Block.hide());
    }
}
