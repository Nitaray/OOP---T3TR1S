package edu.hcmiu.t3tr1s.core;

import edu.hcmiu.t3tr1s.blocks.*;
import edu.hcmiu.t3tr1s.enums.ShapeBoxSize;
import edu.hcmiu.t3tr1s.utils.FileUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class ShapeDataManager {
    private static ShapeDataManager ourInstance = new ShapeDataManager();

    public static ShapeDataManager getInstance() {
        return ourInstance;
    }

    private ShapeDataManager() {
    }

    private static ArrayList<boolean[][]> dataIShape, dataOShape, dataLShape, dataJShape, dataSShape, dataZShape, dataTShape;

    /**
     * Load the data from data source file into a list of boolean arrays.
     * Each list has 4 arrays for 4 states of each shape.
     * @param dataArrayList the list to be loaded with data.
     * @param source the path to the source file.
     * @param boxSize the size of the box of the shape.
     */

    private static void loadData(ArrayList<boolean[][]> dataArrayList, String source, ShapeBoxSize boxSize) {

        //TODO: IMPLEMENT A METHOD TO INPUT DATA FROM source INTO dataArrayList
        //TODO: By Nguyen Nhat Minh
    }

    /**
     * Initialize shape data.
     */

    public static void init() {
        dataIShape = new ArrayList<>();
        dataOShape = new ArrayList<>();
        dataLShape = new ArrayList<>();
        dataJShape = new ArrayList<>();
        dataSShape = new ArrayList<>();
        dataZShape = new ArrayList<>();
        dataTShape = new ArrayList<>();
        loadData(dataIShape, "data/blocks/IShape.dat", ShapeBoxSize.FourByFour);
        loadData(dataOShape, "data/blocks/OShape.dat", ShapeBoxSize.FourByThree);
        loadData(dataLShape, "data/blocks/LShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataJShape, "data/blocks/JShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataSShape, "data/blocks/SShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataZShape, "data/blocks/ZShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataTShape, "data/blocks/TShape.dat", ShapeBoxSize.ThreeByThree);
    }

    /**
     * Return the solid data for the given shape.
     * @param shape a shape to get solid data for.
     * @return a 2D-boolean array containing the solid data of the shape.
     */

    public static boolean[][] getStateData(Shape shape) {
        int state = shape.getState();

        if (shape instanceof IShape) {
            return dataIShape.get(state);
        }
        if (shape instanceof OShape) {
            return dataOShape.get(state);
        }
        if (shape instanceof LShape) {
            return dataLShape.get(state);
        }
        if (shape instanceof JShape) {
            return dataJShape.get(state);
        }
        if (shape instanceof SShape) {
            return dataSShape.get(state);
        }
        if (shape instanceof ZShape) {
            return dataZShape.get(state);
        }
        return dataTShape.get(state);
    }
}
