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
     *
     * @param dataArrayList the list to be loaded with data.
     * @param source        the path to the source file.
     * @param boxSize       the size of the box of the shape.
     */

    private static void loadData(ArrayList<boolean[][]> dataArrayList, String source, ShapeBoxSize boxSize) {
        String dat = FileUtils.loadAsString(source);
        Scanner scanner = new Scanner(dat);
        String line;

        while (scanner.hasNextLine()) {
            for (int state = 0; state < 4; ++state) {
                if (boxSize == ShapeBoxSize.FourByFour) {
                    final boolean[][] matrix = new boolean[4][4];
                    line = "";

                    for (int j = 0; j < 4; ++j)
                        line += scanner.nextLine().replaceAll("\\s+", "");


                    for (int i = 0; i < 4; ++i) {
                        for (int j = 0; j < 4; ++j)
                            matrix[i][j] = (Integer.parseInt(String.valueOf(line.charAt(4 * i + j))) == 1);
                    }

                    dataArrayList.add(matrix);
                }

                if (boxSize == ShapeBoxSize.FourByThree) {
                    final boolean[][] matrix = new boolean[3][4];
                    line = "";

                    for (int j = 0; j < 3; ++j)
                        line += scanner.nextLine().replaceAll("\\s+", "");


                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 4; ++j)
                            matrix[i][j] = (Integer.parseInt(String.valueOf(line.charAt(4 * i + j))) == 1);
                    }

                    dataArrayList.add(matrix);
                }

                if (boxSize == ShapeBoxSize.ThreeByThree) {
                    final boolean[][] matrix = new boolean[3][3];
                    line = "";

                    for (int j = 0; j < 3; ++j)
                        line += scanner.nextLine().replaceAll("\\s+", "");


                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 3; ++j)
                            matrix[i][j] = (Integer.parseInt(String.valueOf(line.charAt(3 * i + j))) == 1);
                    }

                    dataArrayList.add(matrix);
                }

                if (scanner.hasNextLine())
                    scanner.nextLine();
            }
        }
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
        loadData(dataIShape, "T3TR1S/data/blocks/IShape.dat", ShapeBoxSize.FourByFour);
        loadData(dataOShape, "T3TR1S/data/blocks/OShape.dat", ShapeBoxSize.FourByThree);
        loadData(dataLShape, "T3TR1S/data/blocks/LShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataJShape, "T3TR1S/data/blocks/JShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataSShape, "T3TR1S/data/blocks/SShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataZShape, "T3TR1S/data/blocks/ZShape.dat", ShapeBoxSize.ThreeByThree);
        loadData(dataTShape, "T3TR1S/data/blocks/TShape.dat", ShapeBoxSize.ThreeByThree);
    }

    /**
     * Return the solid data for the given shape.
     *
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

    public static boolean[][] getStateData(Shape shape, int state) {
        if (shape instanceof IShape) {
            return dataIShape.get(state);
        }
        if (shape instanceof OShape) {
            return dataIShape.get(state);
        }
        if (shape instanceof LShape) {
            return dataIShape.get(state);
        }
        if (shape instanceof JShape) {
            return dataIShape.get(state);
        }
        if (shape instanceof SShape) {
            return dataIShape.get(state);
        }
        if (shape instanceof ZShape) {
            return dataIShape.get(state);
        }
        return dataTShape.get(state);
    }
}
