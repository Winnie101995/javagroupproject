package application.asteroidsgameproject;
import javafx.scene.paint.Color;

import java.util.Random;

public class LargeAsteroid extends Asteroids {
    private static final int ASTEROID_SIZE = 40;

    public LargeAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);
        this.getGameCharacter().setFill(Color.GREY);
        this.getGameCharacter().setStroke(Color.WHITE);

        //sets orientation

        Random random = new Random();
        this.getGameCharacter().setRotate(random.nextInt(360));

        for (int i = 0; i < 5; i++) {
            accelerate(0.04);
        }
    }

    @Override
    public void destroy() {
        double x = getGameCharacter().getTranslateX();
        double y = getGameCharacter().getTranslateY();

        MediumAsteroid asteroid1 = new MediumAsteroid((int) x, (int) y);
        MediumAsteroid asteroid2 = new MediumAsteroid((int) x, (int) y);

        getParentGroup().getChildren().addAll(asteroid1.getGameCharacter(), asteroid2.getGameCharacter());

        asteroid1.accelerate(1.0);
        asteroid2.accelerate(1.0);

        super.destroy();
    }

}


