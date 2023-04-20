
package application.asteroidsgameproject;

import java.util.Random;
import javafx.scene.paint.Color;

public abstract class Asteroids extends GameCharacters {
    private double rotationalMovement;

    // Constructor for the Asteroids class.

    public Asteroids(int x, int y, int size) {
        super((new PolygonShape()).createPolygonShape((double)size), x, y);
        this.getGameCharacter().setStroke(Color.WHITE);
        this.getGameCharacter().setStrokeWidth(2.0);
        Random generaterandom = new Random();
        // Sets the initial rotation of the asteroid to a random angle between 0 and 360 degrees.
        super.getGameCharacter().setRotate((double)generaterandom.nextInt(360));
        // Sets the rotational movement of the asteroid to a random value between -0.5 and 0.5.
        this.rotationalMovement = 0.5 - generaterandom.nextDouble();
    }

    // Method to move the asteroid.

    public void move() {
        super.move();
        // Updates the rotation of the asteroid based on the rotational movement value.
        super.getGameCharacter().setRotate(super.getGameCharacter().getRotate() + this.rotationalMovement);
    }
}
