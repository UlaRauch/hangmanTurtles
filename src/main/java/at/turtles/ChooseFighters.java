package at.turtles;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ChooseFighters {

    @FXML
    private Label chooseTurtleOrFrog;

    @FXML
    private Button frogButton;

    @FXML
    private Button turtleButton;

    public void chooseTurtle(ActionEvent actionEvent) {
        System.out.println("You chose Tina the Turtle");
        //Stage stage = (Stage) turtleButton.getScene().getWindow():
        //Parent root = FXMLLoader.load(getClass().GetResource(NAME OF NEW WINDOW)
    }

    public void chooseFrog(ActionEvent actionEvent) {
        System.out.println("You chose Franklin the frog");
        //Stage stage = (Stage) frogButton.getScene().getWindow():
        //Parent root = FXMLLoader.load(getClass().GetResource(NAME OF NEW WINDOW)

    }
}