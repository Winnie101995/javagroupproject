package application.asteroidsgameproject;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Bullet extends GameCharacters {

    public Bullet(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
        this.getGameCharacter().setFill(Color.WHITE);

    }

}

//might delete later
//
//    public Bounds getBoundsInParent() {
//        return getGameCharacter().getBoundsInParent();
//    }
//
//
////    public void bulletCollision(Asteroids asteroid) {
////        if (this.collision(asteroid)) {
////            // bullet has hit asteroid
////            if (asteroid instanceof LargeAsteroid) {
////                asteroid.destroy();
////            } else if (asteroid instanceof MediumAsteroid) {
////                asteroid.destroy();
////            } else if (asteroid instanceof SmallAsteroid) {
////                asteroid.destroy();
////            }
//
//
//        }
////    }
//
////}
