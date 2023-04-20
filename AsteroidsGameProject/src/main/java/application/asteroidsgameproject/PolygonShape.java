
package application.asteroidsgameproject;

import java.util.Random;
import javafx.scene.shape.Polygon;
public class PolygonShape {
    private double size; // Holds the size of the polygon.

    public PolygonShape() {
    } // Default constructor for the PolygonShape class.

    public Polygon createPolygonShape(double size) {
        Random rnd = new Random(); // Creates a Random object to generate random values.
        this.size = size; // Sets the size of the polygon to the input size.
        Polygon polygon = new Polygon(); // Creates a new Polygon object.
        // Sets the points of the polygon with calculated values based on the input size.
        polygon.getPoints().addAll(new Double[]{
                size * 1.0, size * 1.5,
                size * 1.68, size * 0.2,
                size * 1.5, size * -1.0,
                size * 0.5, size * -1.2,
                size * -0.9, size * -1.2,
                size * -0.9, size * -0.07,
                size * 0.0, size * 0.0,
                size * -0.9, size * 0.5,
                size * 0.0, size * 1.5
        });

        // Applies random changes to the points of the polygon to add variation to the shape.
        for(int i = 0; i < polygon.getPoints().size(); ++i) {
            int change = rnd.nextInt(5) - 2; // Generates a random value between -2 and 2.
            polygon.getPoints().set(i, (Double)polygon.getPoints().get(i) + (double)change); // Applies the random change to the current point.
        }

        return polygon; // Returns the created polygon.
    }
}
