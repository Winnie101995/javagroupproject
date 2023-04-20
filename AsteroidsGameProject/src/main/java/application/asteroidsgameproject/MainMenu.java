package application.asteroidsgameproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;

public class MainMenu extends Application {
    public static int WIDTH;
    public static int HEIGHT;

    @Override
    public void start(Stage primaryStage) {

        // Get the width and height of the screen
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D bounds = primaryScreen.getBounds();
        WIDTH = (int) bounds.getWidth();
        HEIGHT = (int) bounds.getHeight();

        // Set the background image to the spaceBackground
        Image backgroundImage = new Image("/spaceBackground.png");
        BackgroundSize backgroundSize = new BackgroundSize(WIDTH, HEIGHT, true, true, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        // Create the main menu scene
        StackPane root = new StackPane();
        root.setBackground(new Background(background));
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Create a label for the title
        Label titleLabel = new Label("ASTEROIDS");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        titleLabel.setTextFill(Color.WHITE);

        // Create a button to launch the game
        Button playButton = new Button("Play Asteroids");
        playButton.setFont(Font.font(30));
        playButton.setPrefWidth(300);
        playButton.setOnAction(e -> {
            try {
                // Launch the game
                AsteroidsGame game = new AsteroidsGame();
                game.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create a button to view high scores
        Button highScoresButton = new Button("High Scores");
        highScoresButton.setFont(Font.font(30));
        highScoresButton.setPrefWidth(300);

        highScoresButton.setOnAction(e -> {
            // Show the high scores screen
            System.out.println("TODO: Show high scores screen");
            try {
                // Launch the game
                HighScores_fx score = new HighScores_fx();
                score.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Create a button to view controls
        Button controlsButton = new Button("Controls");
        controlsButton.setFont(Font.font(30));
        controlsButton.setPrefWidth(300);

        controlsButton.setOnAction(e -> {
            // Show the controls screen
            ControlsScreen controlsScreen = new ControlsScreen(scene, primaryStage);
            Scene controlsScene = controlsScreen.controlsScene();
            primaryStage.setScene(controlsScene);
        });

        // Show the main menu
        primaryStage.setScene(scene);
        primaryStage.setTitle("Asteroids");
        primaryStage.show();

        // Add the title label and button to the main menu
        VBox mainMenuLayout = new VBox(50);
        mainMenuLayout.setAlignment(Pos.CENTER);
        mainMenuLayout.getChildren().addAll(titleLabel,playButton, highScoresButton, controlsButton);
        root.getChildren().add(mainMenuLayout);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
