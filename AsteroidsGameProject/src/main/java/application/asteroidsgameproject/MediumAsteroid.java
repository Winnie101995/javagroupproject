package application.asteroidsgameproject;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

//Asteroid Class for meduim asteroid

public class MediumAsteroid extends Asteroids{
    //this size doesnt change
    private static final int ASTEROID_SIZE = 40;
    public MediumAsteroid(int x, int y){
        super(x, y, ASTEROID_SIZE);

        Image image = new Image("asteroid.png");
        ImagePattern imagePattern = new ImagePattern(image);

        this.getGameCharacter().setFill(imagePattern);
        this.getGameCharacter().setStroke(Color.BLACK);
        this.getGameCharacter().setStrokeWidth(2);

//        this.getGameCharacter().setFill(Color.GREY);
//        this.getGameCharacter().setStroke(Color.WHITE);
//        this.getGameCharacter().setStrokeWidth(2);

        //sets orientation and direction of the asteroid
        Random rnd= new Random();
        this.getGameCharacter().setRotate(rnd.nextInt(360));
//        creating the accelation / speed
        this.accelerate(2.5);

    }
}