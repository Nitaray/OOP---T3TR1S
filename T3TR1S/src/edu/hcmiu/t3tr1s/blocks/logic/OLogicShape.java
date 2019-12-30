package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.utils.Tuple;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class OLogicShape extends LogicShape {
    public OLogicShape(LogicBoard logicBoard){
        super(logicBoard);
        ID = 2;
        offsetTransition = new int[][][]{
                {{0,0}},
                {{0,-1}},
                {{-1,-1}},
                {{-1,0}}
        };
    }

    public OLogicShape(int x, int y, LogicBoard logicBoard){
        super(x, y, logicBoard);
        ID = 2;
        offsetTransition = new int[][][]{
                {{0,0}},
                {{0,-1}},
                {{-1,-1}},
                {{-1,0}}
        };
    }

    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        offsetData = new Tuple<>(true, 0, 0);
    }
}
