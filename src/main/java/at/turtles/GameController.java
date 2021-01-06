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
    public final static String WINDOWTITLE = "Play";

    @FXML
    public Label wordLabel;

    @FXML
    public Label triesLabel;

    @FXML
    public Label commentLabel;

    @FXML
    public ImageView image;


    private void updateLabels() {
        wordLabel.setText(String.valueOf(game.wordInProgress));
        triesLabel.setText(String.format("False tries: %d / %d", game.wrongGuesses, game.MAXNUMBEROFGUESSES));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Hangman(GameSettings.listPathOfChoice); //TODO: set GameSettings.wordToGuess
        GameSettings.wordToGuess = game.WORDTOGUESS;
        updateLabels();
        if (GameSettings.chosenAnimal.equals("Tina")) {
            image.setImage(new Image("Tina/tina.001.png"));
            commentLabel.setText("Oh no! Tina is in danger! Save her by finding the right letters!");

        } else {
            image.setImage(new Image("Franklin/franklin.001.png"));
            commentLabel.setText("Oh no! Franklin is in danger! Save him by finding the right letters!");
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
                game.alreadyGuessed.add(letter); //add letter to already used letters
                game.positiveComments(game.wrongGuesses);//TODO: in eigene Commentmethode zusammenfassen?
                commentLabel.setText(Hangman.comments);
            } else {
                game.wrongGuesses++;
                game.negativeComments(game.wrongGuesses); //TODO: in eigene Commentmethode zusammenfassen?
                game.alreadyGuessed.add(letter); //add letter to already used letters
                commentLabel.setText(Hangman.comments);
                pressedButton.setStyle("-fx-background-color: red;");

                if(GameSettings.chosenAnimal.equals("Tina")){ //TODO: Vorschlag: Methode in Gamesettings draus machen?
                    if(game.wrongGuesses == 1){ image.setImage(new Image("Tina/tina.gif"));}
                    if(game.wrongGuesses == 2){ image.setImage(new Image("Tina/tinastep2.gif")); }
                    if(game.wrongGuesses == 3){ image.setImage(new Image("Tina/tinastep3.gif")); }
                    if(game.wrongGuesses == 4){ image.setImage(new Image("Tina/tinastep4.gif")); }
                    if(game.wrongGuesses == 5){ image.setImage(new Image("Tina/tinastep5.gif")); }
                }else if (game.wrongGuesses < game.MAXNUMBEROFGUESSES){
                    image.setImage(new Image("Franklin/franklin.gif"));
                    if(GameSettings.chosenAnimal.equals("Franklin") && game.wrongGuesses % 2 == 0){
                        image.setImage(new Image("Franklin/frogjump2.gif"));
                    }
                }
            }
            //pressedButton.setDisable(true);//prevents nasty comments (sameLetterComments)
            if (game.checkIfWon()) {
                game.finalReaction(game.checkIfWon());//comment will be used in next window
                GameSettings.showWindow("/finish.fxml", Finish.WIDTH, Finish.HEIGHT, Finish.WINDOWTITLE);
            } else if (game.wrongGuesses == game.MAXNUMBEROFGUESSES) {
                game.finalReaction(game.checkIfWon());//comment will be used in next window
                GameSettings.showWindow("/finish.fxml", Finish.WIDTH, Finish.HEIGHT, Finish.WINDOWTITLE);
            }
        } else { //if letter has already been clicked before - just for fun
            game.sameLetterComments(game.existsInTheWord(letter));
            commentLabel.setText(Hangman.comments);
        }
        updateLabels();
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException {
        //go back to chooseList.fxml
        GameSettings.showWindow("/chooseList.fxml", ChooseList.WIDTH, ChooseList.HEIGHT, ChooseList.WINDOWTITLE);
    }


}
