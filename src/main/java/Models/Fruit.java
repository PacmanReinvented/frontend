package Models;

import Enums.FruitType;

public class Fruit extends Item
{
    // fields
    private FruitType fruitType;

    // constructor
    public Fruit(int posX, int posY,  FruitType fruitType)
    {
        super(posX, posY);
        this.fruitType = fruitType;
    }

    // properties
    public FruitType getFruitType(){return fruitType;}

    // methods
    public static int getValue(FruitType fruitType)
    {
        return fruitType.getScoreValue();
    }
    @Override
    public void PickedUp(Pacman pacman){
        System.out.println("[Fruit.java] the type of fruit eaten was a "+fruitType);
        pacman.addScore(getValue(this.fruitType));
        super.PickedUp(pacman);
    }
}
