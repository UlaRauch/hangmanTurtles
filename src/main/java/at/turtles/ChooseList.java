package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

import java.io.File;

public class ChooseList {


    @FXML
    public Text textMessage;

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
    public Button backButton;

    @FXML
    public Button continueButton;



    public void loadTurtleNames(ActionEvent actionEvent) {
        System.out.println("Clicked Turtle names");
        GameSettings.listPathOfChoice = "build/resources/main/turtleNames.txt";
        System.out.println(GameSettings.listPathOfChoice);
    }

    public void loadFrogNames(ActionEvent actionEvent) {
        System.out.println("Clicked Frog names");
        GameSettings.listPathOfChoice= "build/resources/main/frogNames.txt";
        System.out.println(GameSettings.listPathOfChoice);
    }

    public void loadThirdList(ActionEvent actionEvent) {
        System.out.println("Clicked Third list");
        //Hangman.setListPathOfChoice();
        //Ula TODO: create List, change button label, id & method name, set path
    }

    public void loadUserList(ActionEvent actionEvent) {
        System.out.println("Clicked Use your own List");

        //Information Dialog with file requirements
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("File requirements");
        alert.setHeaderText("Choose your words wisely!");
        alert.setContentText("- .txt only\n- each word in a seperate line\n- no line without words\n- semicolons etc. won't be removed\n\nLike this:\nword\nsecond example word\nother word\nanother\n\n- umlaut will be transformed (-> ae, oe, ue)\n- case doesn't matter");
        alert.showAndWait();

        //offne Browser für Fileauswahl
        Stage stage = (Stage) titleLabel.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Where are your words?");

        //nur files mit Dateiendung ".txt" anzeigen
        fileChooser.getExtensionFilters()
                    .add(new ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(stage);

        //use file path information to set variable in GameSettings
        //check if filepath is chosen to avoid NullpointerException
        //check if file is .txt
        if (file != null) {
            if (file.getAbsolutePath().endsWith(".txt")) {
                GameSettings.listPathOfChoice = file.getAbsolutePath();
                System.out.println("File chosen:" + file.getAbsolutePath());
            } else {
                System.out.println("Wrong filetype!");
                Alert wrongFileAlert = new Alert(AlertType.ERROR);
                alert.setTitle("Sorry, not this file!");
                alert.setHeaderText("Please choose a file that ends with .txt to continue!");
                alert.setContentText("If you don't have a list that fits the requirements, choose one from our fine selections of frog names and turtle names.");
                alert.showAndWait();
            }
        }
    }

    public void goToNextScene(ActionEvent actionEvent){
        System.out.println("Clicked continue");
        //check if path variable for word list is already set
        if (GameSettings.listPathOfChoice != null) {
            System.out.println("continues");
            //TODO: Lukas fix scene switching --> Game
        } else {
            //TODO: Info in Popup (Information dialog) ändern
            textMessage.setText("You are without words! Choose a List, then continue.");
            System.out.println("No words defined, refuses to continue");
        }
    }

    public void goBack(ActionEvent actionEvent) {
        System.out.println("Clicked back");
        //TODO Jess: fix scene switching -> chooseFighter
    }

}