package edu.hcmiu.t3tr1s.client.logic;

import edu.hcmiu.t3tr1s.blocks.Shape;
import edu.hcmiu.t3tr1s.client.ShapeDataManager;
import edu.hcmiu.t3tr1s.enums.ShapeType;

import java.util.ArrayList;
import java.util.Random;

/**
 * A random shape generator for the game logic.
 * This class generate random permutation from the given bag of shapeTypes.
 */
public class ShapeRandomizer {
    private ArrayList<ShapeType> bag;
    private int current;

    public ShapeRandomizer(){
        bag = new ArrayList<>();
        current = 0;
    }

    /**
     * Constructor of ShapeRandomizer class.
     * @param bag the bag of shape types from which to generate from.
     */
    public ShapeRandomizer(ArrayList<ShapeType> bag) {
        this();
        this.bag = bag;
    }

    public boolean addToBag(ShapeType shapeType) {
        ShapeDataManager shapeDataManager = ShapeDataManager.getInstance();
        if (shapeDataManager.isValidID(shapeType)) {
            bag.add(shapeType);
            return true;
        }
        System.err.println("Invalid shape type!");
        return false;
    }

    public boolean removeFromBag(ShapeType shapeType) {
        if (bag.contains(shapeType)) {
            bag.remove(shapeType);
            return true;
        }
        System.err.println("Shape type not in bag!");
        return false;
    }

    public void setBag(ArrayList<ShapeType> bag) {
        this.bag = bag;
    }

    private void randomizeBag(){
        java.util.Collections.shuffle(bag);
    }

    public ShapeType getRandom() {
        if (!bag.isEmpty()) {
            ShapeType result = bag.get(current++);
            if (current >= bag.size()) {
                current = 0;
                randomizeBag();
            }
            return result;
        }
        System.err.println("Random bag is empty!");
        return null;
    }
}