//package edu.hcmiu.t3tr1s.client.logic;
//
//import edu.hcmiu.t3tr1s.blocks.*;
//import edu.hcmiu.t3tr1s.blocks.logic.*;
//import sun.rmi.runtime.Log;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
///**
// * A random shape generator for the game logic.
// * This class should generate random permutation of the given length
// * from the given bag of shapes.
// * TODO: Implement by Nguyen Thi Hoai An.
// */
//
//class ShapeRandomizer {
//
//    private static int length;
//    private ArrayList<LogicShape> bag;
//    private static int randomNum;
//
//    /**
//    public static void setLength(int length) {
//        ShapeRandomizer.length = length;
//    }
//     */
//
//    ShapeRandomizer(ArrayList<LogicShape> bag, int length) {
//        this.bag = bag;
//        this.length = length;
//    }
//
//    private static int getRandomNumberInRange(int length) {
//        Random r = new Random();
//        return r.nextInt((7 - 1) + 1) + 1;
//    }
//
//
//    public void shapeRandomizer() {
//        LogicShape[] shapes = new LogicShape[][7];
//        List<Integer> listOfRandomInt = new ArrayList<>();
//
//        for (int i = 0; i < length; i++) {
//            if (i == 0 || i >= 7) {
//                randomNum = getRandomNumberInRange(7);
//                listOfRandomInt.add(randomNum);
//            }
//            if (i > 0 && i < 7) {
//                randomNum = getRandomNumberInRange(7);
//                while (listOfRandomInt.contains(randomNum)) {
//                    randomNum = getRandomNumberInRange(7);
//                }
//                listOfRandomInt.add(randomNum);
//            }
//        }
//
//        shapes[0] = new ILogicShape();
//        shapes[1] = new JLogicShape();
//        shapes[2] = new LLogicShape();
//        shapes[3] = new OLogicShape();
//        shapes[4] = new SLogicShape();
//        shapes[5] = new TLogicShape();
//        shapes[6] = new ZLogicShape();
//
//        for (int j = 0; j < length; j++) {
//            bag.add(shapes[listOfRandomInt.get(j)]);
//        }
//    }
//
//    public void remove(LogicShape input) {
//        for (int j = 0; j < bag.size(); ++j)
//        {
//            if (bag.get(j) == input) {
//                bag.remove(j);
//                break;
//            } else {
//                continue;
//            }
//        }
//    }
//}
