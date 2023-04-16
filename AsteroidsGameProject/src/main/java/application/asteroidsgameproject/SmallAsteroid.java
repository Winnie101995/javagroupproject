package application.asteroidsgameproject;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class SmallAsteroid extends Asteroids{
    private static final int ASTEROID_SIZE = 20;
    public SmallAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);

        Image image = new Image("asteroid.png");
        ImagePattern imagePattern = new ImagePattern(image);

        this.getGameCharacter().setFill(imagePattern);
        this.getGameCharacter().setStroke(Color.BLACK);
        this.getGameCharacter().setStrokeWidth(2);

//        this.getGameCharacter().setFill(Color.GREY);
//        this.getGameCharacter().setStroke(Color.WHITE);
//        this.getGameCharacter().setStrokeWidth(2);

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
