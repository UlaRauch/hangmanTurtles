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
    private Button list1Button;

    @FXML
    private Button list2Button;


    public void list1chosen(ActionEvent actionEvent) {
        System.out.println("Clicked List1");
    }

    public void list2chosen(ActionEvent actionEvent) {
        System.out.println("Clicked List2");
    }
}