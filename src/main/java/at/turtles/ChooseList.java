package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;

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
    public Button continueButton;

    @FXML
    private Button backButton;

    private File file;


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
        //choose external wordlist from directory
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Do you have words?");
        fileChooser.getExtensionFilters()
                    .add(new ExtensionFilter("Text Files", "*.txt"));
        file = fileChooser.showOpenDialog(stage);
        //check if file is chosen, to avoid NullpointerException.
        if (file != null) {
            //set private path variable in Hangman class
            Hangman.setListPathOfChoice(file.getAbsolutePath());
            System.out.println("File chosen:" + file.getAbsolutePath());
        }
    }

    public void goToNextScene(ActionEvent actionEvent) throws Exception {
        System.out.println("Clicked continue");
        //check if path variable for word list is already set
        if (Hangman.getListPathOfChoice() != null) {
            System.out.println("continues");
            //Stage stage = (Stage) titleLabel.getScene().getWindow();
            //Parent root = FXMLLoader.load(getClass().getResource("/choosefighters.fxml"));
            //TODO: fix scene switching
        } else {
            System.out.println("No words defined, refuses to continue");
            //Ula TODO: implement Error message "you can't play without words"
        }
    }

    public void goBack(ActionEvent actionEvent) throws Exception {
        System.out.println("Clicked back");
        //Stage stage = (Stage) titleLabel.getScene().getWindow();
        //Parent root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        //TODO: fix scene switching
    }

}