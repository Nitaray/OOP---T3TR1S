package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.utils.Tuple;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class TLogicShape extends LogicShape {
    public TLogicShape(LogicBoard logicBoard) {
        super(logicBoard);
        ID = 7;
        offsetTransition = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}}
        };
    }

    /**
     * used to set up spawn state
     *
     * @param x x-coordinate of shape in board
     * @param y y-coordinate of shape in board
     */
    public TLogicShape(int x, int y, LogicBoard logicBoard) {
        super(x, y, logicBoard);
        ID = 7;
        offsetTransition = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}}
        };
    }
}
