package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Finish implements Initializable {

    private static Hangman game;
    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;
    public final static String WINDOWTITLE = "Game";

    @FXML
    public Label wordLabel;
    @FXML
    public Text comment;
    @FXML
    public ImageView image;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comment.setText(Hangman.comments);
        wordLabel.setText(String.valueOf(GameSettings.wordToGuess));
    }
}
