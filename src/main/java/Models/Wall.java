package Models;

import Enums.MoveDirection;

public class Wall implements IGameObject
{
    // fields
    private int posX;
    private int posY;

    // constructor
    public Wall(int posX, int posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    // properties
    public int getPosX(){return posX;}
    public int getPosY(){return posY;}
    public void setPosX(int posX){this.posX = posX;}
    public void setPosY(int posY){this.posY = posY;}

    // methods

    /**
     * @param startX    The X coordinate we're on before moving
     * @param startY    The Y coordinate we're on before moving
     * @param amount    The amount of tiles we're moving
     * @param direction The direction we're going in.
     * @return
     */
    public boolean canMoveTo(int startX, int startY, int amount, MoveDirection direction) {
        //The X coordinate we want to go to
        int prospectiveX = startX;
        //The Y coordinate we want to go to
        int prospectiveY = startY;
        switch (direction) {
            case UP:
                prospectiveX -= amount;
                break;
            case DOWN:
                prospectiveX += amount;
                break;
            case LEFT:
                prospectiveY -= amount;
                break;
            case RIGHT:
                prospectiveY += amount;
                break;
        }
        return !collidesWith(prospectiveX, prospectiveY);

    }

    @Override
    public boolean collidesWith(IGameObject gameObject) {
        return collidesWith(gameObject.getPosX(), gameObject.getPosY());
    }

    @Override
    public boolean collidesWith(int x, int y) {
        return getPosX() == x && getPosY() == y;
    }
}
