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
        int value;
        switch(fruitType)
        {
            case Apple: value = 100;
            break;
            case Banana: value = 250;
            break;
            case Cherry: value = 500;
            break;
            default: value = 0;
            break;
        }
        return value;
    }
    @Override
    public void PickedUp(Pacman pacman){
        System.out.println("[Fruit.java] the type of fruit eaten was a "+fruitType);
        pacman.addScore(getValue(this.fruitType));
        super.PickedUp(pacman);
    }
}
