package Models;

import Enums.FruitType;
import Enums.MoveDirection;

import java.util.*;

public abstract class Character extends Observable implements IGameObject {
    // fields
    private int posX;
    private int posY;
    private int score;
    private boolean vulnerable;
    private boolean eaten;
    private List<Fruit> fruitList;

    // constructor
    public Character(int posX, int posY, boolean vulnerable, boolean eaten) {

        this.posX = posX;
        this.posY = posY;
        this.score = 0;
        this.vulnerable = vulnerable;
        this.eaten = eaten;
        fruitList = new ArrayList<Fruit>();
    }

    // properties
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getScore() {
        return score;
    }

    public boolean isEaten() {
        return eaten;
    }

    public boolean isVulnerable() {
        return vulnerable;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setVulnerable(boolean vulnerable) {
        this.vulnerable = vulnerable;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public List<Fruit> getFruitList() {
        return fruitList;
    }

    // methods
    public void addScore(Fruit fruit) {
        fruitList.add(fruit);
        score += fruit.getValue(fruit.getFruitType());
    }

    public void removeScore(int penalty) {
        score -= penalty;
    }

    /**
     * Moves the player a certain amount of tiles in a certain direction
     *
     * @param direction The direction we want to go in
     * @param amount    The amount of tiles we want to move
     */
    public void moveTowards(MoveDirection direction, int amount) {
        int x = posX;
        int y = posY;
        switch (direction) {
            case UP:
                y -= amount;
                break;
            case DOWN:
                y += amount;
                break;
            case LEFT:
                x -= amount;
                break;
            case RIGHT:
                x += amount;
                break;
        }
        setPosX(x);
        setPosY(y);
    }

    /**
     * Moves the player 1 tile in the desired direction.
     *
     * @param direction The direction we want to go in
     */
    public void moveTowards(MoveDirection direction) {
        moveTowards(direction, 1);
    }

    /**
     * The actions we must do when we collide with someone
     *
     * @param character The Character we collide with.
     */
    public void doCollision(Character character) {
        if (this instanceof Pacman && character instanceof Ghost) {
            OnCollide((Pacman) this, (Ghost) character);
        } else if (this instanceof Ghost && character instanceof Pacman) {
            OnCollide((Pacman) character, (Ghost) this);
        }
    }

    /**
     * Adds a certain amount of score to the player
     *
     * @param scoreToAdd The amount of points to add.
     */
    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }

    private static void OnCollide(Pacman pacman, Ghost ghost) {
        if (ghost.isVulnerable()) ghost.beEaten(pacman);
        else pacman.Kill();
    }

    /**
     * Kills the Character and removes it from the game.
     */
    public void Kill() {
        setChanged();
        notifyObservers(null);//TODO determine what we send to Observers... and what class will observe us.
    }

    @Override
    public boolean collidesWith(IGameObject gameObject) {
        return collidesWith(gameObject.getPosX(), gameObject.getPosY());
    }

    @Override
    public boolean collidesWith(int x, int y) {
        return getPosX() == x && getPosY() == y;
    }
}
