package classes;

import enums.MoveDirection;

public class Ghost extends Character {
    // fields
    private final static int maxVulnerableTime = 50;
    private int currentVurnerableTime = 0;
    private int startX,startY;
    // constructor
    public Ghost(int posX, int posY, boolean vulnerable, boolean eaten) {
        super(posX, posY, vulnerable, eaten);
        startX = posX;
        startY = posY;
    }

    // properties

    // methods


    @Override
    public void moveTowards(MoveDirection direction, int amount) {
        if(isVulnerable()){
            currentVurnerableTime++;
            if(currentVurnerableTime>= maxVulnerableTime){
                resetVulnerable();
            }
        }
        super.moveTowards(direction, amount);
    }

    /**
     * What happens when we get eaten by a Pacman. Ideally called after a collsion has been detected.
     * @param pacman The Pacman who ate us
     */
    public void beEaten(Pacman pacman) {
        resetVulnerable();
        setPosX(startX);
        setPosY(startY);
    }

    void resetVulnerable(){
        setVulnerable(false);
        currentVurnerableTime = 0;
    }


}
