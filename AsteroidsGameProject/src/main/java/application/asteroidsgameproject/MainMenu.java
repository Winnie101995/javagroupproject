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

public class MainMenu extends Application {
    public static int WIDTH;
    public static int HEIGHT;
    public static final Rectangle2D PRIMARY_SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();

    @Override
    public void start(Stage primaryStage) {
        // Get the dimensions of the primary screen
        WIDTH = (int) PRIMARY_SCREEN_BOUNDS.getWidth();
        HEIGHT = (int) PRIMARY_SCREEN_BOUNDS.getHeight();
        // Create the main menu welcome screen
        VBox welcomeLayout = createWelcomeLayout(primaryStage); // Create a VBox layout for the welcome screen
        Scene welcomeScene = new Scene(welcomeLayout, WIDTH, HEIGHT); // Create a scene with the welcome layout and size

        // Set the main menu scene
        primaryStage.setScene(welcomeScene); // Set the welcome screen as the main menu scene
        primaryStage.setTitle("Asteroids Game"); // Set the title of the stage
        primaryStage.show(); // Show the stage
    }

    private VBox createWelcomeLayout(Stage primaryStage) {
        // Create the VBox layout for the welcome screen
        VBox welcomeLayout = new VBox(20);

        // Create the title label
        Label titleLabel = new Label("Asteroids");
        titleLabel.setFont(Font.font(50)); // Set the font size of the title label
        titleLabel.setStyle("-fx-text-fill: white;"); // Set the text color of the title label to white

        // Create the "Play Game" button
        Button playButton = new Button("Play Game");
        playButton.setFont(Font.font(24)); // Set the font size of the play button
        playButton.setOnAction(e -> {
            // Launch the AsteroidsGame
            try {
                AsteroidsGame asteroidsGame = new AsteroidsGame();
                asteroidsGame.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Load the background image
        Image backgroundImage = new Image(getClass().getResource("/spaceBackground.png").toExternalForm());

        // Create the background
        BackgroundSize backgroundSize = new BackgroundSize(WIDTH, HEIGHT, true, true, true, true);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImg);

        // Set the background of the VBox
        welcomeLayout.setBackground(background);

        // Create the "High Scores" button
        Button highScoresButton = new Button("Hall of Fame - High Scores");
        highScoresButton.setFont(Font.font(24)); // Set the font size of the high scores button
        highScoresButton.setOnAction(e -> {
            // TODO: Implement the high scores action
        });

        // Create the "Controls" button
        Button controlsButton = new Button("Controls");
        controlsButton.setFont(Font.font(24)); // Set the font size of the controls button
        controlsButton.setOnAction(e -> {
            // Create the controls layout
            VBox controlsLayout = createControlsLayout(primaryStage); // Pass backgroundImg as a parameter
            Scene controlsScene = new Scene(controlsLayout, WIDTH, HEIGHT); // Create a scene with the controls layout and size

            // Set the controls scene
            primaryStage.setScene(controlsScene); // Set the controls screen as the main menu scene
        });

        // Add the title label and buttons to the layout
        welcomeLayout.getChildren().addAll(titleLabel, playButton, highScoresButton, controlsButton);
        welcomeLayout.setAlignment(Pos.CENTER); // Set the alignment of the layout to center
        return welcomeLayout; // Return the layout
    }

    private VBox createControlsLayout(Stage primaryStage) {
        // Create the "Back" button
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(24)); // Set the font size of the back button
        backButton.setOnAction(e -> {
            // Go back to the main menu
            VBox welcomeLayout = createWelcomeLayout(primaryStage); // Create a VBox layout for the welcome screen
            Scene welcomeScene = new Scene(welcomeLayout, WIDTH, HEIGHT); // Create a scene with the welcome layout and size

            primaryStage.setScene(welcomeScene); // Set the welcome screen as the main menu scene
        });

        // Create the controls label
        Label controlsLabel = new Label("Controls:");
        controlsLabel.setFont(Font.font(20)); // Set the font size of the controls label
        controlsLabel.setStyle("-fx-text-fill: white;"); // Set the text color of the controls label to white

        // Load the background image
        Image backgroundControls = new Image(getClass().getResource("/spaceBackground.png").toExternalForm());

        // Create the background
        BackgroundSize backgroundSize = new BackgroundSize(WIDTH, HEIGHT, true, true, true, true);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundControls, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImg);

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

        // Add the controls label, text, and back button to the layout
        VBox layout = new VBox(20, controlsLabel, controlsText, backButton);
        layout.setAlignment(Pos.CENTER);

        // Set the background of the VBox
        layout.setBackground(background);

        Scene controlsScene = new Scene(layout, WIDTH, HEIGHT);
        primaryStage.setScene(controlsScene); // Set the controls scene as the main menu scene

        return layout;
    }
    public static void main(String[] args) {
        launch(args);
    }
}