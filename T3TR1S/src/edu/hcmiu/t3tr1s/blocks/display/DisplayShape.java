package edu.hcmiu.t3tr1s.blocks.display;

import edu.hcmiu.t3tr1s.blocks.Shape;
import edu.hcmiu.t3tr1s.blocks.logic.LogicShape;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Showable;
import edu.hcmiu.t3tr1s.math.Vector3f;
import edu.hcmiu.t3tr1s.utils.Tuple;

public class DisplayShape implements Showable {

    private LogicShape logicShape;

    private Shape shape;

    private Vector3f v;
    private float blockSize;

    private boolean isGhost;

    public DisplayShape (LogicShape logicShape, Vector3f topLeftPosition, float blockSize, boolean isGhost) {
        this.logicShape = logicShape;
        this.v = topLeftPosition;
        this.blockSize = blockSize;
        this.isGhost = isGhost;
        if (!isGhost)
            initShape();
        else
            initShapeGhost();
    }

    public LogicShape getLogicShape() {
        return logicShape;
    }

    public void initShape() {
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        boolean[][] grid = shapeDataManager.getStateData(logicShape);
        shape = new Shape(v, blockSize * grid.length, blockSize, shapeDataManager.getShapeTextureName(logicShape));
    }

    public void initShapeGhost() {
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        boolean[][] grid = shapeDataManager.getStateData(logicShape);
        shape = new Shape(v, blockSize * grid.length, blockSize, shapeDataManager.getGhostTextureName(logicShape));
    }

    public boolean move(Direction direction) {
        if (logicShape.move(direction)) {
            shape.move(direction);
            return true;
        }
        return false;
    }

    public void rotateShapeOnly(Direction direction) {
        shape.rotate(direction);
    }

    public void rotate(Direction direction) {
        Tuple<Integer, Integer, Boolean> tuple = logicShape.rotate(direction);
        if (tuple.z) {
            shape.rotate(direction);
            int offsetX = tuple.x;
            int offsetY = tuple.y;
            if (!isGhost) {
                while (offsetX != 0) {
                    if (offsetX > 0) {
                        shape.move(Direction.RIGHT);
                        offsetX--;
                    }
                    else {
                        shape.move(Direction.LEFT);
                        offsetX++;
                    }
                }
                while (offsetY != 0) {
                    if (offsetY > 0) {
                        shape.move(Direction.UP);
                        offsetY--;
                    }
                    else {
                        shape.move(Direction.DOWN);
                        offsetY++;
                    }
                }
            }
        }
    }

    @Override
    public void show() {
        shape.show();
    }

    @Override
    public void hide() {
        shape.hide();
    }
}
