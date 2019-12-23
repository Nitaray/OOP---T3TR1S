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

public class TShape extends Shape {
    private LogicBoard logicBoard;
    public TShape(LogicBoard logicBoard){
        super(logicBoard);
        this.logicBoard = logicBoard;
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
     * custom TShape
     * @param x x-coordinate of shape in board
     * @param y y-coordinate of shape in board
     * @param logicBoard logic board instance
     */
    public TShape(int x, int y, LogicBoard logicBoard){
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
         * define shape's block
         * MidCoor: the middle block
         * LeftCoor: the block to the left of the middle block
         * RightCoor: the block to the right of the middle block
         * UpCoor: the block above the middle block
         * Spawn state: spawn down 1 row from the top, middle column
         */

        Vector3f MidCoor = new Vector3f(logicBoard.getWIDTH() / 2.0f, logicBoard.getHEIGHT() - size - 1.0f, 0.5f);
        Vector3f LeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY(), 0.5f);
        Vector3f RightCoor = new Vector3f(MidCoor.getX() + size, MidCoor.getY(), 0.5f);
        Vector3f UpCoor = new Vector3f(MidCoor.getX(), MidCoor.getY() + size, 0.5f);

        Block middle = new Block(MidCoor, "Middle_T_shape", ShaderManager.getInstance());
        Block left = new Block(LeftCoor, "Left_T_Shape", ShaderManager.getInstance());
        Block right = new Block(RightCoor, "Right_T_shape", ShaderManager.getInstance());
        Block up = new Block(UpCoor, "Up_T_shape", ShaderManager.getInstance());

        origin_block = middle;

        /**
         * blocks store block in order: middle, left, right, up
         */
        blocks.add(middle);
        blocks.add(left);
        blocks.add(right);
        blocks.add(up);
    }

    private void createBlocks(float x, float y){

        /**
         * define shape's block
         * MidCoor: the middle block
         * LeftCoor: the block to the left of the middle block
         * RightCoor: the block to the right of the middle block
         * UpCoor: the block above the middle block
         * Spawn state: spawn in the row 22, middle column
         */

        Vector3f MidCoor = new Vector3f(x, y, 0.5f);
        Vector3f LeftCoor = new Vector3f(MidCoor.getX() - size, MidCoor.getY(), 0.5f);
        Vector3f RightCoor = new Vector3f(MidCoor.getX() + size, MidCoor.getY(), 0.5f);
        Vector3f UpCoor = new Vector3f(MidCoor.getX(), MidCoor.getY() + size, 0.5f);

        Block middle = new Block(MidCoor, "Middle_T_shape", ShaderManager.getInstance());
        Block left = new Block(LeftCoor, "Left_T_Shape", ShaderManager.getInstance());
        Block right = new Block(RightCoor, "Right_T_shape", ShaderManager.getInstance());
        Block up = new Block(UpCoor, "Up_T_shape", ShaderManager.getInstance());

        origin_block = middle;

        /**
         * blocks store block in order: middle, left, right, up
         */
        blocks.add(middle);
        blocks.add(left);
        blocks.add(right);
        blocks.add(up);
    }

    
    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset;
        if(direction==Direction.CLOCKWISE){
            /**
             * rotate grid
             */
            grid = ShapeDataManager.getStateData(this,(state + 1) % 4);

            if(shouldOffset){
                canOffset = offset(state, (state + 1) % 4, logicBoard);
                if(!canOffset){
                    rotate(Direction.COUNTER_CLOCKWISE, false);
                }
                else{
                    /**
                     * rotate blocks
                     */
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
                else {
                    /**
                     * rotate blocks
                     */
                    for (Block block : blocks) {
                        rotateBlock(block, direction);
                    }
                }
            }
        }
    }
}
