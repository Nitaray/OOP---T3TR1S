package edu.hcmiu.t3tr1s.math;

/**
 * A vector class containing 3 floats x, y, z.
 */

public class Vector3f {

    public float x, y, z;

    public Vector3f(float x, float y, float z) {

        this.x = x;

        this.y = y;

        this.z = z;
    }
    public void setY(float y){
        this.y = y;
    }
    public float getY(){
        return y;
    }

}
