package Models;

public class Ghost extends Character {
    // fields

    // constructor
    public Ghost(int posX, int posY, boolean vulnerable, boolean eaten) {
        super(posX, posY, vulnerable, eaten);
    }

    // properties

    // methods

    public void beEaten(Pacman pacman) {
        setChanged();
        notifyObservers(null);//TODO determine what we send to Observers... and what class will observe us.
    }


}
