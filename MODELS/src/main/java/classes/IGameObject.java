package classes;

public interface IGameObject {

    public int getPosX();

    public int getPosY();

    public void setPosX(int posX);

    public void setPosY(int posY);

    public boolean collidesWith(IGameObject gameObject);

    public boolean collidesWith(int x, int y);

}
