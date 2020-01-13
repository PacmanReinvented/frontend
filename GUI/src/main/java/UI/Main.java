package UI;

import Enums.LoginState;
import Enums.RegisterState;
import Interfaces.IPacmanServer;
import Interfaces.IPacmanClient;
import SocketClient.GameClientMessageSender;
import client.IRestTemplate;
import client.REST;
import com.mysql.cj.log.Log;
import entities.User;
import enums.GameState;
import enums.MoveDirection;
import enums.TileType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements IPacmanClient {
    private Pane GameCanvas = new Pane();
    private Pane ScoreCanvas = new Pane();
    private Pane Container = new Pane();

    private Button startGameButton = new Button();
    private Button loginButton = new Button();
    private Button registerButton = new Button();
    private TextField tbUsername = new TextField();
    private TextField tbPassword = new TextField();

    private double t = 0;
    private int playerNr = 1;
    //TODO get rid of the following thing for the server version
    private IPacmanServer Logic;
    private IRestTemplate restTemplate = new REST();


    private Pane createGameCanvas() throws IOException {
        GameCanvas.setPrefSize(600, 600);
        Logic = new GameClientMessageSender();
        //Logic.loginPlayer(this, "Standalone");
        GameCanvas.setStyle("-fx-background-color: #5fc964; -fx-margin: 0;");
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                startGame();
            }
        });
        startGameButton.setDisable(true);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loginUser();
            }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                registerUser();
            }
        });
        ScoreCanvas.getChildren().add(startGameButton);
        ScoreCanvas.getChildren().add(tbUsername);
        ScoreCanvas.getChildren().add(tbPassword);
        ScoreCanvas.getChildren().add(loginButton);
        ScoreCanvas.getChildren().add(registerButton);
        startGameButton.setText("Start Game");
        registerButton.setText("Register User");
        loginButton.setText("Login User");
        tbUsername.setLayoutX(50);
        tbUsername.setLayoutY(100);
        tbPassword.setLayoutX(50);
        tbPassword.setLayoutY(150);
        loginButton.setLayoutX(50);
        loginButton.setLayoutY(200);
        registerButton.setLayoutX(50);
        registerButton.setLayoutY(250);
        //updateCanvas(grid);
        return GameCanvas;
    }

    private void startGame() {
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

    private void showMessage(final String message, boolean waitGUI) {
        if (waitGUI) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pacman");
                    alert.setContentText(message);
                    alert.showAndWait();
                }
            });
        } else {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Pacman");
                    alert.setContentText(message);
                    alert.show();
                }
            });
        }
    }
    private void registerUser(){
        /*if (restTemplate.registerUser(new User(tbUsername.getText(), tbPassword.getText()))) {
            showMessage("Registration Successful!",true);
        } else {
            showMessage("Registration Failed!",true);
        } */
        Logic.registerPlayer(this, tbUsername.getText(), tbPassword.getText());
    }

    private void loginUser() {
        /*try {
            User user = restTemplate.loginUser(tbUsername.getText(), tbPassword.getText());
            if (user != null) {
                showMessage("Login Successful!",true);
            } else {
                showMessage("Login Failed! Incorrect Password",true);
            }
        } catch (Exception e) {
            showMessage("Username doesn't exist!",true);
        } */
        Logic.loginPlayer(this, tbUsername.getText(), tbPassword.getText());
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

        GameCanvas.getChildren().removeIf(n -> {
            return true;
        });


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
                        GameCanvas.getChildren().add(EntityFactory.drawFruit(j, i, w, h));
                        break;
                    case GHOST:
                        GameCanvas.getChildren().add(EntityFactory.drawGhost(j, i, w, h));
                        break;
                    case GHOSTVULNERABLE:
                        GameCanvas.getChildren().add(EntityFactory.drawVulnGhost(j, i, w, h));
                        break;
                    case EMPTY:
                        break;
                    default:
                        System.out.println("GUI: drawing a TileType of type has not yet been implemented " + grid[i][j]);

                }
            }
        }
    }

    @Override
    public void updateScoreboard(String[] scoreBoard) {
        //TODO poll Game Interface for some scores
        ScoreCanvas.getChildren().clear();
        for (double i = 0; i < scoreBoard.length; i++) {
            Text txt = new Text();
            txt.setStyle("-fx-fill: white; -fx-font: 14pxf Tahoma;");
            txt.setX(25);
            txt.setY(50 + (i * 40));
            txt.setText(scoreBoard[(int) i]);
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
    public void sendScoreList(String[] scores) {
        updateScoreboard(scores);
        System.out.println(scores[0]);
    }

    @Override
    public void receiveGameState(GameState gameState) {
        String message;
        switch (gameState) {
            case STARTED:
                message = "Game has started!";
                break;
            case PAUSED:
                message = "Game is paused...";
                break;
            case ALLITEMSEATEN:
                message = "Pacman was successful!";
                break;
            case PACMANDIED:
                message = "Pacman died!";
                break;
            case ENDED:
                message = "The game is over.";
                break;
            default:
                message = "You received an invalid gamestate: " + gameState;
                break;
        }
        showMessage(message,false);
    }

    @Override
    public void receiveLoginState(LoginState loginState, String username) {
        if(loginState == LoginState.SUCCESS){startGameButton.setDisable(false); }
        String message;
        switch(loginState){
            case SUCCESS:
                message = "Login Successful";
                break;
            case WRONGPASS:
                message = "Login Failed! Incorrect Password!";
                break;
            case WRONGUSER:
                message = "Login Failed! User doesn't exist!";
                break;
            default:
                message = "Unknown LoginState";
        }
        showMessage(message, false);
    }

    @Override
    public void receiveRegisterState(RegisterState registerState) {
        String message;
        switch(registerState){
            case SUCCESS:
                message = "Registration Successful!";
                break;
            case INVALIDDATA:
                message = "Registration Failed! Username or Password too short!";
                break;
            case USEREXISTS:
                message = "Registration Failed! User already exists!";
                break;
            default:
                message = "Unknown RegisterState: " + registerState;
        }
        showMessage(message, false);
    }

    @Override
    public int getID() {
        return this.playerNr;
    }
}