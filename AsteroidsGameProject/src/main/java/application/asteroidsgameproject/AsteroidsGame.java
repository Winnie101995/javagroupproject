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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.VBox;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioInputStream;
import java.io.File;
import javafx.geometry.Point2D;


//the larger game class that extends application
public class AsteroidsGame extends Application {

    //This variables that static means that they can be accessed using the class name rather than through an object of the class.
    public static int WIDTH;
    public static int HEIGHT;
    public static int level = 1;
    Random rnd = new Random();

    // collision related tests variable
    private boolean hyperJumpPressed = false;
    // Declare a boolean variable to keep track of whether an alien ship is present or not
    public boolean alienShipPresent = false;

    private void updateGameObjectsList(List<GameCharacters> gameObjects, AlienShip alienShip, List<Asteroids> asteroids) {
        gameObjects.clear();
        gameObjects.add(alienShip);
        gameObjects.addAll(asteroids);
    }

    @Override
    public void start(Stage mainStage) throws IOException {
        // Get the dimensions of the primary screen
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        WIDTH = (int) primaryScreenBounds.getWidth();
        HEIGHT = (int) primaryScreenBounds.getHeight();

        // Create the scene and canvas
        BorderPane root = new BorderPane();
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.setTitle("Asteroids Game");


        // Initialize the score variable
        AtomicInteger score = new AtomicInteger();
        Text scoreText = new Text();
        // Create the score and lives text nodes
        root.setStyle("-fx-background-color: black;");
        scoreText.setText("\nScore: " + score);
        Text livesText = new Text("\nLives: ❤️ ❤️ ❤️");
        scoreText.setFill(Color.WHITE);
        livesText.setFill(Color.WHITE);
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        livesText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        VBox topBox = new VBox();
        topBox.getChildren().addAll(scoreText, livesText);
        root.setTop(topBox);
        livesText.setLayoutY(AsteroidsGame.HEIGHT - 50);


        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.setCenter(canvas);
//fill screen color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);
//initialising playership
        PlayerShip playership = new PlayerShip(WIDTH/2, HEIGHT/2);
//        add player to game window
        root.getChildren().add(playership.getGameCharacter());

//initialising alien ship
//        AlienShip alienShip = new AlienShip (rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
//        root.getChildren().add(alienShip.getGameCharacter());



// related to alien ship implementation
//        int frameCounter = 0;
//        final Point2D alienShipVelocity = new Point2D(5, 0);

// move the alien ship horizontally across the screen
//        AnimationTimer alienShipMovement = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                alienShipVelocity = alienShipVelocity.normalize().multiply(5);
//                alienShip.move();
//            }
//        };
//        alienShipMovement.start();



//initialising a list of asteroids
        ArrayList<Asteroids> asteroids = new ArrayList<>();

//  this list relates to the hyperjumping testing
        List<GameCharacters> gameObjects = new ArrayList<>();

// WIP       gameObjects.add(alienShip);
        gameObjects.addAll(asteroids);
// need to test whether this updates / deletes the lists

//generating a random position to the first large asteroids
        LargeAsteroid asteroid_one = new LargeAsteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
//add large asteroid to asteroid list
        asteroids.add(asteroid_one);
//        add asteroid to game window
        asteroids.forEach(asteroid -> root.getChildren().add(asteroid.getGameCharacter()));
// initialising a list of bullets
        ArrayList<Bullet> bulletList = new ArrayList<>();
        // handles continuous inputs (as long as key is pressed)
        ArrayList<String> keyPressedList = new ArrayList<>();

        // handles discrete inputs (one per key press)
        ArrayList<String> keyJustPressedList = new ArrayList<>();

        //WIP DM alien ship
        List<AlienShip> alienShips = new ArrayList<>();

//captures the pressed key and adds it to two ArrayLists: "keyPressedList" and "keyJustPressedList".
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
//captures the released key and removes it from the "keyPressedList" ArrayList if it was already in the list.
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
                    playership.accelerate(0.07);
                }

                if (keyPressedList.contains("SHIFT") && !hyperJumpPressed) {
                    hyperJumpPressed = true;
                    playership.hyperJump(gameObjects);
                } else if (!keyPressedList.contains("SHIFT")) {
                    hyperJumpPressed = false;
                }

                //checking if player collides with asteroid
                // @Paul - lets discuss (w/ David) re lives if here is the place to implement negative counter and that this could work with your life recording methods.
                asteroids.forEach(asteroid -> {
                    if (playership.collision(asteroid)) {
                        playership.hyperJump(gameObjects);
                    }
                });


//                // Declare a boolean variable to keep track of whether an alien ship is present or not
//                boolean alienShipPresent = false;


// Spawn the alien ship if the score is divisible by 10 and there are no other alien ships present
                if (score.get() > 0 && score.get() % 10 == 0 && !alienShipPresent) {
                    AlienShip alienShip = spawnAlienShip();
                    alienShips.add(alienShip);
                    alienShipPresent = true;
                    root.getChildren().add(alienShip.getGameCharacter());
                }

// Check if the alien ship has been destroyed and remove it from the list
                Iterator<AlienShip> iterator = alienShips.iterator();
                while (iterator.hasNext()) {
                    AlienShip alien = iterator.next();
                    if (!root.getChildren().contains(alien.getGameCharacter())) {
                        iterator.remove();
                        alienShipPresent = false;
                    }
                }


//                if (score.get() % 10 == 0 && !alienShipPresent) {
////                    List<AlienShip> alienShips = new ArrayList<>();
//                    AlienShip alienShip = spawnAlienShip();
//                    alienShips.add(alienShip);
//                    root.getChildren().add(alienShip.getGameCharacter());
//
//                    // Set alienShipPresent to true
//                    alienShipPresent = true;
//                }
//
//// Check if the alien ship has been destroyed and set alienShipPresent to false
//                alienShips.forEach(alien -> {
//                    if (!alien.isAlive()) {
//                        root.getChildren().remove(alien.getGameCharacter());
//                        alienShips.remove(alien);
//                        alienShipPresent = false;
//                    }
//                });


                // THIS VERSION WAS SPAWNING MULTIPLE SHIPS - Spawns the enemy ship and adds it to the screen

//                List<AlienShip> alienShips = new ArrayList<>();
//                AlienShip alienShip = spawnAlienShip();
//
//                alienShips.add(alienShip);
//                alienShips.forEach(alien -> {
//                    root.getChildren().add(alien.getGameCharacter());
//
//                });


                if (keyJustPressedList.contains("SPACE") ) {
                    // user can fire a bullet
                    Bullet bullet = new Bullet((int) playership.getGameCharacter().getTranslateX(), (int) playership.getGameCharacter().getTranslateY());
                    bullet.getGameCharacter().setRotate(playership.getGameCharacter().getRotate());
                    bulletList.add(bullet);

                    bullet.accelerate(0.3); // sped up bullet speed
                    bullet.setMovement(bullet.getMovement().normalize().multiply(3));

                    // Play the fire sound
                    playSound("./fire.mp3");

                    root.getChildren().add(bullet.getGameCharacter());

                }
//                clears the keyPressesdlist ensures smooth pressing of bullets
                keyJustPressedList.clear();

//player moves
                playership.move();
//asteroids move
                asteroids.forEach(asteroid -> asteroid.move());

//Alien Ship moves
                alienShips.forEach(alienShip -> alienShip.move());




// getting the bullets from the bullet list ensuring they don't stay more than 5 seconds on the screen

                for (int n = 0; n < bulletList.size(); n++) {
                    Bullet bullet = bulletList.get(n);
                    bullet.move();
                    bullet.update(1 / 60.0);
                    if (bullet.elapseTimeSeconds > 5) {
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
                                //  updating the collision list on every change for objects on the screen which are not the player
                                asteroids.forEach(asteroid -> asteroid.move());
                                score.addAndGet(10);
//
                            }

                        } else if (collided instanceof MediumAsteroid) {
                            for (int i = 0; i < 2; i++) {
                                SmallAsteroid asteroidS = new SmallAsteroid((int) collided.getGameCharacter().getTranslateX(), (int) collided.getGameCharacter().getTranslateY());
                                asteroids.add(asteroidS);
                                root.getChildren().add(asteroidS.getGameCharacter());
                                asteroids.forEach(asteroid -> asteroid.move());
                                score.addAndGet(25);
//
                            }

                            //
                        } else if (collided instanceof SmallAsteroid) {
                            asteroids.remove(collided);
                            score.addAndGet(100);
                        }

                        scoreText.setText("\nScore: " + score);
                        asteroids.forEach(asteroid -> asteroid.move());
                    });
                    return true;
                }).collect(Collectors.toList());

//remove bullets once they have hit the asteroid
                bulletToRemove.forEach(bullet -> {
                    root.getChildren().remove(bullet.getGameCharacter());
                    bulletList.remove(bullet);

// Check if there are any asteroids left on the screen

                    if (asteroids.isEmpty()) {
                        // Increase the level and add more large asteroids
                        level++;
                        System.out.print("Level" + level);


                        int numLargeAsteroids = (int) Math.ceil(level * 1); // Formula to calculate number of large asteroids
                        for (int i = 0; i < numLargeAsteroids; i++) {
                            System.out.println("NUMBER OF ASTEROIDS" + numLargeAsteroids);
////                            // generating a random position to the
                            Random rnd = new Random();
//                            // Generate a the first large asteroids
                            LargeAsteroid asteroid_to_add_next_level = new LargeAsteroid(rnd.nextInt(WIDTH), rnd.nextInt(HEIGHT));
                            asteroids.add(asteroid_to_add_next_level);
                            root.getChildren().add(asteroid_to_add_next_level.getGameCharacter());
                            asteroids.forEach(asteroid -> asteroid.move());
                        }
                    }

                });

//checking if player collides with asteroid
                asteroids.forEach(asteroid -> {
                    if (playership.collision(asteroid)) {
//                        stop();
//                        System.out.println("You die!");
                    }

                });






            }




        };
        game.start();
        mainStage.show();

    }

    // This should spawn the alien ship on either side of the screen, provides part of the random element of the ship's transit
    public AlienShip spawnAlienShip() {
        int spawnRight = WIDTH + 50;
        int spawnLeft = WIDTH - 50;
        int spawnLocation = new Random().nextBoolean() ? spawnRight : spawnLeft;

        // The Y-Axis spawn is also random (20 - HEIGHT currently)
        Random r = new Random();
        int randInt = r.nextInt(HEIGHT-20) + 20;

        // Create an enemy ship which spawns in the random location selected above
        AlienShip alienShip = new AlienShip(spawnLocation, randInt);

        return alienShip;
    }

    private void playSound(String filePath) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip.open(inputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
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