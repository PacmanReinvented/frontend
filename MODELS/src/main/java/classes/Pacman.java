package classes;

public class Pacman extends Character {
    // fields

    // constructor
    public Pacman(int posX, int posY, boolean vulnerable, boolean eaten) {
        super(posX, posY, vulnerable, eaten);
    }

    // properties

    // methods

    /**
     * Picks up an item
     *
     * @param item The item to take
     */
    public void takeItem(Item item) {
        item.PickedUp(this);
    }
}
