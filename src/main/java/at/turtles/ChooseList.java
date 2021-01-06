package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import java.io.File;
import java.io.IOException;

/**
 * Controller for chooseList.fxml
 */
public class ChooseList {

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;
    public final static String WINDOWTITLE = "Choose List";
    public final String pathFrogNames = "build/resources/main/wordLists/frogNames.txt";
    public final String pathTurtleNames = "build/resources/main/wordLists/turtleNames.txt";
    /* Quellen:
    turtle names: https://www.schildkroetenwelt.com/schildkroeten-arten/
    frog names: http://www.lexikon-froesche.de/froesche_a.shtml
    */


    @FXML
    private Button turtleNamesButton;

    @FXML
    private Button frogNamesButton;

    @FXML
    private Button userListButton;


    /**
     * is executed when turtleNames button is clicked in GUI
     * sets chosen wordlist to the default turtleNames list
     * updates buttons to reflect choice
     */
    public void loadTurtleNames(ActionEvent actionEvent) {
        System.out.println("Clicked Turtle names");
        GameSettings.listPathOfChoice = pathTurtleNames;
        GameSettings.updateSelectedButtons(turtleNamesButton, frogNamesButton, userListButton);
        System.out.println("Selected file: " + GameSettings.listPathOfChoice);
    }


    /**
     * is executed when frogNames button is clicked in GUI
     * sets chosen wordlist to the default frogNames list
     * updates buttons to reflect choice
     */
    public void loadFrogNames(ActionEvent actionEvent) {
        System.out.println("Clicked Frog names");
        GameSettings.listPathOfChoice = pathFrogNames;
        GameSettings.updateSelectedButtons(frogNamesButton, turtleNamesButton, userListButton);
        System.out.println("Selected file: " + GameSettings.listPathOfChoice);
    }

    /**
     * is executed when customList button is clicked in GUI
     * shows message what the file should contain
     * sets chosen wordlist to path picked in file browser
     * updates buttons to reflect choice
     */
    public void loadUserList(ActionEvent actionEvent) {
        System.out.println("Clicked Use your own List");

        //Information Dialog with file requirements
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("File requirements");
        alert.setHeaderText("Choose your words wisely!");
        alert.setContentText("- .txt only\n- each word in a seperate line\n- no line without words\n- semicolons etc. won't be removed\n\nLike this:\nword\nsecond example word\nother word\nanother\n\n- umlaut will be transformed (-> ae, oe, ue)\n- case doesn't matter");
        alert.showAndWait();

        //offne Browser für Fileauswahl
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Where are your words?");

        //nur files mit Dateiendung ".txt" anzeigen
        fileChooser.getExtensionFilters()
                .add(new ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(GameSettings.stage);

        //use file path information to set variable in GameSettings
        //check if filepath is chosen to avoid NullpointerException
        //check if file is .txt
        if (file != null) {
            if (file.getAbsolutePath().endsWith(".txt")) {
                GameSettings.listPathOfChoice = file.getAbsolutePath();
                GameSettings.updateSelectedButtons(userListButton, turtleNamesButton, frogNamesButton);
                System.out.println("File chosen:" + GameSettings.listPathOfChoice);

            } else {
                System.out.println("Wrong filetype!");
                Alert wrongFileAlert = new Alert(AlertType.ERROR);
                wrongFileAlert.setTitle("Sorry, not this file!");
                wrongFileAlert.setHeaderText("Please choose a file that ends with .txt to continue!");
                wrongFileAlert.setContentText("If you don't have a list that fits the requirements, choose one from our fine selections of frog names and turtle names.");
                wrongFileAlert.showAndWait();
            }
        }
    }


    /**
     * switches to Game Window
     * only possible when a word list is selected
     * otherwise shows error message
     */
    public void goToNextScene(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked continue");

        //check if path variable for word list is already set
        if (GameSettings.listPathOfChoice != null) {
            System.out.println("continues");
            GameSettings.showWindow("/game.fxml", GameController.WIDTH, GameController.HEIGHT, GameController.WINDOWTITLE);
        } else {
            Alert noListAlert = new Alert(AlertType.ERROR);
            noListAlert.setResizable(true);
            noListAlert.setWidth(800);
            noListAlert.setHeight(400);
            noListAlert.setTitle("No game without words!");
            noListAlert.setHeaderText("Please choose a list first!");
            noListAlert.setContentText
                    ("We regret to inform you, that it is not possible at this moment to offer " +
                            "the standard quality game experience without any word to play with. \n\n" +
                            "We offer a choice of the most beautiful turtle names and frog names. " +
                            "If this doesn't satisfy your wishes, you can also upload your own list.\n\n" +
                            "Thank you for understanding."); //;-) normalerweise würde hier natürlich kein Roman stehen.
            noListAlert.showAndWait();
            System.out.println("No words defined, refuses to continue");
        }
    }


    /**
     * switches back to Choose Fighters Window
     */
    public void goBack(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked back");
        GameSettings.showWindow("/choosefighters.fxml",
                ChooseFighters.WIDTH, ChooseFighters.HEIGHT, ChooseFighters.WINDOWTITLE);
    }

}