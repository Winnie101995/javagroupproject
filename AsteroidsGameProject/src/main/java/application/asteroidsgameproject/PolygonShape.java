package application.asteroidsgameproject;
import java.util.Random;
import javafx.scene.shape.Polygon;
public class PolygonShape {

//    private instance variable for size
    private double size;


//Method creates the asteroids with the size variable to specify their size
    public Polygon createPolygonShape(double size){
        Random rnd = new Random();

        this.size = size;


        Polygon polygon = new Polygon();
//        Method create a new Polygon object named "polygon" and set its points
//        to create the shape of an asteroid.
        polygon.getPoints().addAll(
                size * 1.0, size * 1.5,
                size * 1.68, size * 0.2,
                size * 1.5, size * -1.0,
                size * 0.5, size * -1.2,
                size * -0.9, size * -1.2,
                size * -0.9, size * -0.07,
                size * 0.0, size * 0.0,
                size * -0.9, size * 0.5,
                size * 0.0, size * 1.5);
        for (int i = 0; i < polygon.getPoints().size(); i++) {
            int change = rnd.nextInt(5) - 2;
            polygon.getPoints().set(i, polygon.getPoints().get(i) + change);
        }

        return polygon;
    }
}

