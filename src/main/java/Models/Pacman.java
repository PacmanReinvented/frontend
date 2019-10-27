package Models;

public class Pacman extends Character
{
    // fields

    // constructor
    public Pacman(int posX, int posY, boolean vulnerable, boolean eaten)
    {
        super(posX, posY, vulnerable, eaten);
    }

    // properties

    // methods

    public void takeItem(Item item) {
        item.PickedUp(this);
    }
}
