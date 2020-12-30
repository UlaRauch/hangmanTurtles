package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StartController {

    public final static int WIDTH = 700;
    public final static int HEIGHT = 500;
    public final static String WINDOWTITLE = "Start";
    @FXML
    private Label titleLabel;

    @FXML
    private Button exitButton;

    public void onExit(ActionEvent actionEvent) {
        System.out.println("Clicked exit");
        Platform.exit();
    }
}
