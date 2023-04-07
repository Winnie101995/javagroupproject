package application.asteroidsgameproject;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

//absract class that every cgame character will in herit from
public abstract class GameCharacters {

//    instance variable represents the game character as a polygon

    private Polygon gamecharacter;
    //    instance variable represents the movement (velocity) of the game character as a Point2D object.
    private Point2D movement;
    //instance variable represents the time elapsed for the game character, in seconds.
    public double elapseTimeSeconds;


    //This method sets the movement of an object to a new Point2D object.
    public void setMovement(Point2D newmovement){
        this.movement = newmovement;

    }
    //This method retrieves the current movement of an object as a Point2D object.
    public Point2D getMovement(){
        return this.movement;

    }

    //this constructor initializes a "GameCharacters" object with a given Polygon
    // object and position on the screen, and sets the elapsed time
    // and movement to default values.    public GameCharacters(Polygon polygon, int x, int y) {
    public GameCharacters(Polygon polygon, int x, int y) {
        this.gamecharacter = polygon;
        this.gamecharacter.setTranslateX(x);
        this.gamecharacter.setTranslateY(y);
        elapseTimeSeconds = 0;
        this.movement = new Point2D(0, 0);
    }

    //this method updates the elapsed time and position of a game
// character based on its velocity and elapsed time.
    public void update(double deltaTime) {
        // increase elapsed time for sprite
        elapseTimeSeconds += deltaTime;

        // update position according to velocity
        double x = getGameCharacter().getTranslateX() + movement.getX() * deltaTime;
        double y = getGameCharacter().getTranslateY() + movement.getY() * deltaTime;
        getGameCharacter().setTranslateX(x);
        getGameCharacter().setTranslateY(y);
    }
    //return gameCharacter
    public Polygon getGameCharacter() {
        return gamecharacter;
    }

    //enables gameCharacter to turnLeft
    public void turnLeft() {
        this.gamecharacter.setRotate(this.gamecharacter.getRotate() - 5);
    }
    //enables game character to turn right
    public void turnRight() {
        this.gamecharacter.setRotate(this.gamecharacter.getRotate() + 5);
    }
    //    this method updates the position of a game character, and handles
//    the wrapping of the character around the screen if it goes off the edges.
    public void move() {
        this.gamecharacter.setTranslateX(this.gamecharacter.getTranslateX() + this.movement.getX());
        this.gamecharacter.setTranslateY(this.gamecharacter.getTranslateY() + this.movement.getY());

        if (this.gamecharacter.getTranslateX() < 0) {
            this.gamecharacter.setTranslateX(this.gamecharacter.getTranslateX() + AsteroidsGame.WIDTH);
        }

        if (this.gamecharacter.getTranslateX() > AsteroidsGame.WIDTH) {
            this.gamecharacter.setTranslateX(this.gamecharacter.getTranslateX() % AsteroidsGame.WIDTH);
        }

        if (this.gamecharacter.getTranslateY() < 0) {
            this.gamecharacter.setTranslateY(this.gamecharacter.getTranslateY() + AsteroidsGame.HEIGHT);
        }

        if (this.gamecharacter.getTranslateY() > AsteroidsGame.HEIGHT) {
            this.gamecharacter.setTranslateY(this.gamecharacter.getTranslateY() % AsteroidsGame.HEIGHT);
        }
    }
    //        this method is used to manage acceleration as the gameCharacter moves faster
    public void accelerate(double da) {
        double changeX = Math.cos(Math.toRadians(this.gamecharacter.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.gamecharacter.getRotate()));

        changeX *= da;
        changeY *= da;

        this.movement = this.movement.add(changeX, changeY);
    }
    //    method is used for checking for collision
    public boolean collision(GameCharacters other){
        Shape collisionArea = Shape.intersect(this.gamecharacter, other.getGameCharacter());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
}