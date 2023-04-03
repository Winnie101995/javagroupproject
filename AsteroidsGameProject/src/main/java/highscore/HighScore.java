//
//package highscore;
//
//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.layout.*;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
//import javafx.stage.Stage;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import javafx.scene.image.ImageView;
//
//public class HighScore extends Application {
//    @Override
//    public void start(Stage scoreMenu) throws IOException {
//        // Create the score menu screen
//        VBox scoreLayout = createScoreLayout(scoreMenu);
//        Scene scorescene = new Scene(scoreLayout, 1200, 700);
//        // Set the title of the main menu
//        scoreMenu.setTitle("High Score");
//        // Set the score menu scene
//        scoreMenu.setScene(scorescene);
//        // Show the score menu
//        scoreMenu.show();
//    }
//    private VBox createScoreLayout(Stage scoreMenu) {
//        // Create the title label
//        Label scoreLabel = new Label("High Score");
//        // Set the font size of the title label
//        scoreLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 60; " +
//                "-fx-font-weight: bold; -fx-font-family: 'Arial'");
//        // Create the "High Scores" button
//        Button highScoresButton = new Button("TOP Scores");
//        // Set the font size of the high scores button
//        highScoresButton.setStyle("-fx-text-fill: #000000; -fx-font-size: 36; " +
//                "-fx-font-family: 'Arial'");
//        highScoresButton.setOnAction(e -> {
//            // Create the high scores layout
//            VBox highScoresLayout = createHighScoreLayout(scoreMenu);
//            Scene highScoresScene = new Scene(highScoresLayout, 1200, 700);
//            // Set the high scores scene
//            scoreMenu.setScene(highScoresScene);
//        });
//
//        // Add the title label and buttons to the layout
//        // Add the title, play button, high scores button, and controls button to the layout
//        VBox layout = new VBox(20, scoreLabel, highScoresButton);
//        // Set the background color of the layout
//        layout.setStyle("-fx-background-color: #000000");
//        // Set the alignment of the layout to center
//        layout.setAlignment(Pos.CENTER);
//        // Return the layout
//        return layout;
//    }
//
//    private VBox createHighScoreLayout(Stage scoreMenu) {
//        // Create the title label
//        Label scoreLabel = new Label("High Score");
//        // Set the font size of the title label
//        scoreLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 60; " +
//                "-fx-font-weight: bold; -fx-font-family: 'Arial'");
//        // Create the "High Scores" content
//        Label highScoresContent = new Label("""
//                Rank 1: 1000 points
//                Rank 2: 900 points
//                Rank 3: 800 points
//                Rank 4: 700 points
//                Rank 5: 600 points
//                Rank 6: 500 points
//                Rank 7: 400 points
//                Rank 8: 300 points
//                Rank 9: 200 points
//                Rank 10: 100 points""");
//
//        // Set the font size of the high scores content
//        highScoresContent.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 36; " +
//                "-fx-font-family: 'Arial'");
//        // Add the controls label, text, and back button to the layout
//        VBox layout = new VBox(30, scoreLabel, highScoresContent);
//        // Set the background color of the layout
//        layout.setStyle("-fx-background-color: #000000");
//        layout.setAlignment(Pos.CENTER);
//        return layout;
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}