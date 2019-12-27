package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.Direction;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

/**
 * TODO: Implement createBlocks() method
 */
public class JShape extends Shape {
    public JShape(LogicBoard logicBoard){
        super(logicBoard);
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);
    }

    public JShape(int x, int y, LogicBoard logicBoard){
        super(x, y, logicBoard);
        this.x = x;
        this.y = y;
        state = 0;
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);
    }

    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset;
        if(direction==Direction.CLOCKWISE){
            grid = ShapeDataManager.getStateData(this,(state + 1) % 4);
            if(shouldOffset){
                canOffset = offset(state, (state + 1) % 4, logicBoard);
                if(!canOffset){
                    rotate(Direction.COUNTER_CLOCKWISE, false);
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
