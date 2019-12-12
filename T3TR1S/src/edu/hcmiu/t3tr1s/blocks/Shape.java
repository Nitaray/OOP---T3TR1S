package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.client.Board;
import edu.hcmiu.t3tr1s.enums.Direction;

import java.util.ArrayList;

public abstract class Shape {

    protected ArrayList<Block> blocks = new ArrayList<>();

    protected int x, y;
    protected int state;
    protected int[][][] offsetTransition;
    protected boolean[][] grid;


    public Shape() {
        x = 0;
        y = 0;
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

    public void move(Direction direction, Board board) {
        if (board.freeToMove(this, direction))
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

    protected boolean offset(int oldstate, int newstate, Board board) {
        for (int i = 0; i < offsetTransition[oldstate].length; ++i) {
            int transX = offsetTransition[newstate][i][0] - offsetTransition[oldstate][i][0];
            int transY = offsetTransition[newstate][i][1] - offsetTransition[oldstate][i][1];
            if(board.ValidPosition(this, x + transX, y + transY)){
                x += transX;
                y += transY;
                return true;
            }
        }
        return false;
    }

    public abstract void rotate(Direction direction, Board board, boolean shouldOffset);

    public void show() {
        blocks.forEach(Block -> show());
    }

    public void hide() {
        blocks.forEach(Block -> hide());
    }
}
