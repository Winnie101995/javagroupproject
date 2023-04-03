package application.asteroidsgameproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AlienShip extends GameCharacters {
    public AlienShip(int x, int y) {

        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
        this.getGameCharacter().setFill(Color.RED);

    }
}