import classes.Ghost;
import classes.ISuperPalletListener;
import classes.Pacman;
import classes.Pallet;
import enums.MoveDirection;
import org.junit.Assert;
import org.junit.Test;

public class CharacterTest
{
    @Test
    public void correctScoreAdded()
    {
        Pacman pacman = new Pacman(0,0, false, false);
        pacman.addScore(100);
        int expected = 100;
        int actual = pacman.getScore();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void wrongScoreAdded()
    {
        Pacman pacman = new Pacman(0,0, false, false);
        pacman.addScore(100);
        int expected = 50;
        int actual = pacman.getScore();
        Assert.assertNotEquals(expected, actual);
    }

    @Test
    public void movePacmanToRight()
    {
        Pacman pacman = new Pacman(0,0, false, false);
        pacman.moveTowards(MoveDirection.RIGHT);
        int expected = 1;
        int actual = pacman.getPosY();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void movePacmanToLeft()
    {
        Pacman pacman = new Pacman(0,1, false, false);
        pacman.moveTowards(MoveDirection.LEFT);
        int expected = 0;
        int actual = pacman.getPosY();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void movePacmanUp()
    {
        Pacman pacman = new Pacman(1,0, false, false);
        pacman.moveTowards(MoveDirection.UP);
        int expected = 0;
        int actual = pacman.getPosX();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void movePacmanDown()
    {
        Pacman pacman = new Pacman(0,0, false, false);
        pacman.moveTowards(MoveDirection.DOWN);
        int expected = 1;
        int actual = pacman.getPosX();
        Assert.assertEquals(expected, actual);
    }
}

