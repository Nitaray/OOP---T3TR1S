package edu.hcmiu.t3tr1s.client.logic;

import edu.hcmiu.t3tr1s.blocks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A random shape generator for the game logic.
 * This class should generate random permutation of the given length
 * from the given bag of shapes.
 * TODO: Implement by Nguyen Thi Hoai An.
 */

class ShapeRandomizer {

    private static int length;
    private ArrayList<Shape> bag;
    private static int randomNum;

    /**
    public static void setLength(int length) {
        ShapeRandomizer.length = length;
    }
     */

    ShapeRandomizer(ArrayList<Shape> bag, int length) {
        this.bag = bag;
        this.length = length;
    }

    private static int getRandomNumberInRange(int length) {
        Random r = new Random();
        return r.nextInt((7 - 1) + 1) + 1;
    }


    public void shapeRandomizer() {
        Shape[] shapes = new Shape[7];
        List<Integer> listOfRandomInt = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            if (i == 0 || i >= 7) {
                randomNum = getRandomNumberInRange(7);
                listOfRandomInt.add(randomNum);
            }
            if (i > 0 && i < 7) {
                randomNum = getRandomNumberInRange(7);
                while (listOfRandomInt.contains(randomNum)) {
                    randomNum = getRandomNumberInRange(7);
                }
                listOfRandomInt.add(randomNum);
            }
        }

        shapes[0] = new IShape();
        shapes[1] = new JShape();
        shapes[2] = new LShape();
        shapes[3] = new OShape();
        shapes[4] = new SShape();
        shapes[5] = new TShape();
        shapes[6] = new ZShape();

        for (int j = 0; j < length; j++) {
            bag.add(shapes[listOfRandomInt.get(j)]);
        }
    }

    public void remove(Shape input) {
        for (int j = 0; j < bag.size(); ++j)
        {
            if (bag.get(j) == input) {
                bag.remove(j);
                break;
            } else {
                continue;
            }
        }
    }
}
