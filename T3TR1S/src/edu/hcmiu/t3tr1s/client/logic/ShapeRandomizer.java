package edu.hcmiu.t3tr1s.client.logic;

import java.util.Random;
/**
 * A random shape generator for the game logic.
 * This class should generate random permutation of the given length
 * from the given bag of shapes.
 * TODO: Implement by Nguyen Thi Hoai An.
 */

class ShapeRandomizer {
    private int[] bags;
    private static ShapeRandomizer shapeRandomizer = new ShapeRandomizer();
    private int init = 1; // To initialize bags' values
    private int order = 0;

    public static ShapeRandomizer getInstance(){
        return shapeRandomizer;
    }

    private ShapeRandomizer(){
        bags = new int[7];
        for (int i = 0;i<7;i++){
            bags[i] = init;
            init++;
        }
        randomizeBag();
    }

    private void randomizeBag(){
        for(int counter = 0; counter <7;counter++){
            swapBagsElements();
        }
    }

    private int generateRandomInt(){
        Random r = new Random();
        return r.nextInt(7);
    }

    private void swapBagsElements(){
        int r = generateRandomInt();
        int temp = bags[0];
        bags[0] = bags[r];
        bags[r] = temp;
    }

    public int getIntFromBag(){
        int i = bags[order];
        order++;
        if (order == 7){
            randomizeBag();
            order = 0;
        }
        return i;
    }

//    public void printBag(){
//        for(int bag: bags){
//            System.out.print(bag);
//        }
//    }


}
