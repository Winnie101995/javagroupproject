package application.asteroidsgameproject;
import java.util.Random;
import javafx.scene.shape.Polygon;
public class PolygonShape {
    private double size;


    public Polygon createPolygonShape(double size){
        Random rnd = new Random();
        this.size = size;


        Polygon polygon = new Polygon();
//        5 corners of the polygon
        double c1 = Math.cos(Math.PI * 2 / 5);
        double c2 = Math.cos(Math.PI / 5);
        double s1 = Math.sin(Math.PI * 2 / 5);
        double s2 = Math.sin(Math.PI * 4 / 5);


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

//    size, 0.0,
//                size * c1, -1.5 * size * s1,
//                -1 * size * c2, -1 * size * s2,
//                3 * size * c2, size * s2 * -5,
//                size * c1, size * s1);