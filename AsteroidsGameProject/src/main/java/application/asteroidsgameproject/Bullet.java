package application.asteroidsgameproject;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Bullet extends GameCharacters{

    public Bullet(int x, int y ){
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
        this.getGameCharacter().setFill(Color.WHITE);

    }

    public Bounds getBoundsInParent() {
        return getGameCharacter().getBoundsInParent();
    }




        }



