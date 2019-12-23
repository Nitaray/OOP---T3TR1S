package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.client.LogicBoard;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class LShape extends Shape {
    public LShape(LogicBoard logicBoard){
        super(logicBoard);
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);
        createBlocks();
    }

    public LShape(int x, int y, LogicBoard logicBoard){
        super(x, y, logicBoard);
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);
        createBlocks((float)x, (float)y);
    }

    private void createBlocks(){
        /**
         * define shape blocks
         * MidCoor - middle block
         * LeftCoor - to the left of middle block
         * RightCoor - to the right of the middle block
         * UpLeftCoor - to the up left of the middle block
         */
        Vector3f MidCoor = new Vector3f(logicBoard.getWIDTH()/2.0f, logicBoard.getHEIGHT() - size - 1.0f, 0.5f);
        Vector3f LeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY(), 0.5f);
        Vector3f RightCoor = new Vector3f(MidCoor.getX() + size, MidCoor.getY(), 0.5f);
        Vector3f UpLeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY() + size, 0.5f);

        Block mid = new Block(MidCoor, "LShape_middle_block", ShaderManager.getInstance());
        Block left = new Block(LeftCoor, "LShape_left_block", ShaderManager.getInstance());
        Block right = new Block(RightCoor, "LShape_right_block", ShaderManager.getInstance());
        Block upleft = new Block(UpLeftCoor, "LShape_upleft_block", ShaderManager.getInstance());

        /**
         * block order
         * mid, left, right, upleft
         */
        blocks.add(mid);
        blocks.add(left);
        blocks.add(right);
        blocks.add(upleft);
    }

    private void createBlocks(float x, float y){
        Vector3f MidCoor = new Vector3f(x, y, 0.5f);
        Vector3f LeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY(), 0.5f);
        Vector3f RightCoor = new Vector3f(MidCoor.getX() + size, MidCoor.getY(), 0.5f);
        Vector3f UpLeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY() + size, 0.5f);

        Block mid = new Block(MidCoor, "LShape_middle_block", ShaderManager.getInstance());
        Block left = new Block(LeftCoor, "LShape_left_block", ShaderManager.getInstance());
        Block right = new Block(RightCoor, "LShape_right_block", ShaderManager.getInstance());
        Block upleft = new Block(UpLeftCoor, "LShape_upleft_block", ShaderManager.getInstance());

        /**
         * block order
         * mid, left, right, upleft
         */
        blocks.add(mid);
        blocks.add(left);
        blocks.add(right);
        blocks.add(upleft);
    }

    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset = false;
        if(direction==Direction.CLOCKWISE){
            grid = ShapeDataManager.getStateData(this,(state + 1) % 4);
            if(shouldOffset){
                canOffset = offset(state, (state + 1) % 4, logicBoard);
                if(!canOffset){
                    rotate(Direction.COUNTER_CLOCKWISE,false);
                }
                else{
                    for(Block block: blocks){
                        rotateBlock(block, direction);
                    }
                }
            }

        }
        else{
            grid = ShapeDataManager.getStateData(this,(state - 1) % 4);
            if(shouldOffset){
                canOffset = offset(state, (state - 1) % 4, logicBoard);
                if(!canOffset){
                    rotate(Direction.CLOCKWISE, false);
                }
                else{
                    for(Block block: blocks){
                        rotateBlock(block, direction);
                    }
                }
            }

        }
    }
}
