package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.TouchEvent;

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

        if (GameSettings.chosenAnimal == "turtle"){
            image.setImage(new Image("Tina/tina.001.png"));
        }else{
            image.setImage(new Image("Franklin/franklin.001.png"));
        }

    }


    public void letterButtonClicked(ActionEvent actionEvent) throws IOException {
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

                    if(GameSettings.chosenAnimal == "turtle"){
                        if(game.wrongGuesses == 1){ image.setImage(new Image("Tina/tina.gif"));}
                        if(game.wrongGuesses == 2){ image.setImage(new Image("Tina/tinastep2.gif")); }
                        if(game.wrongGuesses == 3){ image.setImage(new Image("Tina/tinastep3.gif")); }
                        if(game.wrongGuesses == 4){ image.setImage(new Image("Tina/tinastep4.gif")); }
                        if(game.wrongGuesses == 5){ image.setImage(new Image("Tina/tinastep5.gif")); }
                    }else{
                        image.setImage(new Image("Franklin/franklin.gif"));
                        if(GameSettings.chosenAnimal == "frog" && game.wrongGuesses % 2 == 0){
                             image.setImage(new Image("Franklin/frogjump2.gif"));
                        }
                    }
            }
            pressedButton.setDisable(true);
            if (game.checkIfWon()) { //TODO: set GameSettings.won
                System.out.println("You won!");
                GameSettings.won = true;
                GameSettings.showWindow("/finish.fxml", Finish.WIDTH, Finish.HEIGHT, Finish.WINDOWTITLE);
            } else if (game.wrongGuesses == game.MAXNUMBEROFGUESSES) {
                System.out.println("You lost!");
                GameSettings.showWindow("/finish.fxml", Finish.WIDTH, Finish.HEIGHT, Finish.WINDOWTITLE);
            }
        }
        updateLabels();
    }

    public void backButtonClicked(ActionEvent actionEvent) throws IOException{
        //go back to chooseList.fxml
        GameSettings.showWindow("/chooseList.fxml", ChooseList.WIDTH, ChooseList.HEIGHT, ChooseList.WINDOWTITLE);
    }


}
