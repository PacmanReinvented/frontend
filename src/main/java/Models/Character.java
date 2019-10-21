package Models;

public abstract class Character
{
    // fields
    private int posX;
    private int posY;
    private int score;
    private boolean vulnerable;
    private boolean eaten;

    // constructor
    public Character(int posX, int posY, int score, boolean vulnerable, boolean eaten)
    {

        this.posX = posX;
        this.posY = posY;
        this.score = score;
        this.vulnerable = vulnerable;
        this.eaten = eaten;
    }

    // properties
    public int getPosX(){return posX;}
    public int getPosY(){return  posY;}
    public int getScore(){return score;}
    public boolean isEaten(){return eaten;}
    public boolean isVulnerable(){return vulnerable;}
    public void setPosX(int posX){this.posX = posX;}
    public void setPosY(int posY){this.posY = posY;}
    public void setScore(int score){this.score = score;}
    public void setVulnerable(boolean vulnerable){this.vulnerable = vulnerable;}
    public void setEaten(boolean eaten){this.eaten = eaten;}

    // methods
}
