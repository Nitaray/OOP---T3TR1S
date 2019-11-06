package edu.hcmiu.t3tr1s.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * A utility class for creating buffer from array.
 */

public class BufferUtils {

    private BufferUtils() {
    }

    /**
     * Create a byte buffer from an array of bytes.
     * @param array an array of bytes.
     * @return a ByteBuffer object constructed from the array of bytes.
     */

    public static ByteBuffer createByteBuffer(byte[] array) {

        ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());

        result.put(array).flip();

        return result;
    }

    /**
     * Create a float buffer from an array of floats.
     * @param array an array of floats.
     * @return a FloatBuffer object constructed from the array of floats.
     */

    public static FloatBuffer createFloatBuffer(float[] array) {

        FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();

        result.put(array).flip();

        return result;
    }

    /**
     * Create an integer buffer from an array of integers.
     * @param array an array of integers.
     * @return an IntegerBuffer object constructed from the array of integers.
     */

    public static IntBuffer createIntBuffer(int[] array) {

        IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();

        result.put(array).flip();

        return result;
    }
}
