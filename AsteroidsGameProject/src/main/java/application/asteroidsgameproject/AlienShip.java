package application.asteroidsgameproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


// original testing ship removed. in original branch


public class AlienShip extends GameCharacters {
    public AlienShip(int x, int y) {
        super(new Polygon(
                -40, 0,
                -36, -8,
                -24, -16,
                -16, -20,
                16, -20,
                24, -16,
                36, -8,
                40, 0,
                36, 8,
                24, 16,
                16, 20,
                -16, 20,
                -24, 16,
                -36, 8
        ), x, y);
        this.getGameCharacter().setFill(Color.GREEN);
    }
}



