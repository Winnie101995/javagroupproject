package application.asteroidsgameproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class HighScores_fx extends Application {

    private final Path highScoresFile = Paths.get("highscores.txt");

    @Override
    public void start(Stage stage) throws IOException {
        // Read the high scores from the file
        List<String> highScores = Files.readAllLines(highScoresFile);

        // Create a VBox to hold the high scores
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        // Set the background color to black
        root.setStyle("-fx-background-color: black;");

        // Create a label for the title
        Label title = new Label("High Scores");
        title.setStyle("-fx-font-size: 36; -fx-font-weight: bold; -fx-text-fill: white;");

        // Add the title to the root
        root.getChildren().add(title);

        // Create a label for each high score
        for (String highScore : highScores) {
            Label label = new Label(highScore);
            label.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
            root.getChildren().add(label);
        }

        // Create a scene with the root VBox
        Scene scene = new Scene(root, 400, 400);

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}