package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    private static Hangman game;

    @FXML
    public Label wordLabel;

    @FXML
    public Label triesLabel;

    private void updateLabels(){
        wordLabel.setText(String.valueOf(game.wordInProgress));
        triesLabel.setText(String.format("Fehlversuche: %d / %d", game.wrongGuesses, game.MAXNUMBEROFGUESSES));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Hangman("build/resources/main/testWords.txt");
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
            if (game.checkIfWon()) {
                System.out.println("You won!");
            } else if (game.wrongGuesses == game.MAXNUMBEROFGUESSES) {
                System.out.println("You lost!");
            }
        }
        updateLabels();
    }
}
