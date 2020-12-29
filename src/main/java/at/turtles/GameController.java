package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private static Hangman game;
    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;
    public final static String WINDOWTITLE = "Game";

    @FXML
    public Label wordLabel;

    @FXML
    public Label triesLabel;

    @FXML
    public Label commentLabel;

    private void updateLabels(){
        wordLabel.setText(String.valueOf(game.wordInProgress));
        triesLabel.setText(String.format("Fehlversuche: %d / %d", game.wrongGuesses, game.MAXNUMBEROFGUESSES));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Hangman(GameSettings.listPathOfChoice); //TODO: set GameSettings.wordToGuess
        GameSettings.wordToGuess = game.WORDTOGUESS;
        GameSettings.won = false;
        updateLabels();
    }

    public void letterButtonClicked(ActionEvent actionEvent) {
        Button pressedButton = (Button) actionEvent.getSource();
        char letter = pressedButton.getText().charAt(0);
        System.out.println("Clicked " + letter );
        if (!game.checkIfAlreadyTyped(letter)) {
            if (game.existsInTheWord(letter)){
                game.updateProgress(letter);
                pressedButton.setStyle("-fx-background-color: green;");
            } else {
                game.wrongGuesses++;
                pressedButton.setStyle("-fx-background-color: red;");
            }
            pressedButton.setDisable(true);
            if (game.checkIfWon()) { //TODO: set GameSettings.won
                System.out.println("You won!");
                GameSettings.won = true;
            } else if (game.wrongGuesses == game.MAXNUMBEROFGUESSES) {
                System.out.println("You lost!");
            }
        }
        updateLabels();
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException{
        //go back to chooseList.fxml
        GameSettings.showWindow("/chooseList.fxml", ChooseList.WIDTH, ChooseList.HEIGHT, ChooseList.WINDOWTITLE);
    }
}
