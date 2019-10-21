package Models;

public abstract class Item
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
}
