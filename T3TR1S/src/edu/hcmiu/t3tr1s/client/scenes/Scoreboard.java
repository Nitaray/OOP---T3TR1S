package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.core.ShaderManager;
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

/**
 * The scoreboard scene for displaying past scores and high scores.
 * TODO: Implement by Nguyen Nhat Minh.
 */

public class Scoreboard extends Scene {
    private Button button;

    private ArrayList<ArrayList<Rectangle>> showScoresOnScreen;

    private static ArrayList<Integer> playerScores;

    Scoreboard(String name, Rectangle background) {
        super(name, background);
        initScoreList();
    }

    private Rectangle addDigit(int score, Vector3f topLeft, float width, float height, ShaderManager shaderManager) {
        String textureName = "";

        switch (score % 10) {
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

        return (new Rectangle(topLeft, width, height, "REGULAR_RECTANGLE", textureName, shaderManager));
    }

    private void initScoreboard(ShaderManager shaderManager) {
        showScoresOnScreen = new ArrayList<>();

        Vector3f topLeft = new Vector3f(10.0f, 1.0f, 0.5f);
        float width = 36.0f;
        float height = 12.0f;

        playerScores.forEach(score -> {
            showScoresOnScreen.add(new ArrayList<>());
            for (int i = score; i != 0; i /= 10) {
                showScoresOnScreen.get(playerScores.indexOf(score)).add(addDigit(score, topLeft, width, height, shaderManager));
            }
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

    public static void saveData() {
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

    public static void addScore(int score) {
        playerScores.add(score);
        saveData();
    }

    private void handleSelection(Client client) {
        button.action(client);
    }

    @Override
    public void show(Renderer renderer) {
        background.show(renderer);
        button.show(renderer);
    }

    @Override
    public void hide(Renderer renderer) {
        background.hide(renderer);
        button.hide(renderer);
    }

    @Override
    public void update(Client client) {
        if (Input.isKeyPress(GLFW_KEY_ENTER))
            handleSelection(client);
    }
}
