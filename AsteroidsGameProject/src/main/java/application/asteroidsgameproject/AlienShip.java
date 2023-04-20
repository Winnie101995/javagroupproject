//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package application.asteroidsgameproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.Random;


// original testing ship removed. in original branch


public class AlienShip extends GameCharacters {
    private double speed;

    public AlienShip(int x, int y) {
        super(new Polygon(-60, 0, -40, 20, 40, 20, 60, 0, 40, -20, 30, -20, 20, -35, -20, -35, -30, -20, -40, -20), x, y);
        this.getGameCharacter().setFill(Color.BLACK);
        this.getGameCharacter().setStroke(Color.WHITE);
        this.getGameCharacter().setStrokeWidth(2.0);


        this.speed = 2;

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

    // WIP BELOW ON MOVEMENT TOWARDS PLAYER
    public void move(PlayerShip playerShip) {
        Point2D alienPosition = new Point2D(this.getGameCharacter().getTranslateX(), this.getGameCharacter().getTranslateY());
        Point2D playerPosition = new Point2D(playerShip.getGameCharacter().getTranslateX(), playerShip.getGameCharacter().getTranslateY());

        Point2D direction = playerPosition.subtract(alienPosition).normalize();
        Point2D movement = direction.multiply(speed);

        this.setMovement(movement);
        super.move();
    }
}