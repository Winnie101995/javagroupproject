package application.asteroidsgameproject;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

public abstract class GameCharacters {

    private Polygon gamecharacter;
    private Point2D movement;
    public double elapseTimeSeconds;
    private double velocityX;

    private double velocityY;




    public void setMovement(Point2D newmovement){
        this.movement = newmovement;

    }

    public Point2D getMovement(){
        return this.movement;

    }

//    initilising a GameCharacter
    public GameCharacters(Polygon polygon, int x, int y) {
        this.gamecharacter = polygon;
        this.gamecharacter.setTranslateX(x);
        this.gamecharacter.setTranslateY(y);
        elapseTimeSeconds = 0;
        this.movement = new Point2D(0, 0);
    }
    public void update(double deltaTime) {
        // increase elapsed time for sprite
        elapseTimeSeconds += deltaTime;

        // update position according to velocity
        double x = getGameCharacter().getTranslateX() + movement.getX() * deltaTime;
        double y = getGameCharacter().getTranslateY() + movement.getY() * deltaTime;
        getGameCharacter().setTranslateX(x);
        getGameCharacter().setTranslateY(y);

        // handle screen wrapping
        if (x < 0) {
            getGameCharacter().setTranslateX(AsteroidsGame.WIDTH);
        } else if (x > AsteroidsGame.WIDTH) {
            getGameCharacter().setTranslateX(0);
        }
        if (y < 0) {
            getGameCharacter().setTranslateY(AsteroidsGame.HEIGHT);
        } else if (y > AsteroidsGame.HEIGHT) {
            getGameCharacter().setTranslateY(0);
        }
    }

    public Polygon getGameCharacter() {
        return gamecharacter;
    }


    public void turnLeft() {
        this.gamecharacter.setRotate(this.gamecharacter.getRotate() - 5);
    }

    public void turnRight() {
        this.gamecharacter.setRotate(this.gamecharacter.getRotate() + 5);
    }

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

    public void accelerate(double da) {
        double changeX = Math.cos(Math.toRadians(this.gamecharacter.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.gamecharacter.getRotate()));

        changeX *= da;
        changeY *= da;

        this.movement = this.movement.add(changeX, changeY);
    }

    //    checking for colloision
    public boolean collision(GameCharacters other){
        Shape collisionArea = Shape.intersect(this.gamecharacter, other.getGameCharacter());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
}