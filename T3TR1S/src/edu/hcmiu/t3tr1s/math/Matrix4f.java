package edu.hcmiu.t3tr1s.math;

import edu.hcmiu.t3tr1s.utils.BufferUtils;

import java.nio.FloatBuffer;

/**
 * A 4 x 4 matrix of floats, used for coordinate manipulation.
 */

public class Matrix4f {

    private static Matrix4f ourInstance = new Matrix4f();

    public static Matrix4f getInstance() {
        return ourInstance;
    }

    private static final int SIZE = 4 * 4;

    public float[] elements = new float[SIZE];

    private Matrix4f() {}

    /**
     * A utility method for creating a new identity matrix.
     * @return the identity matrix
     */

    public static Matrix4f identity() {

        Matrix4f result = new Matrix4f();

        for (int i = 0; i < SIZE; i++) {
            result.elements[i] = 0.0f;
        }

        result.elements[0 + 0 * 4] = 1.0f;

        result.elements[1 + 1 * 4] = 1.0f;

        result.elements[2 + 2 * 4] = 1.0f;

        result.elements[3 + 3 * 4] = 1.0f;

        return result;
    }

    /**
     * Create and return an orthographic matrix used for orthographic projection.
     * @param left the left-most coordinate of the viewing box.
     * @param right the right-most coordinate of the viewing box.
     * @param bottom the bottom-most coordinate of the viewing box.
     * @param top the top-most coordinate of the viewing box.
     * @param near the nearest coordinate of the viewing box.
     * @param far the furthest coordinate of the viewing box.
     * @return An orthographic Matrix4f object constructed with the given bounds.
     */

    public static Matrix4f orthographic(float left, float right, float bottom, float top, float near, float far) {

        Matrix4f result = identity();

        result.elements[0 + 0 * 4] = 2.0f / (right - left);

        result.elements[1 + 1 * 4] = 2.0f / (top - bottom);

        result.elements[2 + 2 * 4] = 2.0f / (near - far);

        result.elements[0 + 3 * 4] = (left + right) / (left - right);

        result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);

        result.elements[2 + 3 * 4] = (far + near) / (far - near);

        return result;
    }

    /**
     * Create and return a translation matrix used for moving objects' coordinates.
     * @param vector a Vector3f object indicating to vector of movement.
     * @return a translation Matrix4f object constructed from the given movement vector.
     */

    public static Matrix4f translate(Vector3f vector) {

        Matrix4f result = identity();

        result.elements[0 + 3 * 4] = vector.x;

        result.elements[1 + 3 * 4] = vector.y;

        result.elements[2 + 3 * 4] = vector.z;

        return result;
}

    /**
     * Create and return a rotation matrix used for rotating objects' coordinates.
     * @param angle the angle to rotate the objects, counter-clockwise.
     * @return a rotation Matrix4f object constructed from the given angle.
     */

    public static Matrix4f rotate(float angle) {

        Matrix4f result = identity();

        float radian = (float) Math.toRadians(angle);

        float cos = (float) Math.cos(radian);

        float sin = (float) Math.sin(radian);


        result.elements[0 + 0 * 4] = cos;

        result.elements[0 + 1 * 4] = -sin;

        result.elements[1 + 0 * 4] = sin;

        result.elements[1 + 1 * 4] = cos;

        return result;
    }

    /**
     * Multiply the current matrix with another matrix (matrix multiplication).
     * @param matrix the matrix to be multiplied with.
     * @return the resulting matrix.
     */

    public Matrix4f multiply(Matrix4f matrix) {

        Matrix4f result = new Matrix4f();

        for (int y = 0; y < 4; y++)
            for (int x = 0; x < 4; x++)
            {
                float sum = 0.0f;

                for (int d = 0; d < 4; d++)
                    sum += this.elements[x + d * 4] * matrix.elements[d + y * 4];

                result.elements[x + y * 4] = sum;
            }

        return result;
    }

    public Matrix4f add(Matrix4f matrix) {

        Matrix4f result = new Matrix4f();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                result.elements[x + y * 4] = elements[x + y * 4] + matrix.elements[x + y * 4];
            }
        }

        return result;
    }

    public void add_translation(Vector3f translation_vector) {

        elements[0 + 3 * 4] += translation_vector.x;
        elements[1 + 3 * 4] += translation_vector.y;
        elements[2 + 3 * 4] += translation_vector.z;
    }
}
