package Models;

import Enums.FruitType;

public class Fruit extends Item
{
    // fields
    private int value;
    private FruitType fruitType;

    // constructor
    public Fruit(int posX, int posY, int value, FruitType fruitType)
    {
        super(posX, posY);
        this.value = value;
        this.fruitType = fruitType;
    }

    // properties
    public FruitType getFruitType(){return fruitType;}

    // methods
    public int getValue(FruitType fruitType)
    {
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
}
