package edu.hcmiu.t3tr1s.blocks;

import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;

/**
 * The individual blocks that make up the tetriminoes.
 */

public class Block extends Rectangle {

    private float size;

    /**
     * Default block constructor.
     * @param topLeftPosition The position of the block.
     * @param textureName Name of the shader of this block.
     */

    public Block(Vector3f topLeftPosition, String textureName, ShaderManager shaderManager) {
        super(topLeftPosition, 2.0f, 2.0f, "REGULAR_RECTANGLE", textureName, shaderManager);
        this.size = 2.0f;
    }

    /**
     * Custom block constructor.
     * @param topLeftPosition the position of the top-left position of the block.
     * @param size the size of the side of the block.
     * @param textureName Name of the shader of this block.
     */

    public Block(Vector3f topLeftPosition, float size, String textureName, ShaderManager shaderManager) {
        super(topLeftPosition, size, size, "REGULAR_RECTANGLE", textureName, shaderManager);
    }

    public float getSize() {
        return size;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP:
                super.translate(new Vector3f(0, size, 0));
                break;
            case DOWN:
                super.translate(new Vector3f(0, -size, 0));
                break;
            case RIGHT:
                super.translate(new Vector3f(size, 0, 0));
                break;
            case LEFT:
                super.translate(new Vector3f(-size, 0, 0));
                break;
        }
    }
}
