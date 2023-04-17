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


    //WIP CURRENTLY - HYPERSPACE JUMPING TEST - can push this to end of code when working
    private void updateGameObjectsList(List<GameCharacters> gameObjects, AlienShip alienShip, List<Asteroids> asteroids, List<Bullet> bulletList, List<AlienBullet> alienBullets) {
        gameObjects.clear();
        gameObjects.addAll(asteroids);
        if (alienShipPresent){
            gameObjects.add(alienShip);
        }
        gameObjects.addAll(bulletList);
        gameObjects.addAll(alienBullets);
    }

    // WIP CURRENTLY

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
        AtomicInteger lives = new AtomicInteger(3); // set initial number of lives to 3

        // the score needed to earn an additional life
        // final one element array suggested by intellij
        final int[] newScore = {10000};
        Text scoreText = new Text();
        Text livesText = new Text("\nLives: " + "❤️ ".repeat(lives.get()));

        // Create the score and lives text nodes
        root.setStyle("-fx-background-color: black;");
        scoreText.setText("\nScore: " + score);
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
        List<AlienBullet> alienBullets = new ArrayList<>();

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

            private long AlienShotTimer = 0;

            @Override
            public void handle(long now) {

                    // Check if lives have reached zero
                    if (lives.get() == 0) {
                        stop();
                        Text gameOverText = new Text("GAME OVER");
                        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 72));
                        gameOverText.setFill(Color.RED);
                        root.setCenter(gameOverText);
                        return;
                    }

                // Update the lives whenever the score reaches a certain threshold
                if (score.get() >= newScore[0]) {
                    lives.incrementAndGet();
                    livesText.setText("\nLives: " + "❤️ ".repeat(lives.get()));
                    newScore[0] += 10000; // update the score threshold for the next life
                }


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
                        lives.decrementAndGet();
                        livesText.setText("\nLives: " + "❤️ ".repeat(lives.get()));
                    }
                });


                //  Collision between player ship and the alien ship

                alienShips.forEach(alien -> {
                    if (playership.collision(alien)) {
                        playership.hyperJump(gameObjects);
                        lives.decrementAndGet();
                        livesText.setText("\nLives: " + "❤️ ".repeat(lives.get()));
                    }
                });

// DAVID WIP - THINK I MIGHT NEED TO REMOVE THE ALIENSHIP FROM THIS LIST AS WELL ONCE THE BULLET HITS. THINK TWO LISTS MIGHT BE CAUSING THE ISSUES

// Spawn the alien ship if the score is divisible by 10 and there are no other alien ships present
                if (score.get() != 0 && score.get() % 20 == 0 && !alienShipPresent) {
                    AlienShip alienShip = spawnAlienShip();
                    alienShips.add(alienShip);
                    alienShipPresent = true;
                    root.getChildren().add(alienShip.getGameCharacter());
                    updateGameObjectsList(gameObjects, alienShip, asteroids, bulletList, alienBullets);
                }

// Check if the alien ship has been destroyed and remove it from the list
                Iterator<AlienShip> iterator = alienShips.iterator();
                while (iterator.hasNext()) {
                    AlienShip alien = iterator.next();
                    if (!root.getChildren().contains(alien.getGameCharacter())) {
                        iterator.remove();
                        updateGameObjectsList(gameObjects, alien, asteroids, bulletList, alienBullets);
//                        alienShipPresent = false;
                    }
                }

                if (alienShips.isEmpty()){
                    alienShipPresent = false;
                }

                // steps for getting the alien ship to fire bullets
                alienShips.forEach(alien -> {
                    // staggered shooting in nanoseconds = 2000000000 is two secs
                    if (now - AlienShotTimer >= 2000000000 && alien.isAlive()) {
                        // Next three lines updated to target the playership. It gets the change in x divided by the change in y = slope formula.
                        double deltaX = (playership.getGameCharacter().getTranslateX() - alien.getGameCharacter().getTranslateX());
                        double deltaY = (playership.getGameCharacter().getTranslateY() - alien.getGameCharacter().getTranslateY());
                        double shootingDirection = Math.toDegrees(Math.atan2(deltaY, deltaX));


                        AlienBullet bullet = new AlienBullet((int) alien.getGameCharacter().getTranslateX(), (int) alien.getGameCharacter().getTranslateY());
                        bullet.getGameCharacter().setRotate(shootingDirection);
                        alienBullets.add(bullet);

//                        this deals with alien bullet speed. adapt for harder game
                        bullet.accelerate(0.05);
                        bullet.setMovement(bullet.getMovement().normalize().multiply(2));

                        root.getChildren().add(bullet.getGameCharacter());

                        AlienShotTimer = now;
                        alien.accelerate(0.07);
                    }
                });

                alienBullets.forEach(bullet -> bullet.move());


                if (keyJustPressedList.contains("SPACE") ) {
                    // user can fire a bullet
                    Bullet bullet = new Bullet((int) playership.getGameCharacter().getTranslateX(), (int) playership.getGameCharacter().getTranslateY());
                    bullet.getGameCharacter().setRotate(playership.getGameCharacter().getRotate());
                    bulletList.add(bullet);

                    bullet.accelerate(0.3); // sped up bullet speed
                    bullet.setMovement(bullet.getMovement().normalize().multiply(3));



                    root.getChildren().add(bullet.getGameCharacter());

                }
//                clears the keyPressesdlist ensures smooth pressing of bullets
                keyJustPressedList.clear();

//player moves
                playership.move();
//asteroids move
                asteroids.forEach(asteroid -> asteroid.move());



                //WIP BELOW
//Alien Ship moves
                alienShips.forEach(alienShip -> alienShip.move(playership));
                alienShips.forEach(alienShip -> alienShip.update(1 / 60.0));



                //WIP ABOVE


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

                for (int n = 0; n < alienBullets.size(); n++) {
                    Bullet bullet = alienBullets.get(n);
                    bullet.move();
                    bullet.update(1 / 60.0);
                    if (bullet.elapseTimeSeconds > 5) {
                        alienBullets.remove(n);
                        root.getChildren().remove(bullet.getGameCharacter());
                    }
                }

//               WIP - collision detection
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

//  bullets and aliens

                List<Bullet> bulletToRemove2 = bulletList.stream().filter(bullet -> {
                    List<AlienShip> alienShipCollisions = alienShips.stream()
                            .filter(alienShip -> alienShip.collision(bullet))
                            .collect(Collectors.toList());

                    if(alienShipCollisions.isEmpty()){
                        return false;
                    }

                    AlienShip collidedAlienShip = alienShipCollisions.get(0);
                    root.getChildren().remove(collidedAlienShip.getGameCharacter());
                    alienShips.remove(collidedAlienShip);
                    score.addAndGet(300);
                    scoreText.setText("\nScore: " + score);
                    return true;
                }).collect(Collectors.toList());

//remove bullets once they have hit the alien ship
                bulletToRemove2.forEach(bullet2 -> {
                    root.getChildren().remove(bullet2.getGameCharacter());
                    bulletList.remove(bullet2);
                });


//DM UPDATED BELOW

//remove alien bullets once they have hit player ship and incorporate player damage / hyperjump

                alienBullets.forEach(bullet -> {
                    if (playership.collision(bullet)) {
                        bullet.setAlive(false);
                        if(bullet.isAlive() == false){
                            root.getChildren().remove(bullet.getGameCharacter());
                            lives.decrementAndGet();
                            livesText.setText("\nLives: " + "❤️ ".repeat(lives.get()));

                        }


                        // Spawns player ship in a safe location
                        playership.hyperJump(gameObjects);
                        score.addAndGet(-500);
                        scoreText.setText("\nScore: " + score);
                    }
                });


                List<Bullet> alienBulletToRemove;
                alienBulletToRemove = alienBullets.stream().filter(bullet -> {
                    List<AlienShip> alienShipCollisions = alienShips.stream()
                            .filter(alienShip -> alienShip.collision(bullet))
                            .collect(Collectors.toList());

                    if(alienShipCollisions.isEmpty()){
                        return false;
                    }

                    return true;
                }).collect(Collectors.toList());



                    // DM updated above - seems to be working fine

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



                    // Check for collision and play audio
                    asteroids.forEach(asteroid -> {
                        if (playership.collision(asteroid)) {
                            // Stop the game (if necessary)
                            // stop();


                            // Print a message (if necessary)
                            // System.out.println("You die!");
                        }
                    });
            }
        };
        game.start();
        mainStage.show();
        //for debugging working directory
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

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



    // Method for getting a list of characters capable of colliding with player ship
//    public List<GameCharacters> getCollisionCharacters(List<GameCharacters> characters) {
//        List<GameCharacters> collisionCharacters = new ArrayList<>();
//        for (GameCharacters character : characters) {
//            if (character instanceof Asteroid || character instanceof AlienShip) {
//                collisionCharacters.add(character);
//            }
//        }
//        return collisionCharacters;
//    }
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