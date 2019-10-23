package UI;
import Interfaces.ILogicGui;
import Enums.TileType;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application implements ILogicGui
{
    private Pane root = new Pane();

    private double t = 0;

    private Parent createContent() {
        root.setPrefSize(800, 600);

        updateCanvas(TestGrid);
        return root;
    }

    TileType[][] TestGrid = {
            {TileType.WALL, TileType.WALL,TileType.WALL,TileType.WALL},
                {TileType.PACMAN, TileType.PALLET,TileType.SUPERPALLET,TileType.SUPERPALLET},
            {TileType.WALL, TileType.EMPTY,TileType.WALL,TileType.WALL}
    };

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    //player.moveLeft();
                    break;
                case D:
                    //player.moveRight();
                    break;
                case SPACE:
                    //shoot(player);
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void updateCanvas(TileType[][] grid) {
        t += 0.016;

        int w = 600 / grid[0].length;
        int h = 600 / grid.length;

        root.getChildren().removeIf(n -> {return true;});


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                switch (grid[i][j]) {
                    case WALL:
                        root.getChildren().add(EntityFactory.drawWall(j, i, w, h));
                        break;
                    case PACMAN:
                        root.getChildren().add(EntityFactory.drawPacman(j, i, w, h));
                        break;
                    case PALLET:
                        root.getChildren().add(EntityFactory.drawPallet(j, i, w, h));
                        break;
                    case SUPERPALLET:
                        root.getChildren().add(EntityFactory.drawSuperPallet(j, i, w, h));
                        break;
                    case EMPTY:
                        break;
                }
            }
        }
    }

    @Override
    public void updateScoreboard(String[] scoreBoard) {

    }
}