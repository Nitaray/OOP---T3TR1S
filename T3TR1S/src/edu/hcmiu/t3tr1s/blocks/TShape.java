package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class TShape extends Shape {
    public TShape(){
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);
        createBlocks();
    }

    /**
     * used to set up spawn state
     * @param x x-coordinate of shape in board
     * @param y y-coordinate of shape in board
     */
    public TShape(int x, int y){
        this.x = x;
        this.y = y;
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);
    }

    private void createBlocks(){
        Vector3f middle = new Vector3f(80.0f, 90.0f - size - 1.0f, 0.5f);
        Vector3f left = new Vector3f(middle.getX() - size, middle.getY(), 0.5f);
        Vector3f right = new Vector3f(middle.getX() + size, middle.getY(), 0.5f);
        Vector3f up = new Vector3f(middle.getX(), middle.getY() + size, 0.5f);

        Block middleBlock = new Block(middle, "SET1_RED", ShaderManager.getInstance());
        Block leftBlock = new Block(left, "SET1_RED", ShaderManager.getInstance());
        Block rightBlock = new Block(right, "SET1_RED", ShaderManager.getInstance());
        Block upBlock = new Block(up, "SET1_RED", ShaderManager.getInstance());

        blocks.add(middleBlock);
        blocks.add(leftBlock);
        blocks.add(rightBlock);
        blocks.add(upBlock);
    }

    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset = false;
        if(direction==Direction.CLOCKWISE){
            grid = ShapeDataManager.getStateData(this,(state + 1) % 4);
            if(shouldOffset){
                canOffset = offset(state, (state + 1) % 4);
            }
            if(!canOffset){
                rotate(Direction.COUNTER_CLOCKWISE, false);
            }
        }
        else{
            grid = ShapeDataManager.getStateData(this,(state - 1) % 4);
            if(shouldOffset){
                canOffset = offset(state, (state - 1) % 4);
            }
            if(!canOffset){
                rotate(Direction.CLOCKWISE,false);
            }
        }
    }
}
