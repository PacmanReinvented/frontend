import classes.Pacman;
import enums.MoveDirection;
import org.junit.Assert;
import org.junit.Test;

public class CharacterTest
{
    /*@Test
    public void ghostIsEaten()
    {
        Ghost ghost = new Ghost(0,0, true, false);
        Pacman pacman = new Pacman(0,0, false, false);
        pacman.collidesWith(ghost);
        boolean expected = true;
        boolean actual = ghost.isEaten();
        Assert.assertEquals(expected,actual);
    } */

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

    /*@Test
    public void pacmanGetsEaten()
    {
      Pacman pacman = new Pacman(0,0,true, false);
      Ghost ghost = new Ghost(0,1,false,false);
      ghost.moveTowards(MoveDirection.LEFT);
      ghost.collidesWith(pacman);
      boolean expected = true;
      boolean actual = pacman.isEaten();
      Assert.assertEquals(expected, actual);
    } */

    /*@Test
    public void ghostsBecomeVulnerable()
    {
      Pacman pacman = new Pacman(0,0, false, false);
      Ghost ghost = new Ghost(4,4, false,false);
      Pallet pallet = new Pallet(0,0, true);
      pacman.takeItem(pallet);
      boolean expected = true;
      boolean actual = ghost.isVulnerable();
      Assert.assertEquals(expected, actual);
    } */
}

