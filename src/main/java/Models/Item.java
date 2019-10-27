package Models;

import java.util.Observable;

public abstract class Item extends Observable implements IGameObject
{
    // fields
    private int posX;
    private int posY;

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

    // methods
    public void PickedUp(Pacman pacman){
        System.out.println("Has the Game class been made the observer of this class?");
        setChanged();
        notifyObservers(this);//TODO determine what we will send to observers... And who will observe us?
    }

    @Override
    public boolean collidesWith(IGameObject gameObject) {
        return collidesWith(gameObject.getPosX(),gameObject.getPosY());
    }

    @Override
    public boolean collidesWith(int x, int y) {
        return getPosX() == x && getPosY() == y;
    }
}
