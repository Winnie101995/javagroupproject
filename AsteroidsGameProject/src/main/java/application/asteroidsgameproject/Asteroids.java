package application.asteroidsgameproject;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.Random;

public abstract class Asteroids extends GameCharacters{
    private double rotationalMovement;
//    private double movementSpeed;
private Group parentGroup = new Group(); // initialize parentGroup

    public Asteroids(int x, int y, int size){
        super(new PolygonShape().createPolygonShape(size), x, y);
        this.getGameCharacter().setStroke(Color.WHITE);
        this.getGameCharacter().setStrokeWidth(2);

        Random generaterandom = new Random();
        super.getGameCharacter().setRotate(generaterandom.nextInt(360));
        this.rotationalMovement = 0.5 - generaterandom.nextDouble();
    }

    @Override
    public void move() {
        super.move();
        super.getGameCharacter().setRotate(super.getGameCharacter().getRotate() + rotationalMovement);
    }

    public Group getParentGroup() {
        return parentGroup;
    }

//    public Bounds getBoundsInParent() {
//        return getGameCharacter().getBoundsInParent();
//    }


//    removing the asteroid from the screen: destroying the asteriod -> every size asteroid implements it's own implementation




}
