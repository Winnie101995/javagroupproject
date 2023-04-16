package application.asteroidsgameproject;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

//creating large asteroids
public class LargeAsteroid extends Asteroids {
    private static final int ASTEROID_SIZE = 60;

    public LargeAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);

        Image image = new Image("asteroid.png");
        ImagePattern imagePattern = new ImagePattern(image);

        this.getGameCharacter().setFill(imagePattern);
        this.getGameCharacter().setStroke(Color.BLACK);

//        this.getGameCharacter().setFill(Color.GREY);
//        this.getGameCharacter().setStroke(Color.WHITE);

        //sets orientation

        Random random = new Random();
        this.getGameCharacter().setRotate(random.nextInt(360));

//        for (int i = 0; i < 5; i++) {
        accelerate(1.5);

    }

}
