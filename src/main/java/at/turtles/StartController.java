package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Controller for start.fxml
 */
public class StartController {

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 500;
    public final static String WINDOWTITLE = "Start";

    /**
     * is executed when Start Button is clicked in GUI
     * switches to Choose Fighters Window
     */
    public void startButtonclicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked 'Start'");
        GameSettings.showWindow("/choosefighters.fxml", ChooseFighters.WIDTH, ChooseFighters.HEIGHT, ChooseFighters.WINDOWTITLE);
    }

    /**
     * is executed when Exit Button is clicked in GUI
     * exits the program
     */
    public void onExit(ActionEvent actionEvent) {
        System.out.println("Clicked exit");
        Platform.exit();
    }
}
