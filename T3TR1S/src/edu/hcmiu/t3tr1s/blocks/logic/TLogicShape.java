package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * TODO: Implement rotation logic by Pham Hoang Minh using SRS
 * https://tetris.fandom.com/wiki/SRS
 */

public class TLogicShape extends LogicShape {
    public TLogicShape(LogicBoard logicBoard) {
        super(logicBoard);
        offsetTransition = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}}
        };
        grid = shapeDataManager.getStateData(this);
    }

    /**
     * used to set up spawn state
     *
     * @param x x-coordinate of shape in board
     * @param y y-coordinate of shape in board
     */
    public TLogicShape(int x, int y, LogicBoard logicBoard) {
        super(x, y, logicBoard);
        offsetTransition = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
                {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}}
        };
        grid = shapeDataManager.getStateData(this);
    }

    @Override
    public void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset = false;
        if (direction == Direction.CLOCKWISE) {
            grid = shapeDataManager.getStateData(this, (state + 1) % 4);
            if (shouldOffset) {
                canOffset = offset(state, (state + 1) % 4);
            }
            if (!canOffset) {
                rotate(Direction.COUNTER_CLOCKWISE, false);
            }
        } else {
            grid = shapeDataManager.getStateData(this, (state - 1) % 4);
            if (shouldOffset) {
                canOffset = offset(state, (state - 1) % 4);
            }
            if (!canOffset) {
                rotate(Direction.CLOCKWISE, false);
            }
        }
    }
}
