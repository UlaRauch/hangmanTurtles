package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ChooseFighters implements Initializable {

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;
    public final static String WINDOWTITLE = "Choose your Fighter";

    @FXML
    private Label chooseTurtleOrFrog;

    @FXML
    public ImageView imageFrog;

    @FXML
    public ImageView imageTurtle;

    @FXML
    private Button frogButton;

    @FXML
    private Button turtleButton;

    @FXML
    public Button backButton;

    @FXML
    public Button continueButton;


    //TODO: set GameSettings.chosenAnimal
    public void chooseTurtle(ActionEvent actionEvent) {
        System.out.println("You chose Tina the Turtle");
        GameSettings.chosenAnimal = "Tina";
        GameSettings.updateSelectedButtons(turtleButton, frogButton);
    }

    public void chooseFrog(ActionEvent actionEvent) {
        System.out.println("You chose Franklin the frog");
        GameSettings.chosenAnimal = "Franklin";
        GameSettings.updateSelectedButtons(frogButton, turtleButton);
    }


    public void goToNextScene(ActionEvent actionEvent) throws IOException {
        if (GameSettings.chosenAnimal != null) {
            System.out.println("clicked continue");
            GameSettings.showWindow("/chooseList.fxml",
                    ChooseList.WIDTH, ChooseList.HEIGHT, ChooseList.WINDOWTITLE);

        } else {                 //alert window
            Alert NoAnimalChosen = new Alert(AlertType.ERROR);
            NoAnimalChosen.setResizable(true);
            NoAnimalChosen.setWidth(600);
            NoAnimalChosen.setHeight(300);
            NoAnimalChosen.setTitle("Choose Tina or Franklin");
            NoAnimalChosen.setHeaderText("You have to choose a character");
            NoAnimalChosen.setContentText
                    ("You have to choose an animal or you can't play. Save at least on animal today!"); //;-) normalerweise würde hier natürlich kein Roman stehen.
            NoAnimalChosen.showAndWait();
            System.out.println("You can't move forward without an animal");
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked back");
        GameSettings.showWindow("/start.fxml",
                StartController.WIDTH, StartController.HEIGHT, StartController.WINDOWTITLE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageFrog.setImage(new Image("images/frog-choose-fighters.png"));
        imageTurtle.setImage(new Image("images/turtle-choose-fighters.png"));

    }
}

