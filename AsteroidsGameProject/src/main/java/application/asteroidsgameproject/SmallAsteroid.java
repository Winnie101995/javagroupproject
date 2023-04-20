package application.asteroidsgameproject;

import javafx.scene.paint.Color;

import java.util.Random;

public class SmallAsteroid extends Asteroids{
    private static final int ASTEROID_SIZE = 20;
    public SmallAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);
        this.getGameCharacter().setFill(Color.TRANSPARENT);
        this.getGameCharacter().setStroke(Color.WHITE);
        this.getGameCharacter().setStrokeWidth(2);

        //sets orientation
        Random random = new Random();
        this.getGameCharacter().setRotate(random.nextInt(360));

        this.accelerate(3.5);

    }

//    @Override
//    public void destroy() {
//        super.destroy();
//    }
}
