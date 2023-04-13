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
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import javafx.geometry.Insets;

public class MainMenu extends Application {
    public static int WIDTH;
    public static int HEIGHT;
    public static final Rectangle2D PRIMARY_SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();

    @Override
    public void start(Stage primaryStage) {
        // Get the screen dimensions
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        int screenWidth = (int) primaryScreenBounds.getWidth();
        int screenHeight = (int) primaryScreenBounds.getHeight();

        // Set the window dimensions
        WIDTH = screenWidth;
        HEIGHT = screenHeight;


        // Create the main menu scene
        BorderPane root = new BorderPane();

        // Set the background image of the root pane
        Image backgroundImage = new Image("/spaceBackground.png");
        BackgroundSize backgroundSize = new BackgroundSize(screenWidth, screenHeight, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root, screenWidth, screenHeight);

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
            VBox controlsLayout = createControlsLayout(primaryStage, scene);
            Scene controlsScene = new Scene(controlsLayout, WIDTH, HEIGHT);
            primaryStage.setScene(controlsScene);
        });

        // Add the buttons to the main menu
        VBox buttonBox = new VBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(playButton, highScoresButton, controlsButton);
        root.setCenter(buttonBox);

        // Show the main menu
        primaryStage.setScene(scene);
        primaryStage.setTitle("Asteroids");
        primaryStage.show();
    }

    private VBox createControlsLayout(Stage primaryStage, Scene scene) {
        // Get the screen dimensions
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        int screenWidth = (int) primaryScreenBounds.getWidth();
        int screenHeight = (int) primaryScreenBounds.getHeight();

        // Create the VBox layout for the controls screen
        VBox controlsLayout = new VBox(20);
        controlsLayout.setAlignment(Pos.CENTER);

        // Set the background image of the controls layout
        Image backgroundImage = new Image("/spaceBackground.png");
        BackgroundSize backgroundSize = new BackgroundSize(screenWidth, screenHeight, false, false, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        controlsLayout.setBackground(new Background(background));

        // Create the "Back" button
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(24));
        backButton.setOnAction(e -> {
            // Go back to the main menu
            primaryStage.setScene(scene);
        });

        // Create the controls label
        Label controlsLabel = new Label("Controls:");
        controlsLabel.setFont(Font.font(20));
        controlsLabel.setStyle("-fx-text-fill: white;");

        // Create the controls text
        Label controlsText = new Label("Use the arrow keys to move the ship.\nThe up arrow key moves the ship forward, \nThe left and right arrow keys rotate the ship\n" +
                "\n" +
                "Press the space bar to shoot lasers"
                +"\nAvoid colliding with asteroids and enemy ships.\nColliding with them will destroy the player's ship.\n" +
                "\n" +
                "Destroy all asteroids and enemy ships to complete the level.\n" +
                "\n" +
                "The player has a limited number of lives. \nLosing all lives will result in a game over.\n" +
                "\n" +
                "Remember to have fun and good luck!");
        controlsText.setStyle("-fx-text-fill: white;"); // Set the text color of the controls text to white

        // Add the controls label, text, and buttons to the layout
        controlsLayout.getChildren().addAll(controlsLabel, controlsText, backButton);

        return controlsLayout;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
