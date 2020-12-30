package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Finish {

    private static Hangman game;
    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;
    public final static String WINDOWTITLE = "Game";

    @FXML
    public Text status;
    @FXML
    private Label won;
    @FXML
    private Label lost;

    @FXML
    private Button changeB;
    @FXML
    private Button retryButton;
    @FXML
    private Button endButton;


    private void endLabelOutput(){
        game.reaction(game.checkIfWon());
    }


//https://edencoding.com/javafx-scene/#change-close-clear
    public void tryAgain(ActionEvent actionEvent) throws IOException {
    //go back to game.fxml
        GameSettings.showWindow("/game.fxml", GameController.WIDTH, GameController.HEIGHT, GameController.WINDOWTITLE);
    }


    public void animalChange(ActionEvent actionEvent) throws IOException {
        System.out.println("Back to Choose your fighter");
        GameSettings.showWindow("/choosefighters.fxml",
                ChooseFighters.WIDTH, ChooseFighters.HEIGHT, ChooseFighters.WINDOWTITLE);
    }


    public void endGame(ActionEvent actionEvent) {
        //go back to start.fxml
        Platform.exit();
    }
}
