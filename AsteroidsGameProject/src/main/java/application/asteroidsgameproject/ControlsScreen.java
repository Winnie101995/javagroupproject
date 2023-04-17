package application.asteroidsgameproject;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
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

public class ControlsScreen extends Parent {

    private Scene mainMenuScene;
    private Stage primaryStage;

    public ControlsScreen(Scene mainMenuScene, Stage primaryStage) {
        this.mainMenuScene = mainMenuScene;
        this.primaryStage = primaryStage;
    }

    public Scene controlsScene() {
        // Get the screen dimensions
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        int screenWidth = (int) primaryScreenBounds.getWidth();
        int screenHeight = (int) primaryScreenBounds.getHeight();

        // Create the VBox layout for the controls screen
        VBox controlsLayout = new VBox(20);
        controlsLayout.setAlignment(Pos.CENTER);

        // Set the background image of the controls layout
        Image backgroundImage = new Image("/spaceBackground.png");
        BackgroundSize backgroundSize = new BackgroundSize(screenWidth, screenHeight, true, true, true, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        controlsLayout.setBackground(new Background(background));

        // Create the "Back" button
        Button backButton = new Button("Back");
        backButton.setFont(Font.font(24));
        backButton.setOnAction(e -> {
            // Go back to the main menu
            primaryStage.setScene(mainMenuScene);
        });

        // Create the controls label
        Label controlsLabel = new Label("Controls:");
        controlsLabel.setFont(Font.font(50));
        controlsLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-underline: true;");


        // Create the controls text
        Label controlsText = new Label("The up arrow key moves the ship forward, " +
                "\nThe left and right arrow keys rotate the ship\n" +
                "\n" + "Press the space bar to shoot lasers" +
                "\n" + "Press the shift button to hyper jump"
                +"\nAvoid colliding with asteroids and enemy ships." +
                "\nColliding with them will destroy the player's ship.\n" +
                "\n" +
                "Destroy all asteroids and enemy ships to complete the level." +
                "\n Large Asteroids are worth 20 points. " +
                "\n Medium Asteroids are worth 50 points. " +
                "\n Small Asteroids are worth 100 points.\n" +
                "Alien ships are worth 300 points.\n" +
                "\nThe player has a 3 lives. \n You can regain a life for every 10,000 points scored." +
                " \nLosing all lives will result in a game over.\n" +
                "\n" +
                "Remember to have fun and good luck!");
        controlsText.setStyle("-fx-text-fill: white;"); //text color to white so its visible on black background
        controlsText.setFont(Font.font(20));

        // Add the controls label, text, and buttons to the layout
        controlsLayout.getChildren().addAll(controlsLabel, controlsText, backButton);

        return new Scene(controlsLayout, screenWidth, screenHeight);
    }
}