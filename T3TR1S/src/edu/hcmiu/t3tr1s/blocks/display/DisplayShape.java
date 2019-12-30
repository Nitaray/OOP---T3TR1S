package edu.hcmiu.t3tr1s.blocks.display;

import edu.hcmiu.t3tr1s.blocks.Block;
import edu.hcmiu.t3tr1s.blocks.logic.LogicShape;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Showable;
import edu.hcmiu.t3tr1s.math.Vector3f;

import java.util.ArrayList;

public class DisplayShape implements Showable {

    private LogicShape shape;

    private ArrayList<Block> blocks = new ArrayList<>();

    private Vector3f v;
    private float blockSize;

    public DisplayShape (LogicShape shape, Vector3f topLeftPosition, float blockSize) {
        this.shape = shape;
        this.v = topLeftPosition;
        this.blockSize = blockSize;
        initBlocks();
    }

    public void initBlocks() {
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        boolean[][] grid = shapeDataManager.getStateData(shape);
        for (int y = 0; y < grid.length; y++)
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x]) {
//                    Block block = new Block(new Vector3f(v.x + i * blockSize, v.y + j * blockSize, v.z),
//                            shapeDataManager.getShapeTextureName(shape));
                    Block block = new Block(new Vector3f(v.x + x * blockSize, v.y - y * blockSize, v.z), blockSize,
                            "SET1_PURPLE"); // Temporary until getShapeTextureName is implemented
                    blocks.add(block);
                }
            }
    }

    public void move(Direction direction) {
        if (shape.move(direction)) {
            blocks.forEach(block -> block.move(direction));
        }
    }

    @Override
    public void show() {
        blocks.forEach(Block::show);
    }

    @Override
    public void hide() {
        blocks.forEach(Block::hide);
    }
}
