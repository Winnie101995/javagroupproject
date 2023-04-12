package application.asteroidsgameproject;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.Random;


// original testing ship removed. in original branch


public class AlienShip extends GameCharacters {
    public AlienShip(int x, int y) {
        super(new Polygon(-60, 0, -40, 20, 40, 20, 60, 0, 40, -20, 30, -20, 20, -35, -20, -35, -30, -20, -40, -20), x, y);
        this.getGameCharacter().setFill(Color.GREEN);
        Random random = new Random();
        int velocityRange = 10;
        int velocityX = random.nextInt(velocityRange * 2) - velocityRange;
        int velocityY = random.nextInt(velocityRange * 2) - velocityRange;
        this.movement = new Point2D(velocityX, velocityY);
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

    public void move() {
        super.update(0.016);
        // elapsed time in seconds (you can adjust this value to change the speed of the ship)
        double elapsedTimeSeconds = 0.016;

        // fixed velocity in pixels per second (you can adjust this value to change the initial speed of the ship)
        double fixedVelocity = 200.0;

        // update the position of the ship by a fixed velocity in the direction of its current rotation
        double deltaX = fixedVelocity * Math.cos(Math.toRadians(this.getGameCharacter().getRotate())) * elapsedTimeSeconds;
        double deltaY = fixedVelocity * Math.sin(Math.toRadians(this.getGameCharacter().getRotate())) * elapsedTimeSeconds;
        this.getGameCharacter().setTranslateX(this.getGameCharacter().getTranslateX() + deltaX);
        this.getGameCharacter().setTranslateY(this.getGameCharacter().getTranslateY() + deltaY);
        if (this.getGameCharacter().getTranslateX() < 0) {
            this.getGameCharacter().setTranslateX(this.getGameCharacter().getTranslateX() + AsteroidsGame.WIDTH);
        }

        if (this.getGameCharacter().getTranslateX() > AsteroidsGame.WIDTH) {
            this.getGameCharacter().setTranslateX(this.getGameCharacter().getTranslateX() % AsteroidsGame.WIDTH);
        }

        if (this.getGameCharacter().getTranslateY() < 0) {
            this.getGameCharacter().setTranslateY(this.getGameCharacter().getTranslateY() + AsteroidsGame.HEIGHT);
        }

        if (this.getGameCharacter().getTranslateY() > AsteroidsGame.HEIGHT) {
            this.getGameCharacter().setTranslateY(this.getGameCharacter().getTranslateY() % AsteroidsGame.HEIGHT);
        }
    }

}
