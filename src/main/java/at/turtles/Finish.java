package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Finish {

    private static Hangman game;

    @FXML
    public Text status;
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


    private void endLabelOutput(){
        game.reaction(game.checkIfWon());
    }


//https://edencoding.com/javafx-scene/#change-close-clear
    public void tryAgain(ActionEvent actionEvent) throws IOException {
    //go back to game.fxml

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
       //Finish.stage.close();

    }


    public void animalChange(ActionEvent actionEvent) {
        //go back to choosefighters.fxml
    }


    public void endGame(ActionEvent actionEvent) {
        //go back to start.fxml
    }
}
