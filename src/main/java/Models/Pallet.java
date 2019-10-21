package Models;

public class Pallet extends Item
{
    // fields
    private boolean isSuper;

    // constructor
    public Pallet(int posX, int posY, boolean isSuper)
    {
        super(posX, posY);
        this.isSuper = isSuper;
    }

    // properties
    public boolean isSuper(){return isSuper;}
    public void setSuper(boolean isSuper){this.isSuper = isSuper;}

    // methods
}
