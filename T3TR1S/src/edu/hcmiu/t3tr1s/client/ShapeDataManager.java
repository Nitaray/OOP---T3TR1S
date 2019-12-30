package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.blocks.logic.*;
import edu.hcmiu.t3tr1s.enums.ShapeBoxSize;
import edu.hcmiu.t3tr1s.utils.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShapeDataManager {
    private static ShapeDataManager ourInstance = new ShapeDataManager();

    public static ShapeDataManager getInstance() {
        return ourInstance;
    }

    private ShapeDataManager() {
        init();
    }

    private ArrayList<boolean[][]> dataIShape, dataOShape, dataLShape, dataJShape, dataSShape, dataZShape, dataTShape;

    private HashMap<Integer, String> shapeTextureName;
    private HashMap<Integer, String> blockTextureName;

    /**
     * Initialize shape data.
     */

    private void init() {
        dataIShape = new ArrayList<>();
        dataOShape = new ArrayList<>();
        dataLShape = new ArrayList<>();
        dataJShape = new ArrayList<>();
        dataSShape = new ArrayList<>();
        dataZShape = new ArrayList<>();
        dataTShape = new ArrayList<>();
        loadShapeData(dataIShape, "data/blocks/IShape.dat", ShapeBoxSize.FourByFour);
        loadShapeData(dataOShape, "data/blocks/OShape.dat", ShapeBoxSize.TwoByTwo);
        loadShapeData(dataLShape, "data/blocks/LShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(dataJShape, "data/blocks/JShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(dataSShape, "data/blocks/SShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(dataZShape, "data/blocks/ZShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(dataTShape, "data/blocks/TShape.dat", ShapeBoxSize.ThreeByThree);

        shapeTextureName = new HashMap<>();
        blockTextureName = new HashMap<>();
        loadShapeTextureName("config/shapeTexture.cfg");
        loadBlockTextureName("config/blockTexture.cfg");
    }

    /**
     * Load the data from data sourcePath file into a list of boolean arrays.
     * Each list has 4 arrays for 4 states of each shape.
     *
     * @param dataArrayList the list to be loaded with data.
     * @param sourcePath        the path to the sourcePath file.
     * @param boxSize       the size of the box of the shape.
     */
    private void loadShapeData(ArrayList<boolean[][]> dataArrayList, String sourcePath, ShapeBoxSize boxSize) {
        String dat = FileUtils.loadAsString(sourcePath);
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

                if (boxSize == ShapeBoxSize.TwoByTwo) {
                    final boolean[][] matrix = new boolean[3][4];
                    line = "";

                    for (int j = 0; j < 2; ++j)
                        line += scanner.nextLine().replaceAll("\\s+", "");


                    for (int i = 0; i < 2; ++i) {
                        for (int j = 0; j < 2; ++j)
                            matrix[i][j] = (Integer.parseInt(String.valueOf(line.charAt(2 * i + j))) == 1);
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
     * Load the name of shapes texture from shapeTexture.cfg into shapeTextureName.
     */
    private void loadShapeTextureName(String sourcePath) {
        String cfg = FileUtils.loadAsString(sourcePath);
        Scanner s = new Scanner(cfg);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            Scanner lineScanner = new Scanner(line);
            int ID = lineScanner.nextInt();
            lineScanner.next();
            String name = lineScanner.next();
            shapeTextureName.put(ID, name);
        }
    }

    /**
     * Load the name of shapes' blocks' textures from blockTexture.cfg into blockTextureName.
     * Similar to loadShapeTextureName.
     * TODO: By Nguyen Nhat Minh
     */
    private void loadBlockTextureName(String sourcePath) {

    }

    /**
     * Return the solid data for the given shape.
     *
     * @param logicShape a shape to get solid data for.
     * @return a 2D-boolean array containing the solid data of the shape.
     */
    public boolean[][] getStateData(LogicShape logicShape) {
        int state = logicShape.getState();

        if (logicShape instanceof ILogicShape) {
            return dataIShape.get(state);
        }
        if (logicShape instanceof OLogicShape) {
            return dataOShape.get(state);
        }
        if (logicShape instanceof LLogicShape) {
            return dataLShape.get(state);
        }
        if (logicShape instanceof JLogicShape) {
            return dataJShape.get(state);
        }
        if (logicShape instanceof SLogicShape) {
            return dataSShape.get(state);
        }
        if (logicShape instanceof ZLogicShape) {
            return dataZShape.get(state);
        }
        return dataTShape.get(state);
    }

    /**
     * Return the solid data for the given shape.
     *
     * @param logicShape a shape to get solid data for.
     * @param state the state of the given shape.
     * @return a 2D-boolean array containing the solid data of the shape.
     */
    public boolean[][] getStateData(LogicShape logicShape, int state) {
        if (logicShape instanceof ILogicShape) {
            return dataIShape.get(state);
        }
        if (logicShape instanceof OLogicShape) {
            return dataOShape.get(state);
        }
        if (logicShape instanceof LLogicShape) {
            return dataLShape.get(state);
        }
        if (logicShape instanceof JLogicShape) {
            return dataJShape.get(state);
        }
        if (logicShape instanceof SLogicShape) {
            return dataSShape.get(state);
        }
        if (logicShape instanceof ZLogicShape) {
            return dataZShape.get(state);
        }
        return dataTShape.get(state);
    }

    /**
     * Get the name of the texture of a given shape.
     * @param logicShape a shape to get texture name for.
     * @return the name of the texture.
     */
    public String getShapeTextureName(LogicShape logicShape) {
        int ID = logicShape.getID();
        if (shapeTextureName.containsKey(ID))
            return shapeTextureName.get(ID);
        System.err.println("Cannot find shape ID!");
        return "";
    }

    /**
     * Get the name of the texture of the blocks of a given shape.
     * @param logicShape a shape to get texture name for.
     * @return the name of the texture.
     */
    public String getBlockTextureName(LogicShape logicShape) {
        int ID = logicShape.getID();
        if (blockTextureName.containsKey(ID))
            return blockTextureName.get(ID);
        System.err.println("Cannot find shape ID!");
        return "";
    }
}
