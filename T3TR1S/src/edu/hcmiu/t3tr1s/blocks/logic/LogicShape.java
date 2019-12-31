package edu.hcmiu.t3tr1s.blocks.logic;

import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.enums.ShapeType;
import edu.hcmiu.t3tr1s.utils.Tuple;
import javafx.util.Pair;

import java.util.ArrayList;

public class LogicShape {

    protected int x, y;
    protected int state;
    protected ShapeType shapeType;
    protected ArrayList<ArrayList<Pair<Integer, Integer>>> offsetTransition;

    protected LogicBoard logicBoard;
    protected ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();

    public LogicShape(LogicBoard logicBoard, ShapeType shapeType) {
        x = 0;
        y = 0;
        state = 0;
        this.logicBoard = logicBoard;
        this.shapeType = shapeType;
        this.offsetTransition = shapeDataManager.getShapeOffset(shapeType);
    }

    public LogicShape(int x, int y, LogicBoard logicBoard, ShapeType shapeType) {
        this.x = x;
        this.y = y;
        state = 0;
        this.logicBoard = logicBoard;
        this.shapeType = shapeType;
        this.offsetTransition = shapeDataManager.getShapeOffset(shapeType);
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

    public ShapeType getType() {
        return shapeType;
    }

    public boolean move(Direction direction) {
        if (logicBoard.freeToMove(this, direction)) {
            System.out.println("Move successfully!");
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

    public Tuple<Integer, Integer, Boolean> rotate(Direction direction) {
        Tuple<Integer, Integer, Boolean> offsetData = new Tuple<>(0, 0, false);

        if (direction == Direction.CLOCKWISE)
            offset(state, (state + 1) % 4, offsetData);
        else
            offset(state, ((state - 1) % 4 + 4) % 4, offsetData);

        return offsetData;
    }

    protected void offset(int oldState, int newState, Tuple<Integer, Integer, Boolean> offsetData) {
        state = newState;
        for (int i = 0; i < offsetTransition.get(oldState).size(); ++i) {
            int transX = offsetTransition.get(oldState).get(i).getKey() - offsetTransition.get(newState).get(i).getKey();
            int transY = offsetTransition.get(oldState).get(i).getValue() - offsetTransition.get(newState).get(i).getValue();
            if (logicBoard.isFreeSpace(this, x + transX, y + transY)) {
                offsetData.x = transX;
                offsetData.y = transY;
                offsetData.z = true;
                x += transX;
                y += transY;
                return;
            }
        }
        state = oldState;
        offsetData.x = 0;
        offsetData.y = 0;
        offsetData.z = false;
    }
}
