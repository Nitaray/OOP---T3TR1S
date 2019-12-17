package edu.hcmiu.t3tr1s.graphics;

import edu.hcmiu.t3tr1s.core.ShaderManager;
import edu.hcmiu.t3tr1s.math.Vector3f;

public class Button extends Rectangle {
    private int selection;

    /**
     * Constructor for the rectangle class
     *
     * @param topLeft       The 3D-coordinates for the top-left of the rectangle.
     * @param width         The width of the rectangle.
     * @param height        The height of the rectangle.
     * @param shaderName    The name of the shader to draw in this rectangle.
     * @param selection     The selecting position: 0 is at Start, 1 is at Quit, more to come
     */
    public Button(Vector3f topLeft, float width, float height, String shaderName, String textureName, ShaderManager shaderManager, int selection) {
        super(topLeft, width, height, shaderName, textureName, shaderManager);
        this.selection = selection;
    }


    public void translate(Vector3f v) {
        //if arrow is pointing at Start Game
        if (this.getSelection() == 0){
            v.setY(-v.getY());
            super.translate(v);
            this.setSelection(1);
        }
        // if arrow is pointing at Quit Game
        else{
            super.translate(v);
            this.setSelection(0);
        }
    }

    public int getSelection(){
        return selection;
    }
    public void setSelection(int selection){
        this.selection = selection;
    }

}
