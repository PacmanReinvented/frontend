package UI;
<<<<<<< HEAD
import Logic.MapReaderWriter;
=======
import Enums.TileType;
>>>>>>> be02becd9e91e35bca1c391bdd56e81c746e488a
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application
{
    private Pane root = new Pane();

    private double t = 0;

    private Parent createContent() {
        root.setPrefSize(800, 600);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        try {
            MapReaderWriter.getMapFromFile("maps/textmap.pacmanmap");
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer.start();

        return root;
    }

    TileType[][] grid = {
            {TileType.WALL, TileType.WALL,TileType.WALL},
                {TileType.PACMAN, TileType.PALLET,TileType.SUPERPALLET},
            {TileType.WALL, TileType.EMPTY,TileType.WALL}
    };



    private void update() {
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
            //System.out.println(root.getChildren().size());
    }

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
}
