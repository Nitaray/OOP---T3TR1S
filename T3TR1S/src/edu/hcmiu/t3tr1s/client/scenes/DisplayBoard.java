package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.blocks.Shape;
import edu.hcmiu.t3tr1s.blocks.TShape;
import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.logic.LogicBoard;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.enums.Direction;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;
import org.lwjgl.glfw.GLFW;

public class DisplayBoard extends Scene {
    private LogicBoard logicBoard;

    private Vector3f topLeft;
    private Vector3f offSet;

    private Shape shape;


//    public DisplayBoard(String name, LogicBoard logicBoard, ShaderManager shaderManager) {
//        super(name, shaderManager);
//        this.logicBoard = logicBoard;
////        this.topLeft = background.getTopLeft();
//        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
//                "REGULAR_RECTANGLE", "MENU_BACKGROUND", shaderManager);
//        setBackground(background);
//    }

    public DisplayBoard(String name, ShaderManager shaderManager){
        super(name, shaderManager);
        this.logicBoard = LogicBoard.getInstance();
        Rectangle background = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND", shaderManager);
        setBackground(background);
    }

//    public void drawLogicBoard() {
//        int[][] grid = logicBoard.getGrid();
//        for (int x = 0; x < grid.length; x++)
//            for (int y = 0; y < grid[x].length; y++) {
//                switch (grid[x][y]) {
//                    case
//                }
//            }
//    }

    @Override
    public void show(Renderer renderer) {
        background.show(renderer);
        shape = new TShape();
        shape.show(renderer);
    }

    @Override
    public void hide(Renderer renderer) {
        background.hide(renderer);
        shape.hide(renderer);
    }

    @Override
    public void update(Client client) {
        if(Input.isKeyDown(GLFW.GLFW_KEY_RIGHT) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.RIGHT);
        }
        else if(Input.isKeyDown(GLFW.GLFW_KEY_LEFT) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.LEFT);
        }
        else if(Input.isKeyDown(GLFW.GLFW_KEY_UP) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.UP);
        }
        else if(Input.isKeyDown(GLFW.GLFW_KEY_DOWN) && keyCooled(300 * MILLISECONDS)){
            shape.move(Direction.DOWN);
        }
    }
}
