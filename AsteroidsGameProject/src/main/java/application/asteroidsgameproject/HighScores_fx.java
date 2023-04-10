package application.asteroidsgameproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class HighScores_fx extends Application {
    private List<String> highScores;
    private final Path highScoresFile = Paths.get("high_scores.txt");

    @Override
    public void start(Stage scoreMenu) throws IOException {
        loadHighScores();
        music();

        // Create the score menu screen
        VBox scoreLayout = createScoreLayout(scoreMenu);
        Scene scorescene = new Scene(scoreLayout, 1200, 700);
        // Set the title of the main menu
        scoreMenu.setTitle("High Score");
        // Set the score menu scene
        scoreMenu.setScene(scorescene);
        // Show the score menu
        scoreMenu.show();
    }

    MediaPlayer mediaPlayer;
    public void music(){
        String s = "/Users/liujinhao/Documents/GitHub/javagroupproject/file_example_MP3_1MG.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setAutoPlay(true);
    }

    private void loadHighScores() throws IOException {
        if (!Files.exists(highScoresFile)) {
            Files.createFile(highScoresFile);
        }
        highScores = Files.readAllLines(highScoresFile);
    }

    private void saveHighScores() throws IOException {
        Files.write(highScoresFile, highScores, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private VBox createScoreLayout(Stage scoreMenu) {
        // Create the title label
        Label scoreLabel = new Label("High Score");
        // Set the font size of the title label
        scoreLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 60; " +
                "-fx-font-weight: bold; -fx-font-family: 'Arial'");
        // Create the "High Scores" button
        Button highScoresButton = new Button("TOP Scores");
        // Set the font size of the high scores button
        highScoresButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 36; " +
                "-fx-font-family: 'Arial'");
        highScoresButton.setOnAction(e -> {
            // Create the high scores layout
            VBox highScoresLayout = createHighScoreLayout(scoreMenu);
            Scene highScoresScene = new Scene(highScoresLayout, 1200, 700);
            // Set the high scores scene
            scoreMenu.setScene(highScoresScene);
        });

        // Add the title label and buttons to the layout
        // Add the title, play button, high scores button, and controls button to the layout
        VBox layout = new VBox(20, scoreLabel, highScoresButton);
        // Set the background color of the layout
        layout.setStyle("-fx-background-color: #000000");
        // Set the alignment of the layout to center
        layout.setAlignment(Pos.CENTER);
        // Return the layout
        return layout;
    }


    private VBox createHighScoreLayout(Stage scoreMenu) {
        // Create the title label
        Label scoreLabel = new Label("High Score");
        // Set the font size of the title label
        scoreLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 60; " +
                "-fx-font-weight: bold; -fx-font-family: 'Arial'");
        // Create the "High Scores" content
        Label highScoresContent = new Label(formatHighScores());

        // Set the font size of the high scores content
        highScoresContent.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 36; " +
                "-fx-font-family: 'Arial'");
        // Add the controls label, text, and back button to the layout
        VBox layout = new VBox(30, scoreLabel, highScoresContent);
        // Set the background color of the layout
        layout.setStyle("-fx-background-color: #000000");
        layout.setAlignment(Pos.CENTER);
        return layout;
    }

    private String formatHighScores() {
        StringBuilder formattedHighScores = new StringBuilder();
        for (int i = 0; i < highScores.size(); i++) {
            formattedHighScores.append("Rank ").append(i + 1).append(": ").append(highScores.get(i)).append(" points\n");
        }
        return formattedHighScores.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void addNewHighScore(int newScore) throws IOException {
        // Add the new high score to the highScores list
        highScores.add(Integer.toString(newScore));

        // Sort the highScores list in descending order
        highScores.sort((s1, s2) -> Integer.parseInt(s2) - Integer.parseInt(s1));

        // Keep only the top 10 high scores
        if (highScores.size() > 10) {
            highScores = highScores.subList(0, 10);
        }

        // Save the updated highScores list to the file
        saveHighScores();
    }

    public HighScores_fx() {
        highScores = new ArrayList<>();
        try {
            loadHighScores();
        } catch (IOException e) {
            System.err.println("Error loading high scores: " + e.getMessage());
        }
    }

}

//    There is a game over method in the game logic class
//    where you pass the new high score to the HighScores_fx2 class
//    and call the addNewHighScore method.
//public void gameOver(int finalScore) {
//    // ... Other game over logic ...
//
//    // Create an instance of the HighScores_fx class (or use an existing instance)
//    HighScores_fx2 highScoresFx = new HighScores_fx2();
//
//    // Add the new high score
//    try {
//        highScoresFx.addNewHighScore(finalScore);
//    } catch (IOException e) {
//        System.err.println("Error saving high score: " + e.getMessage());
//    }
//}