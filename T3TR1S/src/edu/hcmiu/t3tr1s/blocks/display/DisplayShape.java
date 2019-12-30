package edu.hcmiu.t3tr1s.blocks.display;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.blocks.Shape;
import edu.hcmiu.t3tr1s.blocks.logic.LogicShape;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Showable;
import edu.hcmiu.t3tr1s.math.Vector3f;
import edu.hcmiu.t3tr1s.utils.Tuple;

import java.util.ArrayList;

public class DisplayShape implements Showable {

    private LogicShape logicShape;

    private Shape shape;

    private Vector3f v;
    private float blockSize;

    public DisplayShape (LogicShape logicShape, Vector3f topLeftPosition, float blockSize) {
        this.logicShape = logicShape;
        this.v = topLeftPosition;
        this.blockSize = blockSize;
        initShape();
    }

    public void initShape() {
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        boolean[][] grid = shapeDataManager.getStateData(logicShape);
        shape = new Shape(v, blockSize * grid.length, blockSize, shapeDataManager.getShapeTextureName(logicShape));
    }

    public ArrayList<Block> getBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        boolean[][] grid = shapeDataManager.getStateData(logicShape);
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x]) {
                    Block block = new Block(new Vector3f(v.x + x * blockSize, v.y + y * blockSize, v.z),
                            shapeDataManager.getBlockTextureName(logicShape));
//                    Block block = new Block(new Vector3f(v.x + x * blockSize, v.y - y * blockSize, v.z), blockSize,
//                            "SET1_PURPLE"); // Temporary until getBlockTextureName is implemented
                    blocks.add(block);
                }
            }
        return blocks;
    }

    public void move(Direction direction) {
        if (logicShape.move(direction)) {
            shape.move(direction);
        }
    }

    public void rotate(Direction direction) {
        Tuple<Boolean, Integer, Integer> tuple = logicShape.rotate(direction);
        if (tuple.x) {
            shape.rotate(direction);
            int offsetX = tuple.y;
            int offsetY = tuple.z;
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

    @Override
    public void show() {
        shape.show();
    }

    @Override
    public void hide() {
        shape.hide();
    }
}
