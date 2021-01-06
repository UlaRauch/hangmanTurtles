package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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

    /**
     * is executed when loading window
     * shows comment and image to reflect if game has been won
     * shows complete word of ended game
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comment.setText(Hangman.comments);
        wordLabel.setText(String.valueOf(GameSettings.wordToGuess));

        if(GameSettings.chosenAnimal.equals("Tina")){
            image.setImage(new Image("Tina/tinadeath.gif"));
        }else {
            image.setImage(new Image("Franklin/franklindeath.gif"));
        }
    }

    /**
     * is executed when Play again Button is clicked in GUI
     * Switches back to Game Window
     * starts new game with the same animal and word list
     */
    public void tryAgain(ActionEvent actionEvent) throws IOException {
    //go back to game.fxml
        GameSettings.showWindow("/game.fxml", GameController.WIDTH, GameController.HEIGHT, GameController.WINDOWTITLE);
    }


    /**
     * is executed when Change animal/list Button is clicked in GUI
     * Switches back to Choose Fighters Window
     */
    public void animalChange(ActionEvent actionEvent) throws IOException {
        System.out.println("Back to Choose your fighter");
        GameSettings.showWindow("/choosefighters.fxml",
                ChooseFighters.WIDTH, ChooseFighters.HEIGHT, ChooseFighters.WINDOWTITLE);
    }

    /**
     * is executed when Exit Button is clicked in GUI
     * exits the program
     */
    public void endGame(ActionEvent actionEvent) {
        Platform.exit();
    }


}
