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

    private static Hangman game;
    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;
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
                image.setImage(new Image("Tina/winningdance.gif"));
            }else {
                image.setImage(new Image("Tina/tinadeath.gif"));
            }
        }else {
            if(!GameSettings.won) {
                image.setImage(new Image("Franklin/franklindeath.gif"));
            }else {
                image.setImage(new Image("Franklin/frog.png"));
            }
        }
    }

    /**
     * is executed when Play again Button is clicked in GUI
     * Switches back to Game Window
     * starts new game with the same animal and word list
     */
    public void tryAgain(ActionEvent actionEvent) throws IOException {
        GameSettings.showWindow("/game.fxml", GameController.WIDTH, GameController.HEIGHT, GameController.WINDOWTITLE);
    }


    /**
     * is executed when Change animal/list Button is clicked in GUI
     * Switches back to Choose Fighters Window
     */
    public void animalChange(ActionEvent actionEvent) throws IOException {
        System.out.println("Back to Choose your fighter");
        GameSettings.showWindow("/choosefighters.fxml",
                ChooseFightersController.WIDTH, ChooseFightersController.HEIGHT, ChooseFightersController.WINDOWTITLE);
    }

    /**
     * is executed when Exit Button is clicked in GUI
     * exits the program
     */
    public void endGame(ActionEvent actionEvent) {
        Platform.exit();
    }


}