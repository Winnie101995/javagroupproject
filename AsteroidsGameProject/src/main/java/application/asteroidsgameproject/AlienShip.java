package application.asteroidsgameproject;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.Random;

public class AlienShip extends GameCharacters {
    private Point2D velocity;
    private int shootingCounter = 0;
    private int shootingInterval = 10; // You can adjust this to change the shooting frequency
    private Random random = new Random();

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


        // Initialize random velocity
        double xVelocity = random.nextDouble() * 2 - 1;
        double yVelocity = random.nextDouble() * 2 - 1;

        // Ensure xVelocity is in the correct direction
        if (x < AsteroidsGame.WIDTH / 2) {
            xVelocity = Math.abs(xVelocity);
        } else {
            xVelocity = -Math.abs(xVelocity);
        }

        velocity = new Point2D(xVelocity, yVelocity).normalize().multiply(2);
    }

    public void move() {
        // Move the alien ship along its current velocity vector
        getGameCharacter().setTranslateX(getGameCharacter().getTranslateX() + velocity.getX());
        getGameCharacter().setTranslateY(getGameCharacter().getTranslateY() + velocity.getY());
    }

    public Bullet shootBullet(double playerX, double playerY) {
        // Calculate the direction vector from the alien ship to the player ship
        Point2D direction = new Point2D(playerX - getGameCharacter().getTranslateX(), playerY - getGameCharacter().getTranslateY()).normalize();

        // Create a bullet with the appropriate position and angle
        Bullet bullet = new Bullet((int) getGameCharacter().getTranslateX(), (int) getGameCharacter().getTranslateY());
        bullet.setMovement(direction.multiply(3));

        return bullet;
    }

    public boolean shouldShoot() {
        shootingCounter++;
        if (shootingCounter > shootingInterval) {
            shootingCounter = 0;
            return true;
        } else {
            return false;
        }
    }


//    Bullet bullet = new Bullet((int) playership.getGameCharacter().getTranslateX(), (int) playership.getGameCharacter().getTranslateY());

    public void createAlienBullet(AlienShip alienShip, PlayerShip playerShip) {
        Bullet bullet = new Bullet();

        // Set bullet's position to be the same as the alien ship's position
        int x = this.bullet.setTranslateX(alienShip.getTranslateX());
        int y = this.bullet.setTranslateY(alienShip.getTranslateY());

        // Calculate the direction vector from the alien ship to the player ship
        Point2D direction = new Point2D(
                playerShip.getTranslateX() - alienShip.getTranslateX(),
                playerShip.getTranslateY() - alienShip.getTranslateY()
        );

        // Normalize the direction vector and multiply by a scalar value (3 in this case) to set the bullet's speed
        direction = direction.normalize().multiply(3);

        // Set the bullet's movement according to the calculated direction vector
        bullet.setMovement(direction);

        // Add the bullet to the alien bullets list
        alienBulletList.add(bullet);

        // Add the bullet to the game objects list (if you have one)
        // gameObjects.add(bullet);

        // Add the bullet to the game's root node so it's displayed on the screen - MAY ALREADY BE INCORPORATED IN AS.GAME CLASS
        // root.getChildren().add(bullet);
    }

//    public Bullet createAlienBullet(double playerX, double playerY) {
//        Point2D direction = new Point2D(playerX - getTranslateX(), playerY - getTranslateY());
//        Bullet alienBullet = new Bullet((int) getTranslateX(), (int) getTranslateY(), Color.RED);
//        alienBullet.setMovement(direction.normalize().multiply(3));
//        return alienBullet;
//    }
}



