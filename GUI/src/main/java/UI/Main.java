package UI;

import Interfaces.IPacmanServer;
import Interfaces.IPacmanClient;
import Logic.CharacterManager;
import SocketClient.GameClientMessageSender;
import enums.MoveDirection;
import enums.TileType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements IPacmanClient
{
    private Pane GameCanvas = new Pane();
    private Pane ScoreCanvas = new Pane();
    private Pane Container = new Pane();

    private Button startGameButton = new Button();

    private double t = 0;
    private int playerNr = 1;
    //TODO get rid of the following thing for the server version
    IPacmanServer Logic;


    private Pane createGameCanvas() throws IOException {
        GameCanvas.setPrefSize(600, 600);
        Logic = new GameClientMessageSender();
        Logic.registerPlayer(this,"Standalone");
        GameCanvas.setStyle("-fx-background-color: #5fc964; -fx-margin: 0;");
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startGame();
            }
        });
        ScoreCanvas.getChildren().add(startGameButton);
        startGameButton.setText("Start Game");
        //updateCanvas(grid);
        return GameCanvas;
    }
    private void startGame()
    {
        Logic.StartGame(this);
    }

    private Pane createScoreCanvas() throws IOException {
        ScoreCanvas.setPrefSize(200, 600);
        ScoreCanvas.setLayoutX(600);
        ScoreCanvas.setStyle("-fx-background-color: #57eb5e;");
        return ScoreCanvas;
    }

    private Parent createParent() throws IOException {
        Container.setPrefSize(800, 600);
        Container.getChildren().add(createGameCanvas());
        Container.getChildren().add(createScoreCanvas());
        return Container;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(createParent());

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case A:
                    handleInput(MoveDirection.LEFT);
                    break;
                case D:
                    handleInput(MoveDirection.RIGHT);
                    break;
                case W:
                    handleInput(MoveDirection.UP);
                    break;
                case S:
                    handleInput(MoveDirection.DOWN);
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
        ScoreCanvas.getChildren().clear();
        for (int i = 0; i < scoreBoard.length; i++)
        {
            Text txt = new Text();
            txt.setStyle("-fx-fill: white; -fx-font: 14pxf Tahoma;");
            txt.setX(25);
            txt.setY(50 + (i * 40));
            txt.setText(scoreBoard[i]);
            ScoreCanvas.getChildren().add(txt);
        }
    }

    @Override
    public void handleInput(MoveDirection inputType) {

        switch (inputType) {
            case UP:
                Logic.Move(enums.MoveDirection.UP, this);
                break;
            case DOWN:
                Logic.Move(enums.MoveDirection.DOWN, this);
                break;
            case RIGHT:
                Logic.Move(enums.MoveDirection.RIGHT, this);
                break;
            case LEFT:
                Logic.Move(enums.MoveDirection.LEFT, this);
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

    @Override
    public void sendScoreList(String[] scores)
    {
        updateScoreboard(scores);
        System.out.println(scores[0]);
    }

    @Override
    public int getID() {
        return this.playerNr;
    }
}