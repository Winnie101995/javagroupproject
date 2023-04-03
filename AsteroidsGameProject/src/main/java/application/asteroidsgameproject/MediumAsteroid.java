package application.asteroidsgameproject;

import javafx.scene.paint.Color;

import java.util.Random;

public class MediumAsteroid extends Asteroids{
    private static final int ASTEROID_SIZE = 25;
    public MediumAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);
        this.getGameCharacter().setFill(Color.GREY);
        this.getGameCharacter().setStroke(Color.WHITE);
        this.getGameCharacter().setStrokeWidth(2);

        //sets orientation

        Random random = new Random();
        this.getGameCharacter().setRotate(random.nextInt(360));

        for (int i = 0; i < 5; i++) {
            accelerate(0.1);
        }
    }

    @Override
    public void destroy() {
        double x = getGameCharacter().getTranslateX();
        double y = getGameCharacter().getTranslateY();

        SmallAsteroid asteroid1 = new SmallAsteroid((int) x, (int) y);
        SmallAsteroid asteroid2 = new SmallAsteroid((int) x, (int) y);

        getParentGroup().getChildren().addAll(asteroid1.getGameCharacter(), asteroid2.getGameCharacter());

        asteroid1.accelerate(0.6);
        asteroid2.accelerate(0.6);

        super.destroy();
    }
}
