package UI;

import Enums.MoveDirection;
import Interfaces.IGuiLogic;
import Interfaces.ILogicGui;
import Enums.TileType;
import Logic.CharacterManager;
import Interfaces.enums.InputTypes;
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
    private int playerNr = 1;
    //TODO get rid of the following thing for the server version
    IGuiLogic Logic;


    private Pane createGameCanvas() throws IOException {
        GameCanvas.setPrefSize(600, 600);
        Logic = new CharacterManager();
        Logic.registerPlayer(this,"Standalone");
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
                    handleInput(InputTypes.MOVELEFT);
                    break;
                case D:
                    handleInput(InputTypes.MOVERIGHT);
                    break;
                case W:
                    handleInput(InputTypes.MOVEUP);
                    break;
                case S:
                    handleInput(InputTypes.MOVEDOWN);
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
                    case FRUIT:
                        GameCanvas.getChildren().add(EntityFactory.drawFruit(j,i,w,h));
                        break;
                    case GHOST:
                        GameCanvas.getChildren().add(EntityFactory.drawGhost(j,i,w,h));
                    case EMPTY:
                        break;
                    default:
                        System.out.println("GUI: drawing a TileType of type has not yet been implemented "+grid[i][j]);

                }
            }
        }
    }

    @Override
    public void updateScoreboard(String[] scoreBoard) {
        //TODO poll Game Interface for some scores
        throw new UnsupportedOperationException();
    }

    @Override
    public void handleInput(InputTypes inputType) {

        switch (inputType) {
            case MOVEUP:
                Logic.Move(MoveDirection.UP, playerNr);
                break;
            case MOVEDOWN:
                Logic.Move(MoveDirection.DOWN, playerNr);
                break;
            case MOVERIGHT:
                Logic.Move(MoveDirection.RIGHT, playerNr);
                break;
            case MOVELEFT:
                Logic.Move(MoveDirection.LEFT, playerNr);
                break;
            default:
                System.out.println("Input " + inputType + " has not yet been handled.");
        }
        //TODO send to a thing
        //throw new UnsupportedOperationException();
    }

    @Override
    public void setID(int playerNr) {
        this.playerNr = playerNr;
    }
}
