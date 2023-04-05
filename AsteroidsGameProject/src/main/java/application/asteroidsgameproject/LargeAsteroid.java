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

        MediumAsteroid asteroid1 = new MediumAsteroid((int)x - 5, (int)y + 5);
        MediumAsteroid asteroid2 = new MediumAsteroid((int)x + 5, (int)y - 5);
//        getParentGroup().getChildren().addAll(asteroid1.getGameCharacter(), asteroid2.getGameCharacter());
        System.out.println("Asteroid 1 at (" + asteroid1.getGameCharacter().getTranslateX() + ", " + asteroid1.getGameCharacter().getTranslateY() + ")");
        System.out.println("Asteroid 2 at (" + asteroid2.getGameCharacter().getTranslateX() + ", " + asteroid2.getGameCharacter().getTranslateY() + ")");
        System.out.println("large asteoid at (" + this.getGameCharacter().getTranslateX() + ", " + this.getGameCharacter().getTranslateY() + ")");

        asteroid1.accelerate(1.0);
        asteroid2.accelerate(1.0);

        super.destroy();
    }

}


