package application.asteroidsgameproject;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import java.util.Random;

public class PlayerShip extends GameCharacters {
    public PlayerShip(int x, int y) {

        super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
        this.getGameCharacter().setFill(Color.BLUE);

    }

    public void hyperJump(){
//        get the current location
//        double current_x_location = this.getGameCharacter().getTranslateX();
//        double current_y_location = this.getGameCharacter().getTranslateY();

//        get current location of o
//     remove the space space from current location
        this.getGameCharacter().setTranslateX(-1000);
        this.getGameCharacter().setTranslateY(-1000);

//        generate a random location on the screen and move the spaceshap there

        double newRandX = Math.random() * AsteroidsGame.WIDTH;
        double newRandY = Math.random() * AsteroidsGame.HEIGHT;
        this.getGameCharacter().setTranslateX(newRandX);
        this.getGameCharacter().setTranslateY(newRandY);
    }

}
