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
        createBlocks();
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
        createBlocks();
    }

    private void createBlocks() {
        Vector3f middle = new Vector3f(80.0f, 90.0f - size - 1.0f, 0.5f);
        Vector3f left = new Vector3f(middle.getX() - size, middle.getY(), 0.5f);
        Vector3f right = new Vector3f(middle.getX() + size, middle.getY(), 0.5f);
        Vector3f up = new Vector3f(middle.getX(), middle.getY() + size, 0.5f);

        Block middleBlock = new Block(middle, "SET1_RED");
        Block leftBlock = new Block(left, "SET1_RED");
        Block rightBlock = new Block(right, "SET1_RED");
        Block upBlock = new Block(up, "SET1_RED");

        blocks.add(middleBlock);
        blocks.add(leftBlock);
        blocks.add(rightBlock);
        blocks.add(upBlock);
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
