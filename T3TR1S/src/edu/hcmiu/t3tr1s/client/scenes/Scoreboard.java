package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.client.buttons.Button;
import edu.hcmiu.t3tr1s.core.Input;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
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

    private static ArrayList<Integer> playerScores;

    Scoreboard(String name, Rectangle background) {
        super(name, background);
        initScoreList();
    }

    private static void loadData(ArrayList<Integer> playerScores) {
        String dat = FileUtils.loadAsString("data/client/score.dat");
        Scanner scanner = new Scanner(dat);
        while (scanner.hasNextLine()) {
            String score = scanner.nextLine();
            playerScores.add(Integer.parseInt(score));
        }
        Collections.sort(playerScores, Collections.reverseOrder());
    }

    public static void saveData(ArrayList<Integer> playerScores) {
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

    static void initScoreList() {
        playerScores = new ArrayList<>();
        loadData(playerScores);
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
