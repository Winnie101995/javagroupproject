package application.asteroidsgameproject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


public class AsteroidsGame extends Application {
    public static int WIDTH;
    public static int HEIGHT;

//    flag to check if a byllet has been fired


    @Override
    public void start(Stage mainStage) throws IOException {
//test

       // Get the dimensions of the primary screen
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            WIDTH = (int) primaryScreenBounds.getWidth();
            HEIGHT = (int) primaryScreenBounds.getHeight();

        // Create the scene and canvas
            BorderPane root = new BorderPane();
            Scene mainScene = new Scene(root);
            mainStage.setScene(mainScene);
            mainStage.setTitle("Asteroids Game");


        Canvas canvas = new Canvas(WIDTH, HEIGHT);
            GraphicsContext gc = canvas.getGraphicsContext2D();
            root.setCenter(canvas);

            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, WIDTH, HEIGHT);
//playershi[
        PlayerShip playership = new PlayerShip(WIDTH/2, HEIGHT/2);
//        list of asterioids
        List<Asteroids> asteroids = new ArrayList<>();
        Random rnd = new Random();

// Generate large asteroids
        for (int i = 0; i < 5; i++) {
            LargeAsteroid asteroid = new LargeAsteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
            asteroids.add(asteroid);
        }

// Generate medium asteroids
        for (int i = 0; i < 5; i++) {
            MediumAsteroid asteroid = new MediumAsteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
            asteroids.add(asteroid);
        }

// Generate small asteroids
        for (int i = 0; i < 5; i++) {
            SmallAsteroid asteroid = new SmallAsteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
            asteroids.add(asteroid);
        }

        ArrayList<Bullet> bulletList = new ArrayList<>();


        root.getChildren().add(playership.getGameCharacter());
        asteroids.forEach(asteroid -> root.getChildren().add(asteroid.getGameCharacter()));


//    keeping a record of the pressed keys using a hash table, if the key is pressed the boolean returns true
        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        mainScene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        mainScene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });


        new AnimationTimer() {
            AtomicBoolean hasFiredBullet = new AtomicBoolean(false);

            @Override
            public void handle(long now) {
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    playership.turnLeft();
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    playership.turnRight();
                }


                if(pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    playership.accelerate(0.05);
                }

                if (pressedKeys.getOrDefault(KeyCode.SHIFT, false)) {
                    playership.hyperJump();
                }

                if (hasFiredBullet.get() == false && pressedKeys.getOrDefault(KeyCode.SPACE, false) && bulletList.size() < 10) {
                    // user can fire a bullet
//                    System.out.println("bullet has been shot");
                    Bullet bullet = new Bullet((int) playership.getGameCharacter().getTranslateX(), (int) playership.getGameCharacter().getTranslateY());
                    bullet.getGameCharacter().setRotate(playership.getGameCharacter().getRotate());
                    bulletList.add(bullet);

                    bullet.accelerate(0.05);
                    bullet.setMovement(bullet.getMovement().normalize().multiply(3));

                    root.getChildren().add(bullet.getGameCharacter());

                    // set hasFiredBullet to true to indicate that the user has fired a bullet
                    hasFiredBullet.set(true);
                }


                playership.move();

//                getting the bullets from the the bullet list ensuring thet dont stay more than 5 seconds on the screen
                asteroids.forEach(asteroid -> asteroid.move());

                for (int n = 0; n < bulletList.size(); n++) {

                    GameCharacters bullet = bulletList.get(n);

                    bullet.move();
                    bullet.update(1/60.0);
                    if (bullet.elapseTimeSeconds > 2) {
                        bulletList.remove(n);
                        root.getChildren().remove(bullet.getGameCharacter());
                    }
                }

                asteroids.forEach(asteroid -> {
                    if (playership.collision(asteroid)) {
//                         stop();
                    }
                });

            }





        }.start();
        mainStage.show();

    }
        public static void main(String[] args){
        try {
            launch(args);
        } catch(Exception error) {
            error.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

}