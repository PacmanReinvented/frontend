package UI;
import Interfaces.IGuiLogic;
import Interfaces.ILogicGui;
import Enums.TileType;
import Logic.CharacterManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements ILogicGui
{
    private Pane GameCanvas = new Pane();
    private Pane Container = new Pane();

    private double t = 0;

    private Pane createGameCanvas() throws IOException {
        GameCanvas.setPrefSize(600, 600);
        IGuiLogic Logic = new CharacterManager();
        TileType[][] grid = Logic.StartGame();
        updateCanvas(grid);
        return GameCanvas;
    }

    private Parent createParent() throws IOException {
        Container.setPrefSize(800, 600);
        Container.getChildren().add(createGameCanvas());
        return Container;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createParent());

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

        GameCanvas.getChildren().removeIf(n -> {return true;});


        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                switch (grid[i][j]) {
                    case WALL:
                        GameCanvas.getChildren().add(EntityFactory.drawWall(j, i, w, h));
                        break;
                    case PACMAN:
                        GameCanvas.getChildren().add(EntityFactory.drawPacman(j, i, w, h));
                        break;
                    case PALLET:
                        GameCanvas.getChildren().add(EntityFactory.drawPallet(j, i, w, h));
                        break;
                    case SUPERPALLET:
                        GameCanvas.getChildren().add(EntityFactory.drawSuperPallet(j, i, w, h));
                        break;
                    case EMPTY:
                        break;
                }
            }
        }
    }

    @Override
    public void updateScoreboard(String[] scoreBoard)
    {

    }
}
