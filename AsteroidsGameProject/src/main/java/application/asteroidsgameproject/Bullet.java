
package application.asteroidsgameproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Bullet extends GameCharacters {
    public Bullet(int x, int y) {
        super(new Polygon(new double[]{2.0, -2.0, 2.0, 2.0, -2.0, 2.0, -2.0, -2.0}), x, y);
        this.getGameCharacter().setFill(Color.WHITE);
    }
}
