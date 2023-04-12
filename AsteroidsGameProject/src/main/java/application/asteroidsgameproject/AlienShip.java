package application.asteroidsgameproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import java.util.Random;


// original testing ship removed. in original branch


public class AlienShip extends GameCharacters {
    public AlienShip(int x, int y) {
        super(new Polygon(-60, 0, -40, 20, 40, 20, 60, 0, 40, -20, 30, -20, 20, -35, -20, -35, -30, -20, -40, -20), x, y);
        this.getGameCharacter().setFill(Color.GREEN);
    }

    @Override
    public void accelerate(double accelerationFactor) {
        double changeX = Math.cos(Math.toRadians(this.getGameCharacter().getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getGameCharacter().getRotate()));

        Random random = new Random();
        int velocityRange = 10;
        int velocityX = random.nextInt(velocityRange * 2) - velocityRange;
        int velocityY = random.nextInt(velocityRange * 2) - velocityRange;

        changeX += accelerationFactor * velocityX;
        changeY += accelerationFactor * velocityY;

        this.movement = new Point2D(changeX, changeY);
    }

//    public void move() {
//        super.update(0.016); // elapsed time in seconds
//    }

}
