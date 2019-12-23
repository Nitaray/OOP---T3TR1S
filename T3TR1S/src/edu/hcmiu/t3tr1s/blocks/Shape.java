package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.client.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.math.Vector3f;
import java.util.ArrayList;

public abstract class Shape {

    protected ArrayList<Block> blocks = new ArrayList<>();

    protected int x, y;
    protected int state;
    protected int[][][] offsetTransition;
    protected boolean[][] grid;
    protected Block origin_block;
    protected LogicBoard logicBoard;
    protected float size = 2.0f; // size of shape blocks


    public Shape(LogicBoard logicBoard) {
        this.logicBoard = logicBoard;
        this.x = 0;
        this.y = 0;
        this.state = 0;
    }

    public Shape(int x, int y, LogicBoard logicBoard){
        this.x = x;
        this.y = y;
        this.logicBoard = logicBoard;
        this.state = 0;
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

    public void setSize(float size) {
        this.size = size;
    }

    public void move(Direction direction, LogicBoard logicBoard) {
        if (logicBoard.freeToMove(this, direction))
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

    protected boolean offset(int oldState, int newState, LogicBoard logicBoard) {
        /**
         * run 5 tests
         */
        for (int i = 0; i < offsetTransition[oldState].length; ++i) {
            int transX = offsetTransition[newState][i][0] - offsetTransition[oldState][i][0];
            int transY = offsetTransition[newState][i][1] - offsetTransition[oldState][i][1];
            if(logicBoard.ValidPosition(this, x + transX, y + transY)){
                x += transX;
                y += transY;
                return true;
            }
        }
        return false;
    }

    /**
     * rotate a block about origin block
     * @param block block needs rotating
     * @param direction rotate direction
     */
    protected void rotateBlock(Block block, Direction direction){
        Vector3f blockPosition = block.getTopLeft();
        Vector3f originPosition = origin_block.getTopLeft();

        float blockX = blockPosition.getX();
        float blockY = blockPosition.getY();
        float originX = originPosition.getX();
        float originY = originPosition.getY();

        float relativeX = blockX - originX;
        float relativeY = blockY - originY;
        float newRelativeX, newRelativeY;
        if(direction == Direction.CLOCKWISE){
            newRelativeX = 0 * relativeX + 1 * relativeY;
            newRelativeY = -1 * relativeX + 0 * relativeY;
        }

        else{
            newRelativeX = 0 * relativeX - 1 * relativeY;
            newRelativeY = 1 * relativeX + 0 * relativeY;
        }

        float newX = newRelativeX + originX;
        float newY = newRelativeY + originY;

        block.setTopLeft(newX, newY, 0.5f);
    }

    public abstract void rotate(Direction direction, boolean shouldOffset);

    public void show() {
        blocks.forEach(Block -> show());
    }

    public void hide() {
        blocks.forEach(Block -> hide());
    }
}
