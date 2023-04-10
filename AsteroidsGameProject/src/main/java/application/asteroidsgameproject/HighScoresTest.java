package application.asteroidsgameproject;


import java.io.IOException;

public class HighScoresTest {

    public static void main(String[] args) {
        HighScores_fx highScoresFx = new HighScores_fx();

        // Add example high scores
        try {
            highScoresFx.addNewHighScore(1000);
            highScoresFx.addNewHighScore(900);
            highScoresFx.addNewHighScore(800);
            highScoresFx.addNewHighScore(700);
            highScoresFx.addNewHighScore(600);
            highScoresFx.addNewHighScore(500);
            highScoresFx.addNewHighScore(400);
            highScoresFx.addNewHighScore(300);
            highScoresFx.addNewHighScore(200);
            highScoresFx.addNewHighScore(100);
        } catch (IOException e) {
            System.err.println("Error saving high score: " + e.getMessage());
        }

        // Run the HighScores_fx application
        HighScores_fx.main(args);
    }
}

