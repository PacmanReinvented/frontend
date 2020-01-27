import classes.Fruit;
import classes.Pacman;
import classes.Pallet;
import classes.Wall;
import enums.FruitType;
import enums.MoveDirection;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ItemTest
{
    @Test
    public void getFruitType()
    {
        Fruit fruit = new Fruit(0,0, FruitType.Banana);
        FruitType expected = FruitType.Banana;
        FruitType actual = fruit.getFruitType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getFruitValue()
    {
        Fruit fruit = new Fruit(0,0, FruitType.Banana);
        int expected = 250;
        int actual = Fruit.getValue(fruit.getFruitType());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void palletIsSuper()
    {
        Pallet pallet = new Pallet(0,0, true, null);
        boolean expected = true;
        boolean actual = pallet.isSuper();
        Assert.assertEquals(expected, actual);

        Pallet pallet2 = new Pallet(0,0, false, null);
        boolean expected2 = false;
        boolean actual2 = pallet2.isSuper();
        Assert.assertEquals(expected2, actual2);
    }

    @Test
    public void itemCollidesWith()
    {
        Fruit fruit = new Fruit(0,0, FruitType.Banana);
        Fruit fruit2 = new Fruit(0,0, FruitType.Cherry);
        boolean expected = true;
        boolean actual = fruit.collidesWith(fruit2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itemDoesNotCollideWith()
    {
        Fruit fruit = new Fruit(0,0, FruitType.Banana);
        Fruit fruit2 = new Fruit(1,1, FruitType.Cherry);
        boolean expected = false;
        boolean actual = fruit.collidesWith(fruit2);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void cannotMoveThroughWall()
    {
        Wall wall = new Wall(0,0);
        Pacman pacman = new Pacman(0,1,false,false);
        boolean expected = false;
        boolean actual = wall.canMoveTo(pacman.getPosX(), pacman.getPosY(), 1, MoveDirection.LEFT);
        Assert.assertEquals(expected, actual);
    }
}
