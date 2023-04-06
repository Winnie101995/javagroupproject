package application.asteroidsgameproject;
import javafx.scene.paint.Color;

import java.util.Random;

public class LargeAsteroid extends Asteroids {
    private static final int ASTEROID_SIZE = 60;

    public LargeAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);
        this.getGameCharacter().setFill(Color.GREY);
        this.getGameCharacter().setStroke(Color.WHITE);

        //sets orientation

        Random random = new Random();
        this.getGameCharacter().setRotate(random.nextInt(360));

//        for (int i = 0; i < 5; i++) {
            accelerate(1.5);

    }

}


