package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.Direction;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class LShape extends Shape {
    public LShape(){
        state = 0;
        offsetTransition = new int[][][]{
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{1,0},{1,-1},{0,2},{1,2}},
                {{0,0},{0,0},{0,0},{0,0},{0,0}},
                {{0,0},{-1,0},{-1,-1},{0,2},{-1,2}}
        };
        grid = ShapeDataManager.getStateData(this);
    }

    public LShape(int x, int y){
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
                rotate(Direction.CLOCKWISE, false);
            }
        }
    }
}
