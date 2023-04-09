package application.asteroidsgameproject;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.Random;

//parent class that contains small, large and meduim asteroids
public abstract class Asteroids extends GameCharacters{
    //   Private instance variable represent the rotational movement of an object in the class,
//   typically a game character or a sprite.
    private double rotationalMovement;
    //    Method constructors for Asteroids -> initializes a objects
    public Asteroids(int x, int y, int size){
        super(new PolygonShape().createPolygonShape(size), x, y);
        this.getGameCharacter().setStroke(Color.WHITE);
        this.getGameCharacter().setStrokeWidth(2);

        Random generaterandom = new Random();
        super.getGameCharacter().setRotate(generaterandom.nextInt(360));
        this.rotationalMovement = 0.5 - generaterandom.nextDouble();
    }

    //    this overridden method moves the Asteroid game character according to its current movement and
//    updates its rotation based on the rotational movement.
    @Override
    public void move() {
        super.move();
        super.getGameCharacter().setRotate(super.getGameCharacter().getRotate() + rotationalMovement);
    }
}