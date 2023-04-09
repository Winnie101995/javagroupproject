package application.asteroidsgameproject;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Rectangle;


import java.util.List;
import java.util.Random;

public class PlayerShip extends GameCharacters {
    //    PlayerShip constructor
    public PlayerShip(int x, int y) {

        super(new Polygon(15.0, 0.0, -15.0, 10.0, -5.0, 0.0, -15.0, -10.0), x, y);
        this.getGameCharacter().setFill(Color.BLUE);
        this.getGameCharacter().setOpacity(1);

    }

    //  creating a separate collision method from gameCharacters as this one can apply the simplified version of the test
    private boolean hyperJumpCollision(Shape shape, double x, double y) {
        // Create a new rectangle at the desired position
        Rectangle rect = new Rectangle(x, y, shape.getBoundsInLocal().getWidth(), shape.getBoundsInLocal().getHeight());

        // Check if the rectangle collides with the shape
        Shape collisionArea = Shape.intersect(shape, new Rectangle(x, y, shape.getBoundsInLocal().getWidth(), shape.getBoundsInLocal().getHeight()));
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }


//    method that implements hyperJump mechanism movement.

    public void hyperJump(List<GameCharacters> gameObjects) {
        // Get the current location of the game character
        double current_x_location = this.getGameCharacter().getTranslateX();
        double current_y_location = this.getGameCharacter().getTranslateY();

        // Generate a new random location for the game character
        double newRandX = Math.random() * AsteroidsGame.WIDTH;
        double newRandY = Math.random() * AsteroidsGame.HEIGHT;

        // Check if the new location collides with any game objects
        boolean collisionDetected = false;
        for (GameCharacters gameObject : gameObjects) {
            if (hyperJumpCollision(gameObject.getGameCharacter(), newRandX, newRandY)) {
                collisionDetected = true;
                break;
            }
        }

        // If there is a collision, generate a new random location until there isn't one
        while (collisionDetected) {
            newRandX = Math.random() * AsteroidsGame.WIDTH;
            newRandY = Math.random() * AsteroidsGame.HEIGHT;
            collisionDetected = false;
            for (GameCharacters gameObject : gameObjects) {
                if (hyperJumpCollision(gameObject.getGameCharacter(), newRandX, newRandY)) {
                    collisionDetected = true;
                    break;
                }
            }
        }

        // Move the game character to the new location
        this.getGameCharacter().setTranslateX(newRandX);
        this.getGameCharacter().setTranslateY(newRandY);
    }
}