package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for chooseFighters.fxml
 */
public class ChooseFightersController implements Initializable {

    public final static String WINDOWTITLE = "Choose your Fighter";


    @FXML
    public ImageView imageFrog;

    @FXML
    public ImageView imageTurtle;

    @FXML
    private Button frogButton;

    @FXML
    private Button turtleButton;


    /**
     * is executed when loading window
     * initializes default settings for the window
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageFrog.setImage(new Image("franklin/frog-choose-fighters.png"));
        imageTurtle.setImage(new Image("tina/turtle-choose-fighters.png"));
        if (GameSettings.chosenAnimal != null) {
            if (GameSettings.chosenAnimal.equals("tina")) {
                GameSettings.updateSelectedButtons(turtleButton, frogButton);
            } else if (GameSettings.chosenAnimal.equals("franklin")) {
                GameSettings.updateSelectedButtons(frogButton, turtleButton);
            }
        }
    }


    /**
     * is executed when turtle button is clicked in GUI
     * calls chooseTurtle
     */
    public void chooseTurtleButtonClicked(ActionEvent actionEvent) {
        chooseTurtle();
    }

    /**
     * automatically called when image is clicked
     * sets chosenAnimal to the turtles name
     * updates buttons to reflect choice
     */
    public void chooseTurtle() {
        System.out.println("You chose Tina the Turtle");
        GameSettings.chosenAnimal = "tina";
        GameSettings.updateSelectedButtons(turtleButton, frogButton);
    }


    /**
     * is executed when frog button is clicked in GUI
     * calls chooseFrog
     */
    public void chooseFrogButtonClicked(ActionEvent actionEvent) {
        chooseFrog();
    }

    /**
     * automatically called when image is clicked
     * sets chosenAnimal to the frogs name
     * updates buttons to reflect choice
     */
    public void chooseFrog() {
        System.out.println("You chose Franklin the frog");
        GameSettings.chosenAnimal = "franklin";
        GameSettings.updateSelectedButtons(frogButton, turtleButton);
    }

    /**
     * is executed when Continue Button is clicked in GUI
     * switches to Choose List Window
     * only possible when animal is selected
     * otherwise shows error message
     */
    public void goToNextScene(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked continue");
        if (GameSettings.chosenAnimal != null) {
            GameSettings.showWindow("/chooseList.fxml", ChooseListController.WINDOWTITLE);
        } else {
            Alert noAnimalChosen = new Alert(AlertType.ERROR);
            noAnimalChosen.setWidth(600);
            noAnimalChosen.setHeight(300);
            noAnimalChosen.setTitle("Choose Tina or Franklin");
            noAnimalChosen.setHeaderText("You have to choose a character");
            noAnimalChosen.setContentText
                    ("You have to choose an animal or you can't play. Save at least one animal today!");
            noAnimalChosen.showAndWait();
            System.out.println("You can't move forward without an animal");
        }
    }


    /**
     * is executed when Back Button is clicked in GUI
     * switches back to Start Window
     */
    public void goBack(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked back");
        GameSettings.showWindow("/start.fxml", StartController.WINDOWTITLE);
    }
}

