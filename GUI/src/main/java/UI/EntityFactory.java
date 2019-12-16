package UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EntityFactory {

    static Image WallIMG = new Image("file:GUI\\src\\main\\java\\UI\\assets\\wall.png");
    static Image PacManIMG = new Image("file:GUI\\src\\main\\java\\UI\\assets\\pacman.gif");
    static Image SuperPalletIMG = new Image("file:GUI\\src\\main\\java\\UI\\assets\\coin.gif");
    static Image PalletIMG = new Image("file:GUI\\src\\main\\java\\UI\\assets\\coin.png");
    static Image GhostIMG = new Image("file:GUI\\src\\main\\java\\UI\\assets\\ghost.png");
    static Image ghostVuln = new Image("file:GUI\\src\\main\\java\\UI\\assets\\ghostvulnerable.png");
    static Image FruitIMG = new Image("file:GUI\\src\\main\\java\\UI\\assets\\fruit.png");


    public static ImageView drawWall(int x, int y, int w, int h)
    {
        ImageView iv = new ImageView();
        iv.setImage(WallIMG);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setX(x * w);
        iv.setY(y * h);

        return iv;
    }

    public static ImageView drawPacman(int x, int y, int w, int h) {

        ImageView iv = new ImageView();
        iv.setImage(PacManIMG);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setX(x * w);
        iv.setY(y * h);

        return iv;
    }

    public static ImageView drawPallet(int x, int y, int w, int h) {

        ImageView iv = new ImageView();
        iv.setImage(PalletIMG);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setX(x * w);
        iv.setY(y * h);

        return iv;
    }

    public static ImageView drawSuperPallet(int x, int y, int w, int h) {
        ImageView iv = new ImageView();
        iv.setImage(SuperPalletIMG);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setX(x * w);
        iv.setY(y * h);

        return iv;
    }

    public static ImageView drawFruit(int x, int y, int w, int h) {
        ImageView iv = new ImageView();
        iv.setImage(FruitIMG);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setX(x * w);
        iv.setY(y * h);

        return iv;
    }

    public static ImageView drawGhost(int x, int y, int w, int h) {
        ImageView iv = new ImageView();
        iv.setImage(GhostIMG);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setX(x * w);
        iv.setY(y * h);

        return iv;
    }

    public static ImageView drawVulnGhost(int x, int y, int w, int h) {
        ImageView iv = new ImageView();
        iv.setImage(ghostVuln);
        iv.setFitWidth(w);
        iv.setFitHeight(h);
        iv.setX(x * w);
        iv.setY(y * h);

        return iv;
    }
}

