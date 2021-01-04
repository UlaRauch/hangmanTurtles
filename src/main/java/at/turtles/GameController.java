package at.turtles;

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
import javafx.scene.input.TouchEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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

    @FXML
    public ImageView image;
    public ImageView movingPic;


    private void updateLabels() {
        wordLabel.setText(String.valueOf(game.wordInProgress));
        triesLabel.setText(String.format("Fehlversuche: %d / %d", game.wrongGuesses, game.MAXNUMBEROFGUESSES));
        commentLabel.setText("Oh no! The " + GameSettings.chosenAnimal + " is in danger! Save it by finding the right letters!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Hangman(GameSettings.listPathOfChoice); //TODO: set GameSettings.wordToGuess
        GameSettings.wordToGuess = game.WORDTOGUESS;
        GameSettings.won = false;
        updateLabels();

        if (GameSettings.chosenAnimal == "turtle") {
            image.setImage(new Image("Tina/tina.001.png"));
        } else {
            image.setImage(new Image("Franklin/franklin.001.png"));
        }
    }

    //playing the game
    public void letterButtonClicked(ActionEvent actionEvent) throws IOException {
        Button pressedButton = (Button) actionEvent.getSource();
        char letter = pressedButton.getText().charAt(0);
        System.out.println("Clicked " + letter);
        if (!game.checkIfAlreadyTyped(letter)) {
            if (game.existsInTheWord(letter)) {
                game.updateProgress(letter);
                pressedButton.setStyle("-fx-background-color: green;");
                game.positiveComments(game.wrongGuesses);
                commentLabel.setText(Hangman.comments);//TODO: warum funktioniert setText hier schon wieder nicht?
            } else {
                game.wrongGuesses++;
                game.negativeComments(game.wrongGuesses); //TODO: in eigene Commentmethode zusammenfassen?
                commentLabel.setText(Hangman.comments);//TODO: setText funktioniert wieder nicht?
                pressedButton.setStyle("-fx-background-color: red;");

                if (GameSettings.chosenAnimal == "turtle") {
                    image.setImage(new Image("Tina/tina.gif"));
                } else {
                    image.setImage(new Image("Franklin/franklin.gif"));
                }
            }
            pressedButton.setDisable(true);//TODO: remove & add comment
            if (game.checkIfWon()) {
                GameSettings.won = true;//TODO: wird Gamesettings.won noch benötigt?
                game.finalReaction(game.checkIfWon());
                GameSettings.showWindow("/finish.fxml", Finish.WIDTH, Finish.HEIGHT, Finish.WINDOWTITLE);
            } else if (game.wrongGuesses == game.MAXNUMBEROFGUESSES) {
                game.finalReaction(game.checkIfWon());
                GameSettings.showWindow("/finish.fxml", Finish.WIDTH, Finish.HEIGHT, Finish.WINDOWTITLE);
            }
        }
        updateLabels();
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        //go back to chooseList.fxml
        GameSettings.showWindow("/chooseList.fxml", ChooseList.WIDTH, ChooseList.HEIGHT, ChooseList.WINDOWTITLE);
    }


}
