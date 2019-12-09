package classes;

import java.util.Observable;

public abstract class Item extends Observable implements IGameObject
{
    // fields
    private int posX;
    private int posY;
    private boolean eaten = false;

    // constructor
    public Item(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    // properties
    public int getPosX(){return posX;}
    public int getPosY(){return posY;}
    public void setPosX(int posX){this.posX = posX;}
    public void setPosY(int posY){this.posY = posY;}

    // methods

    /**
     * What happens when a Pacman picks up this object
     * @param pacman The pacman who picked us up
     */
    // methods
    public void PickedUp(Pacman pacman){
        setChanged();
        notifyObservers(pacman);
    }

    @Override
    public boolean collidesWith(IGameObject gameObject) {
        return collidesWith(gameObject.getPosX(),gameObject.getPosY());
    }

    @Override
    public boolean collidesWith(int x, int y) {
        return getPosX() == x && getPosY() == y;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }
}
