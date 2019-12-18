package edu.hcmiu.t3tr1s.client.scenes;

import edu.hcmiu.t3tr1s.client.Client;
import edu.hcmiu.t3tr1s.core.Renderer;
import edu.hcmiu.t3tr1s.graphics.Rectangle;
import edu.hcmiu.t3tr1s.utils.FileUtils;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The scoreboard scene for displaying past scores and high scores.
 * TODO: Implement by Nguyen Nhat Minh.
 */

public class Scoreboard extends Scene{
    private static ArrayList<String> playerScores;

    Scoreboard(String name, Rectangle background) {
        super(name, background);
    }

    private static void loadData(ArrayList<String> playerScores) {
        String dat = FileUtils.loadAsString("data/client/score.dat");
        Scanner scanner = new Scanner(dat);
        while (scanner.hasNextLine()) {
            String score = scanner.nextLine();
            playerScores.add(score);
        }
    }

    static void initScoreList() {
        playerScores = new ArrayList<>();
        loadData(playerScores);
    }

    @Override
    public void show(Renderer renderer) {

    }

    @Override
    public void hide(Renderer renderer) {

    }

    @Override
    public void update(Client client) {

    }
}
