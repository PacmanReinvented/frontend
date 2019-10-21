package UI;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class EntityFactory {

    public static Rectangle drawWall(int x, int y, int w, int h)
    {
        Rectangle wall = new Rectangle(w * x, h * y, w, h);
        wall.setFill(Color.valueOf("#474a48"));

        return wall;
    }

    public static Rectangle drawPacman(int x, int y, int w, int h)
    {
        Rectangle pacman = new Rectangle(w * x + w * 0.1, h * y + h * 0.1, w * 0.8, h * 0.8);
        pacman.setFill(Color.YELLOW);

        return pacman;
    }

    public static Circle drawPallet(int x, int y, int w, int h) {
        Circle Pallet = new Circle(w * x + w/2, h * y+ h/2, w *0.35);
        Pallet.setFill(Color.YELLOW);

        return Pallet;
    }

    public static Circle drawSuperPallet(int x, int y, int w, int h) {
        Circle Pallet = new Circle(w * x+ w/2, h * y+ h/2, w *0.35);
        Pallet.setFill(Color.RED);

        return Pallet;
    }
}
