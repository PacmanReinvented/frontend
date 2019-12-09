package classes;

public class Ghost extends Character {
    // fields

    // constructor
    public Ghost(int posX, int posY, boolean vulnerable, boolean eaten) {
        super(posX, posY, vulnerable, eaten);
    }

    // properties

    // methods

    /**
     * What happens when we get eaten by a Pacman. Ideally called after a collsion has been detected.
     * @param pacman The Pacman who ate us
     */
    public void beEaten(Pacman pacman) {
        setChanged();
        notifyObservers(null);//TODO determine what we send to Observers... and what class will observe us.
    }


}
