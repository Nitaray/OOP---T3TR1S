package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.utils.Tuple;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class ILogicShape extends LogicShape {
    public ILogicShape(LogicBoard logicBoard){
        super(logicBoard);
        ID = 1;
        offsetTransition = new int[][][]{
                {{0,0},{-1,0},{2,0},{-1,0},{2,0}},
                {{-1,0},{0,0},{0,0},{0,1},{0,-2}},
                {{-1,1},{1,1},{-2,1},{1,0},{-2,0}},
                {{0,1},{0,1},{0,1},{0,-1},{0,2}}
        };
    }

    public ILogicShape(int x, int y, LogicBoard logicBoard) {
        super(x, y, logicBoard);
        ID = 1;
        offsetTransition = new int[][][]{
                {{0,0},{-1,0},{2,0},{-1,0},{2,0}},
                {{-1,0},{0,0},{0,0},{0,1},{0,-2}},
                {{-1,1},{1,1},{-2,1},{1,0},{-2,0}},
                {{0,1},{0,1},{0,1},{0,-1},{0,2}}
        };
    }
}
