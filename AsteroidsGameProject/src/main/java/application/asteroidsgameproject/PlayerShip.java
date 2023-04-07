package application.asteroidsgameproject;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import java.util.Random;

public class PlayerShip extends GameCharacters {
    //    PlayerShip constructor
    public PlayerShip(int x, int y) {

        super(new Polygon(15.0, 0.0, -15.0, 10.0, -5.0, 0.0, -15.0, -10.0), x, y);
        this.getGameCharacter().setFill(Color.BLUE);
        this.getGameCharacter().setOpacity(1);

    }

//    method that implements hyperJump mechanism movement.

    public void hyperJump() {
        // Get the current location of the game character
        double current_x_location = this.getGameCharacter().getTranslateX();
        double current_y_location = this.getGameCharacter().getTranslateY();


        // Generate a new random location for the game character
        double newRandX = Math.random() * AsteroidsGame.WIDTH;
        double newRandY = Math.random() * AsteroidsGame.HEIGHT;

        // Move the game character to the new location
        this.getGameCharacter().setTranslateX(newRandX);
        this.getGameCharacter().setTranslateY(newRandY);


    }
}