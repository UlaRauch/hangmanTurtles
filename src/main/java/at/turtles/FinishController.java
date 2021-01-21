package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FinishController implements Initializable {

    public final static String WINDOWTITLE = "Game";

    @FXML
    public Text comment;
    @FXML
    public Text yourWord;
    @FXML
    public ImageView image;


    /**
     * is executed when loading window
     * shows comment and image to reflect if game has been won
     * shows complete word of ended game
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comment.setText(Hangman.comments);
        yourWord.setText("Your word was " + String.valueOf(GameSettings.wordToGuess));
        if(GameSettings.chosenAnimal.equals("Tina")){
            if(GameSettings.won){
                image.setImage(new Image("tina/winningdance.gif"));
            }else {
                image.setImage(new Image("tina/tinadeath.gif"));
            }
        }else {
            if(GameSettings.won) {
                image.setImage(new Image("franklin/frog.png"));
            }else {
                image.setImage(new Image("franklin/franklindeath.gif"));
            }
        }
    }

    /**
     * is executed when Play again Button is clicked in GUI
     * Switches back to Game Window
     * starts new game with the same animal and word list
     */
    public void playAgain(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked Play Again");
        GameSettings.showWindow("/game.fxml", GameController.WINDOWTITLE);
    }


    /**
     * is executed when Change animal/list Button is clicked in GUI
     * Switches back to Choose Fighters Window
     */
    public void animalChange(ActionEvent actionEvent) throws IOException {
        System.out.println("Back to Choose your fighter");
        GameSettings.showWindow("/choosefighters.fxml", ChooseFightersController.WINDOWTITLE);
    }

    /**
     * is executed when Exit Button is clicked in GUI
     * exits the program
     */
    public void endGame(ActionEvent actionEvent) {
        System.out.println("Clicked exit");
        Platform.exit();
    }


}
