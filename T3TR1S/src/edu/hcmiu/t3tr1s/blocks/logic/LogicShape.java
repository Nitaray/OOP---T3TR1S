package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.utils.Tuple;

public abstract class LogicShape {

    protected int x, y;
    protected int state;
    protected int ID;
    protected int[][][] offsetTransition;

    protected LogicBoard logicBoard;
    protected ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();

    /**
     * offsetData<rotatable, offsetx, offsetY, direction>
     * rotatable - whether the shape can rotate
     * offsetX, offsetY - values of offset if can rotate, else 0.
     * direction: rotate direction. If rotatable is false, direction = null
     */
    protected Tuple<Boolean, Integer, Integer> offsetData;

    protected int offsetX, offsetY;

    public LogicShape(LogicBoard logicBoard) {
        x = 0;
        y = 0;
        state = 0;
        this.logicBoard = logicBoard;
    }

    public LogicShape(int x, int y, LogicBoard logicBoard) {
        this.x = x;
        this.y = y;
        state = 0;
        this.logicBoard = logicBoard;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getState() {
        return state;
    }

    public int getID() {
        return ID;
    }

    public boolean move(Direction direction) {
        if (logicBoard.freeToMove(this, direction)) {
//            System.out.println("Move successfully!");
            switch (direction) {
                case LEFT:
                    x--;
                    return true;
                case DOWN:
                    y--;
                    return true;
                case UP:
                    y++;
                    return true;
                case RIGHT:
                    x++;
                    return true;
            }
        }
        System.out.println("Move failed!");
        return false;
    }

    protected boolean offset(int oldState, int newState) {
        state = newState;
        for (int i = 0; i < offsetTransition[oldState].length; ++i) {
            int transX = offsetTransition[oldState][i][0] - offsetTransition[newState][i][0];
            int transY = offsetTransition[oldState][i][1] - offsetTransition[newState][i][1];
            if (logicBoard.isFreeSpace(this, x + transX, y + transY)) { // <-- bug right here
                System.out.println(transX);
                System.out.println(transY);
                offsetX = transX;
                offsetY = transY;
                x += transX;
                y += transY;
                return true;
            }
        }
        state = oldState;
        return false;
    }

    public Tuple<Boolean, Integer, Integer> rotate(Direction direction) {
        rotate(direction, true);
        return offsetData;
    }

    protected void rotate(Direction direction, boolean shouldOffset) {
        boolean canOffset = false;
        if (direction == Direction.CLOCKWISE) {
            if (shouldOffset) {
                canOffset = offset(state, (state + 1) % 4);
                if (!canOffset) {
                    offsetData = new Tuple<>(false, 0, 0);
                    rotate(Direction.COUNTER_CLOCKWISE, false);
                }
                else{
                    offsetData = new Tuple<>(true, offsetX, offsetY);
                }
            }
        } else {
            if (shouldOffset) {
                canOffset = offset(state, ((state - 1) % 4 + 4) % 4);
                if (!canOffset) {
                    offsetData = new Tuple<>(false, 0, 0);
                    rotate(Direction.CLOCKWISE, false);
                }
                else{
                    offsetData = new Tuple<>(true, offsetX, offsetY);
                }
            }
        }
    }
}
