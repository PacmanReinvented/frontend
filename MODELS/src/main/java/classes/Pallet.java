package classes;

public class Pallet extends Item {
    // fields
    private boolean isSuper;
    private final static int regularScore = 1;
    private ISuperPalletListener superPalletListener;

    // constructor
    public Pallet(int posX, int posY, boolean isSuper, ISuperPalletListener superPalletListener) {
        super(posX, posY);
        this.isSuper = isSuper;
        this.superPalletListener = superPalletListener;
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
        else superPalletListener.palletWasEaten(this);

        super.PickedUp(pacman);
    }

    // methods
}
