package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.client.buttons.ReturnToMenuButton;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.math.Vector3f;
import edu.hcmiu.t3tr1s.utils.FileUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ENTER;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;

/**
 * The scoreboard scene for displaying past scores and high scores.
 * TODO: Implement by Nguyen Nhat Minh.
 */

public class Scoreboard extends Scene {

    private final Button returnButton;
    private final Rectangle board;

    private static ArrayList<Integer> playerScores;

    private ArrayList<ArrayList<Rectangle>> showScoresOnScreen;

    public Scoreboard(String name) {
        super(name);

        setBackground(new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "MENU_BACKGROUND"));

        //TODO: need texture button and scoreboard
        returnButton = new ReturnToMenuButton(new Vector3f(120.0f, 35.0f, 0.1f), 36.0f, 12.0f,
                "REGULAR_RECTANGLE", "START_BUTTON", "START_BUTTON_SELECTED", true);
        board = new Rectangle(new Vector3f(0, 90.0f, 0.0f), 160.0f, 90.0f,
                "REGULAR_RECTANGLE", "HIGH_SCORE_BOARD");

        initScoreList();
        initScoreboard();
    }

    @Override
    protected void handleKeyPress() {

    }

    private Rectangle addDigit(int number, Vector3f topLeft, float width, float height) {
        String textureName = "";

        switch (number % 10) {
            case 1:
                textureName = "DIGIT_ONE";
                break;
            case 2:
                textureName = "DIGIT_TWO";
                break;
            case 3:
                textureName = "DIGIT_THREE";
                break;
            case 4:
                textureName = "DIGIT_FOUR";
                break;
            case 5:
                textureName = "DIGIT_FIVE";
                break;
            case 6:
                textureName = "DIGIT_SIX";
                break;
            case 7:
                textureName = "DIGIT_SEVEN";
                break;
            case 8:
                textureName = "DIGIT_EIGHT";
                break;
            case 9:
                textureName = "DIGIT_NINE";
                break;
            case 0:
                textureName = "DIGIT_ZERO";
                break;
        }

        return (new Rectangle(topLeft, width, height, "REGULAR_RECTANGLE", textureName));
    }

    private void initScoreboard() {
        showScoresOnScreen = new ArrayList<>();

        Vector3f topLeft = new Vector3f(10.0f, 1.0f, 0.5f);
        Vector3f columnChange = new Vector3f(-2.0f, 0f, 0f);
        Vector3f rowChange = new Vector3f(0f, -5.0f, 0f);

        float width = 36.0f;
        float height = 12.0f;

        playerScores.forEach(score -> {
            showScoresOnScreen.add(new ArrayList<>());
            Vector3f position = topLeft;
            for (int number = score; number != 0; number /= 10) {
                showScoresOnScreen.get(playerScores.indexOf(score)).add(addDigit(number, position, width, height));
                position.add(columnChange);
            }
            topLeft.add(rowChange);
        });
    }

    private static void loadData() {
        String dat = FileUtils.loadAsString("data/client/score.dat");
        Scanner scanner = new Scanner(dat);
        while (scanner.hasNextLine()) {
            String score = scanner.nextLine();
            playerScores.add(Integer.parseInt(score));
        }
        refreshScoreList();
    }

    private static void saveData() {
        refreshScoreList();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("data/client/score.dat"));

            for (Integer score : playerScores) {
                writer.write(score.toString() + '\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ioe) {
                }
            }
        }
    }

    private static void refreshScoreList() {
        Collections.sort(playerScores, Collections.reverseOrder());
        playerScores.removeIf(score -> playerScores.indexOf(score) >= 10);
    }

    private static void initScoreList() {
        playerScores = new ArrayList<>();
        loadData();
    }

    public static void addPlayerScore(int score) {
        playerScores.add(score);
        saveData();
    }

    public static void setPlayerScores(ArrayList<Integer> playerScores) {
        Scoreboard.playerScores = playerScores;
        saveData();
    }

    @Override
    public void show() {
        background.show();
        returnButton.show();
        board.show();

        showScoresOnScreen.forEach(score -> {
            score.forEach(digit -> digit.show());
        });
    }

    @Override
    public void hide() {
        background.hide();
        returnButton.hide();
        board.hide();

        showScoresOnScreen.forEach(score -> {
            score.forEach(digit -> digit.hide());
        });
    }

    @Override
    public void update() {
        returnButton.update();

        if ((Input.isKeyDown(GLFW_KEY_ENTER) || Input.isKeyDown(GLFW_KEY_ESCAPE)) && keyCooled(300 * MILLISECONDS))
            handleSelection();
    }
}
