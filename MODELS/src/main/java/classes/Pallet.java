package classes;

public class Pallet extends Item {
    // fields
    private boolean isSuper;
    private final static int regularScore = 1;

    // constructor
    public Pallet(int posX, int posY, boolean isSuper) {
        super(posX, posY);
        this.isSuper = isSuper;
    }

    // properties
    public boolean isSuper() {
        return isSuper;
    }

    public void setSuper(boolean isSuper) {
        this.isSuper = isSuper;
    }

    @Override
    public void PickedUp(Pacman pacman) {

        if (!isSuper) {
            pacman.addScore(regularScore);
        }

        super.PickedUp(pacman);
    }

    // methods
}
