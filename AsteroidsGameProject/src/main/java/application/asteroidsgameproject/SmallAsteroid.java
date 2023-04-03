package application.asteroidsgameproject;

import javafx.scene.paint.Color;

import java.util.Random;

public class SmallAsteroid extends Asteroids{
    private static final int ASTEROID_SIZE = 10;
    public SmallAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);
        this.getGameCharacter().setFill(Color.GREY);
        this.getGameCharacter().setStroke(Color.WHITE);
        this.getGameCharacter().setStrokeWidth(2);

        //sets orientation
        Random random = new Random();
        this.getGameCharacter().setRotate(random.nextInt(360));

        for (int i = 0; i < 5; i++) {
            accelerate(0.6);
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
