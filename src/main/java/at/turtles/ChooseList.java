package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ChooseList {


    @FXML
    private Label titleLabel;

    @FXML
    private Button turtleNamesButton;

    @FXML
    private Button frogNamesButton;

    @FXML
    private Button thirdListButton;

    @FXML
    private Button userListButton;

    @FXML
    private Button backButton;


    public void loadTurtleNames(ActionEvent actionEvent) {
        System.out.println("Clicked Turtle names");
        Hangman.setListPathOfChoice("build/resources/main/turtleNames.txt");
        //ULA TODO: set path
    }

    public void loadFrogNames(ActionEvent actionEvent) {
        System.out.println("Clicked Frog names");
        Hangman.setListPathOfChoice("build/resources/main/frogNames.txt");
        //Ula TODO: set path
    }

    public void loadThirdList(ActionEvent actionEvent) {
        System.out.println("Clicked Third list");
        //Hangman.setListPathOfChoice();
        //Ula TODO: create List, change button label, id & method name, set path
    }

    public void loadUserList(ActionEvent actionEvent) {
        System.out.println("Clicked Use your own List");
        //Hangman.setListPathOfChoice();
        //Ula TODO: create File chooser
    }

    public void goBack(ActionEvent actionEvent) {
        System.out.println("Clicked back");
        //TODO: go back to previous scene
    }
}