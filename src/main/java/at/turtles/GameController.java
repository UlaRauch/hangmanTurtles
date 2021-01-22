package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for game.fxml
 */
public class GameController implements Initializable {

    private Hangman game;
    public final static String WINDOWTITLE = "Play";

    @FXML
    public Label wordLabel;

    @FXML
    public Label triesLabel;

    @FXML
    public Label instructionLabel;

    @FXML
    public Label commentLabel;

    @FXML
    public ImageView image;

    /**
     * is executed when loading window
     * initializes new Hangman object to create new game
     * shows _ for letters in word to be guessed
     * shows image of chosen animal
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Hangman(GameSettings.listPathOfChoice);
        GameSettings.wordToGuess = game.WORDTOGUESS;    //for use in finish window
        updateLabels();
        instructionLabel.setText("Animals can't read signs. \nFind the right letters to stop " + GameSettings.chosenAnimal + " from moving!");
        commentLabel.setText("Wrong guesses will scare " + GameSettings.chosenAnimal + "!");
        if (GameSettings.chosenAnimal.equals("Tina")) {
            image.setImage(new Image("tina/tina.001.png"));
        } else {
            image.setImage(new Image("franklin/franklin.001.png"));
        }
    }


    /**
     * updates labels in Window to show current game progress
     */
    private void updateLabels() {
        wordLabel.setText(String.valueOf(game.wordInProgress));
        triesLabel.setText(String.format("False tries: %d / %d", game.wrongGuesses, game.MAXNUMBEROFGUESSES));
        commentLabel.setText(Hangman.comments);
    }


    /**
     * is executed when a letter button is clicked
     * contains game logic for Hangman
     * reacts to guessed letter (was it already guessed?, is it correct?)
     * switches to Finish Window at the end of the game
     */
    public void letterButtonClicked(ActionEvent actionEvent) throws IOException {
        Button pressedButton = (Button) actionEvent.getSource();
        char letter = pressedButton.getText().charAt(0);
        System.out.println("Clicked " + letter);
        if (!game.checkIfAlreadyTyped(letter)) {
            if (game.existsInTheWord(letter)) {
                game.updateProgress(letter);
                pressedButton.setStyle("-fx-background-color: green;");
                game.alreadyUsed.add(letter);
                game.positiveComments();
            } else {
                game.wrongGuesses++;
                game.negativeComments();
                game.alreadyUsed.add(letter);
                pressedButton.setStyle("-fx-background-color: red;");

                if (game.wrongGuesses < game.MAXNUMBEROFGUESSES) {
                    if (GameSettings.chosenAnimal.equals("Tina")) {
                        image.setImage(new Image(String.format("tina/tinastep%d.gif", game.wrongGuesses)));
                    } else {
                        image.setImage(new Image(String.format("franklin/franklinjump%d.gif", game.wrongGuesses)));
                    }
                }
            }
            //pressedButton.setDisable(true);//prevents nasty comments (sameLetterComments)
            if (game.checkIfWon()) {
                game.finalReaction(true);//comment will be used in next window
                GameSettings.won = true;
                GameSettings.showWindow("/finish.fxml", FinishController.WINDOWTITLE);
            } else if (game.wrongGuesses == game.MAXNUMBEROFGUESSES) {
                game.finalReaction(false);//comment will be used in next window
                GameSettings.won = false;
                GameSettings.showWindow("/finish.fxml", FinishController.WINDOWTITLE);
            }
        } else { //if letter has already been clicked before - just for fun
            game.sameLetterComments(game.existsInTheWord(letter));
        }
        updateLabels();
    }

    /**
     * is executed when Back Button is clicked in GUI
     * Switches back to Choose List Window
     */
    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked back");
        GameSettings.showWindow("/chooseList.fxml", ChooseListController.WINDOWTITLE);
    }


}
