package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class OLogicShape extends LogicShape {
    public OLogicShape(LogicBoard logicBoard){
        super(logicBoard);
        offsetTransition = new int[][][]{
                {{0,0}},
                {{0,-1}},
                {{-1,-1}},
                {{-1,0}}
        };
    }

    public OLogicShape(int x, int y, LogicBoard logicBoard){
        super(x, y, logicBoard);
        offsetTransition = new int[][][]{
                {{0,0}},
                {{0,-1}},
                {{-1,-1}},
                {{-1,0}}
        };
    }

    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset = false;
        if(direction==Direction.CLOCKWISE){
            if(shouldOffset){
                canOffset = offset(state, (state + 1) % 4);
                if(!canOffset){
                    offsetData = new Tuple<>(false, 0, 0, null);
                    rotate(Direction.COUNTER_CLOCKWISE, false);
                }
                else{
                    offsetData = new Tuple<>(true, offsetX, offsetY, direction);
                }
            }

        }
        else{
            if(shouldOffset){
                canOffset = offset(state, (state - 1) % 4);
                if(!canOffset){
                    offsetData = new Tuple<>(false, 0, 0, null);
                    rotate(Direction.CLOCKWISE, false);
                }
                else{
                    offsetData = new Tuple<>(true, offsetX, offsetY, direction);
                }
            }
        }
    }
}
