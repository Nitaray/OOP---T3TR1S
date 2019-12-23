package edu.hcmiu.t3tr1s.math;

/**
 * A vector class containing 3 floats x, y, z.
 */

public class Vector3f {

    public float x, y, z;

    public Vector3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

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

    public void setXYZ(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public Vector3f add(Vector3f v) {
        Vector3f result = new Vector3f();
        result.x = this.x + v.x;
        result.y = this.y + v.y;
        result.z = this.z + v.z;
        return result;
    }

    public Vector3f subtract(Vector3f v) {
        Vector3f result = new Vector3f();
        result.x = this.x - v.x;
        result.y = this.y - v.y;
        result.z = this.z - v.z;
        return result;
    }

}
