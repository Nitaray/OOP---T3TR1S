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
public class SShape extends Shape {

    public SShape(LogicBoard logicBoard){
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

    public SShape(int x, int y, LogicBoard logicBoard){
        super(x, y, logicBoard);
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);

        createBlocks((float) x, (float) y);
    }

    private void createBlocks(){
        /**
         * define blocks
         * MidCoor: middle blocks
         * LeftCoor: to the left of the middle
         * UpCoor: to the top of the middle
         * UpRight: to the top right of the middle
         */
        Vector3f MidCoor = new Vector3f(logicBoard.getWIDTH()/2.0f, logicBoard.getHEIGHT() - size - 1.0f, 0.5f);
        Vector3f LeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY(), 0.5f);
        Vector3f UpCoor = new Vector3f(MidCoor.getX(), MidCoor.getY() + size, 0.5f);
        Vector3f UpRight = new Vector3f(MidCoor.getX() + size, MidCoor.getY() + size, 0.5f);

        Block middle = new Block(MidCoor, "SShape_mid_block", ShaderManager.getInstance());
        Block left = new Block(LeftCoor, "SShape_left_block", ShaderManager.getInstance());
        Block up = new Block(UpCoor, "SShape_up_block", ShaderManager.getInstance());
        Block upright = new Block(UpRight, "SShape_upright_block", ShaderManager.getInstance());

        /**
         * blocks are restored
         * middle, left, up, upright
         */
        blocks.add(middle);
        blocks.add(left);
        blocks.add(up);
        blocks.add(upright);
    }

    private void createBlocks(float x, float y){
        Vector3f MidCoor = new Vector3f(x, y, 0.5f);
        Vector3f LeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY(), 0.5f);
        Vector3f UpCoor = new Vector3f(MidCoor.getX(), MidCoor.getY() + size, 0.5f);
        Vector3f UpRight = new Vector3f(MidCoor.getX() + size, MidCoor.getY() + size, 0.5f);

        Block middle = new Block(MidCoor, "SShape_mid_block", ShaderManager.getInstance());
        Block left = new Block(LeftCoor, "SShape_left_block", ShaderManager.getInstance());
        Block up = new Block(UpCoor, "SShape_up_block", ShaderManager.getInstance());
        Block upright = new Block(UpRight, "SShape_upright_block", ShaderManager.getInstance());

        /**
         * blocks are restored
         * middle, left, up, upright
         */
        blocks.add(middle);
        blocks.add(left);
        blocks.add(up);
        blocks.add(upright);
    }

    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset;
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
