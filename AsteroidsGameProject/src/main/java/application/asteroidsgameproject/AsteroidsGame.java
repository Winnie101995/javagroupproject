package application.asteroidsgameproject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class AsteroidsGame extends Application {
    public static int WIDTH;
    public static int HEIGHT;

//    flag to check if a byllet has been fired
 // create parent group


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
//playership
        PlayerShip playership = new PlayerShip(WIDTH/2, HEIGHT/2);
//        list of asterioids
        ArrayList<Asteroids> asteroids = new ArrayList<>();
//        generating a random position to the
            Random rnd = new Random();
// Generate a the first large asteroids
            LargeAsteroid asteroid_one = new LargeAsteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));

        asteroids.add(asteroid_one);
//            generating a list of collided or hit asteroids
//           list of bullests
        ArrayList<Bullet> bulletList = new ArrayList<>();
//level
//        add player
        root.getChildren().add(playership.getGameCharacter());
//        add asteroids

        asteroids.forEach(asteroid -> root.getChildren().add(asteroid.getGameCharacter()));

        // handles continuous inputs (as long as key is pressed)
        ArrayList<String> keyPressedList = new ArrayList<>();

        // handles discrete inputs (one per key press)
        ArrayList<String> keyJustPressedList = new ArrayList<>();

        mainScene.setOnKeyPressed(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();
                    //avoid adding duplicates to list
                    if (!keyPressedList.contains(keyName)) {
                        keyPressedList.add(keyName);
                        keyJustPressedList.add(keyName);
                    }
                }
        );

        mainScene.setOnKeyReleased(
                (KeyEvent event) -> {
                    String keyName = event.getCode().toString();
                    if (keyPressedList.contains(keyName)) {
                        keyPressedList.remove(keyName);
                    }
                }
        );


        AnimationTimer game = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (keyPressedList.contains("LEFT")) {
                    playership.turnLeft();
                }

                if (keyPressedList.contains("RIGHT")) {
                    playership.turnRight();
                }


                if (keyPressedList.contains("UP")) {
                    playership.accelerate(0.05);
                }

                if (keyPressedList.contains("SHIFT")) {
                    playership.hyperJump();
                }

                if (keyJustPressedList.contains("SPACE") ) {
                    // user can fire a bullet
//                    System.out.println("bullet has been shot");
                    Bullet bullet = new Bullet((int) playership.getGameCharacter().getTranslateX(), (int) playership.getGameCharacter().getTranslateY());
                    bullet.getGameCharacter().setRotate(playership.getGameCharacter().getRotate());
                    bulletList.add(bullet);

                    bullet.accelerate(0.08);
                    bullet.setMovement(bullet.getMovement().normalize().multiply(3));

                    root.getChildren().add(bullet.getGameCharacter());

                }
//                clears the keyPressesdlist ensures smooth pressing of bullets
                keyJustPressedList.clear();

//player moves
                playership.move();
//asteroids move
                asteroids.forEach(asteroid -> asteroid.move());

// getting the bullets from the bullet list ensuring they don't stay more than 5 seconds on the screen

                for (int n = 0; n < bulletList.size(); n++) {
                    Bullet bullet = bulletList.get(n);
                    bullet.move();
                    bullet.update(1 / 60.0);
                    if (bullet.elapseTimeSeconds > 2) {
                        bulletList.remove(n);
                        root.getChildren().remove(bullet.getGameCharacter());
                    }
                }

//               collision detection

                // disappear bullet when it hit
                List<Bullet> bulletToRemove = bulletList.stream().filter(bullet -> {
                    List<Asteroids> collisions = asteroids.stream()
                            .filter(asteroid -> asteroid.collision(bullet))
                            .collect(Collectors.toList());

                    if(collisions.isEmpty()){
                        return false;
                    }

                    collisions.stream().forEach(collided -> {
                        asteroids.remove(collided);
                        root.getChildren().remove(collided.getGameCharacter());

                        if (collided instanceof LargeAsteroid) {
                            for (int i = 0; i < 2; i++) {
                                MediumAsteroid asteroidM = new MediumAsteroid((int) collided.getGameCharacter().getTranslateX(), (int) collided.getGameCharacter().getTranslateY());
                                asteroids.add(asteroidM);
                                root.getChildren().add(asteroidM.getGameCharacter());
                                asteroids.forEach(asteroid -> asteroid.move());

                            }
                        } else if (collided instanceof MediumAsteroid) {
                            for (int i = 0; i < 2; i++) {
                                SmallAsteroid asteroidS = new SmallAsteroid((int) collided.getGameCharacter().getTranslateX(), (int) collided.getGameCharacter().getTranslateY());
                                asteroids.add(asteroidS);
                                root.getChildren().add(asteroidS.getGameCharacter());
                                asteroids.forEach(asteroid -> asteroid.move());

                            }
                        } else if (collided instanceof SmallAsteroid) {
                            asteroids.remove(collided);
                        }

                        asteroids.forEach(asteroid -> asteroid.move());
                    });

                    return true;
                }).collect(Collectors.toList());


                bulletToRemove.forEach(bullet -> {
                    root.getChildren().remove(bullet.getGameCharacter());
                    bulletList.remove(bullet);
                    // Create a variable to keep track of the current level

// Check if there are any asteroids left on the screen
                    int level = 1;

                    if (asteroids.isEmpty()) {
                        // Increase the level and add more large asteroids
                        level++;
                        for (int i = 0; i < level; i++) {
//        generating a random position to the
                            Random rnd = new Random();
// Generate a the first large asteroids
                            LargeAsteroid asteroid_to_add = new LargeAsteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
                            asteroids.add(asteroid_to_add);
                            root.getChildren().add(asteroid_to_add.getGameCharacter());
                            asteroids.forEach(asteroid -> asteroid.move());
                        }
                    }


                });

//checking if player collides with asteroid
                asteroids.forEach(asteroid -> {
                    if (playership.collision(asteroid)) {
//                        stop();
                        System.out.println("You die!");
                    }

                });



            }




        };
        game.start();
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