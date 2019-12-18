package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.blocks.Shape;

import java.util.ArrayList;

/**
 * A random shape generator for the game logic.
 * This class should generate random permutation of the given length
 * from the given bag of shapes.
 * TODO: Implement by Nguyen Thi Hoai An.
 */

class ShapeRandomizer {
    private ArrayList<Shape> bag;
    private int length;

    ShapeRandomizer(ArrayList<Shape> bag, int length) {
        this.bag = bag;
        this.length = length;
    }

    /**
     * Generate the next random permutation if the current one is empty.
     * Should do nothing if the current permutation has not been used up yet.
     */

    void randomize() {

    }

    /**
     * Get the next shape in the current permutation.
     * Delete that shape out of the current permutation.
     * @return
     */

//    Shape getNext() {
//        Shape next;
//        return next;
//    }

    /**
     * Add another shape to the bag.
     * @param shape the shape to be removed.
     */

    void addShape(Shape shape) {

    }

    /**
     * Remove a shape from the bag.
     * @param shape the shape to be removed.
     */

    void removeShape(Shape shape) {

    }
}
