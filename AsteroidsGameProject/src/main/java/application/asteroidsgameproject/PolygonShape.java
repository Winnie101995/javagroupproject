package application.asteroidsgameproject;
import java.util.Random;
import javafx.scene.shape.Polygon;
public class PolygonShape {
    private double size;

//creates the asteroid with the size variable
    public Polygon createPolygonShape(double size){
        Random rnd = new Random();
        this.size = size;


        Polygon polygon = new Polygon();
//getting my asteroid shape
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

