package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class Shape extends Rectangle {
    /**
     * Constructor for the rectangle class
     *
     * @param topLeft     The 3D-coordinates for the top-left of the rectangle.
     * @param shapeSize   The size of the shape.
     * @param blockSize   The size of the shape's blocks.
     * @param textureName The name of the texture to paint over this rectangle.
     */
    public Shape(Vector3f topLeft, float shapeSize, float blockSize, String textureName) {
        super(topLeft, shapeSize, shapeSize, "REGULAR_RECTANGLE", textureName);
        this.blockSize = blockSize;
    }

    private float blockSize;


    public void move(Direction direction) {
        switch (direction) {
            case UP:
                super.translate(new Vector3f(0, blockSize, 0));
                break;
            case DOWN:
                super.translate(new Vector3f(0, -blockSize, 0));
                break;
            case RIGHT:
                super.translate(new Vector3f(blockSize, 0, 0));
                break;
            case LEFT:
                super.translate(new Vector3f(-blockSize, 0, 0));
                break;
        }
    }

    public void rotate(Direction direction) {
        switch (direction) {
            case CLOCKWISE:
                super.rotate(-90.0f);
                break;
            case COUNTER_CLOCKWISE:
                super.rotate(90.0f);
                break;
        }
    }
}
