package edu.hcmiu.t3tr1s.client;

import edu.hcmiu.t3tr1s.blocks.logic.*;
import edu.hcmiu.t3tr1s.enums.ShapeBoxSize;
import edu.hcmiu.t3tr1s.enums.ShapeType;
import edu.hcmiu.t3tr1s.utils.FileUtils;
import javafx.util.Pair;

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

    private HashMap<ShapeType, String> shapeTextureName;
    private HashMap<ShapeType, String> ghostTextureName;
    private HashMap<ShapeType, String> blockTextureName;

    private HashMap<ShapeType, ArrayList<boolean[][]>> shapeDataMap;
    private HashMap<ShapeType, ArrayList<ArrayList<Pair<Integer, Integer>>>> shapeOffsetMap;

    /**
     * Initialize shape data.
     */
    private void init() {
        shapeDataMap = new HashMap<>();
        loadShapeData(ShapeType.I, "data/blocks/IShape.dat", ShapeBoxSize.FourByFour);
        loadShapeData(ShapeType.O, "data/blocks/OShape.dat", ShapeBoxSize.FourByFour);
        loadShapeData(ShapeType.J, "data/blocks/JShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(ShapeType.L, "data/blocks/LShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(ShapeType.Z, "data/blocks/ZShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(ShapeType.S, "data/blocks/SShape.dat", ShapeBoxSize.ThreeByThree);
        loadShapeData(ShapeType.T, "data/blocks/TShape.dat", ShapeBoxSize.ThreeByThree);

        shapeOffsetMap = new HashMap<>();
        loadShapeOffset(ShapeType.I, "data/offset/IShape.dat");
        loadShapeOffset(ShapeType.O, "data/offset/OShape.dat");
        loadShapeOffset(ShapeType.J, "data/offset/JShape.dat");
        loadShapeOffset(ShapeType.L, "data/offset/LShape.dat");
        loadShapeOffset(ShapeType.Z, "data/offset/ZShape.dat");
        loadShapeOffset(ShapeType.S, "data/offset/SShape.dat");
        loadShapeOffset(ShapeType.T, "data/offset/TShape.dat");


        shapeTextureName = new HashMap<>();
        ghostTextureName = new HashMap<>();
        blockTextureName = new HashMap<>();
        readShapeTextureName("config/shapeTexture.cfg");
        readGhostTextureName("config/ghostShapeTexture.cfg");
        readBlockTextureName("config/blockTexture.cfg");
    }

    /**
     * Load the shape data from data files into a map for easy access.
     * @param shapeType Type of the shape.
     * @param sourcePath Directory path of the data file.
     * @param shapeBoxSize Size of the shape box.
     */
    private void loadShapeData(ShapeType shapeType, String sourcePath, ShapeBoxSize shapeBoxSize) {
        ArrayList<boolean[][]> dataList = new ArrayList<>();
        readShapeData(dataList, sourcePath, shapeBoxSize);
        shapeDataMap.put(shapeType, dataList);
    }

    /**
     * Load shape offset data into a map for easy access.
     * @param shapeType Type of the shape.
     * @param sourcePath Directory path of the data file.
     */
    private void loadShapeOffset(ShapeType shapeType, String sourcePath) {
        ArrayList<ArrayList<Pair<Integer, Integer>>> offsetList = new ArrayList<>();
        readShapeOffset(offsetList, sourcePath);
        shapeOffsetMap.put(shapeType, offsetList);
    }

    /**
     * Load the data from data sourcePath file into a list of boolean arrays.
     * Each list has 4 arrays for 4 states of each shape.
     *
     * @param dataArrayList the list to be loaded with data.
     * @param sourcePath        the path to the sourcePath file.
     * @param boxSize       the size of the box of the shape.
     */
    private void readShapeData(ArrayList<boolean[][]> dataArrayList, String sourcePath, ShapeBoxSize boxSize) {
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
                    final boolean[][] matrix = new boolean[2][2];
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
     * Performs the data file read to load shape offset data into a container.
     * @param shapeOffsetList The container to store the offset data.
     * @param sourcePath The directory path of the data file.
     */
    private void readShapeOffset(ArrayList<ArrayList<Pair<Integer, Integer>>> shapeOffsetList, String sourcePath) {
        String dat = FileUtils.loadAsString(sourcePath);
        Scanner scanner = new Scanner(dat);
        String line;

        while (scanner.hasNextLine()) {
            ArrayList<Pair<Integer, Integer>> stateOffset = new ArrayList<>();

            line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            for (int i = 0; i < 5; i++) {
                int xOffset = lineScanner.nextInt();
                int yOffset = lineScanner.nextInt();
                Pair<Integer, Integer> offsetTestData = new Pair<>(xOffset, yOffset);
                stateOffset.add(offsetTestData);
            }

            shapeOffsetList.add(stateOffset);
        }
    }

    /**
     * Load the name of shapes texture from data files into a container.
     * @param sourcePath The directory path of the data file.
     */
    private void readShapeTextureName(String sourcePath) {
        readTextureName(sourcePath, shapeTextureName);
    }

    /**
     * Load the name of shapes ghost texture from data files into a container.
     * @param sourcePath The directory path of the data file.
     */
    private void readGhostTextureName(String sourcePath) {
        readTextureName(sourcePath, ghostTextureName);
    }

    /**
     * Load the name of block textures from data files into a container.
     * @param sourcePath The directory path of the data file.
     */
    private void readBlockTextureName(String sourcePath) {
        readTextureName(sourcePath, blockTextureName);
    }

    private void readTextureName(String sourcePath, HashMap<ShapeType, String> textureNameMap) {
        String cfg = FileUtils.loadAsString(sourcePath);
        Scanner s = new Scanner(cfg);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            Scanner lineScanner = new Scanner(line);
            String type = lineScanner.next();
            lineScanner.next();
            String name = lineScanner.next();
            textureNameMap.put(ShapeType.valueOf(type), name);
        }
    }

    /**
     * Return the solid data for the given shape.
     *
     * @param logicShape a shape to get solid data for.
     * @return a 2D-boolean array containing the solid data of the shape.
     */
    public boolean[][] getStateData(LogicShape logicShape) {
        int state = logicShape.getState();
        ShapeType ID = logicShape.getType();

        if (isValidID(ID)) {
            ArrayList<boolean[][]> dataList = shapeDataMap.get(ID);
            if (dataList.size() > state)
                return dataList.get(state);
            else {
                System.err.println("Invalid shape state!");
                return null;
            }
        }
        System.err.println("Invalid shape ID!");
        return null;
    }

    public ArrayList<ArrayList<Pair<Integer, Integer>>> getShapeOffset(ShapeType shapeType) {
        if (isValidID(shapeType))
            return shapeOffsetMap.get(shapeType);
        System.err.println("Invalid shape type!");
        return null;
    }

    /**
     * Get the name of the texture of a given shape.
     * @param logicShape a shape to get texture name for.
     * @return the name of the texture.
     */
    public String getShapeTextureName(LogicShape logicShape) {
        ShapeType type = logicShape.getType();
        if (shapeTextureName.containsKey(type))
            return shapeTextureName.get(type);
        System.err.println("Cannot find shape type!");
        return "";
    }

    /**
     * Get the name of the texture of a given shape.
     * @param shapeType type of shape.
     * @return the name of the texture.
     */
    public String getShapeTextureName(ShapeType shapeType) {
        if (shapeTextureName.containsKey(shapeType))
            return shapeTextureName.get(shapeType);
        System.err.println("Cannot find shape type!");
        return "";
    }

    /**
     * Get the name of the ghost texture of a given shape.
     * @param logicShape a shape to get texture name for.
     * @return the name of the ghost texture.
     */
    public String getGhostTextureName(LogicShape logicShape) {
        ShapeType type = logicShape.getType();
        if (ghostTextureName.containsKey(type))
            return ghostTextureName.get(type);
        System.err.println("Cannot find shape type!");
        return "";
    }

    /**
     * Get the name of the ghost texture of a given shape.
     * @param shapeType type of shape.
     * @return the name of the ghost texture.
     */
    public String getGhostTextureName(ShapeType shapeType) {
        if (ghostTextureName.containsKey(shapeType))
            return ghostTextureName.get(shapeType);
        System.err.println("Cannot find shape type!");
        return "";
    }

    /**
     * Get the name of the texture of the blocks of a given shape.
     * @param logicShape a shape to get texture name for.
     * @return the name of the texture.
     */
    public String getBlockTextureName(LogicShape logicShape) {
        ShapeType type = logicShape.getType();
        if (blockTextureName.containsKey(type))
            return blockTextureName.get(type);
        System.err.println("Cannot find shape type!");
        return "";
    }

    /**
     * Get the name of the texture of the blocks of a given shape.
     * @param shapeType type of shape.
     * @return the name of the texture.
     */
    public String getBlockTextureName(ShapeType shapeType) {
        if (blockTextureName.containsKey(shapeType))
            return blockTextureName.get(shapeType);
        System.err.println("Cannot find shape type!");
        return "";
    }

    /**
     * Check if the given ID is valid or not.
     * @param ID the ID to be checked.
     * @return True if valid, false otherwise.
     */
    public boolean isValidID(ShapeType ID) {
        return shapeDataMap.containsKey(ID);
    }
}
