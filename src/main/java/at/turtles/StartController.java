package at.turtles;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Controller for start.fxml
 */
public class StartController {

    public final static String WINDOWTITLE = "Start";


    /**
     * is executed when Start Button is clicked in GUI
     * switches to Choose Fighters Window
     */
    public void startButtonClicked(ActionEvent actionEvent) throws IOException {
        System.out.println("Clicked 'Start'");
        GameSettings.showWindow("/choosefighters.fxml", ChooseFightersController.WINDOWTITLE);
    }


    /**
     * is executed when Exit Button is clicked in GUI
     * exits the program
     */
    public void exitButtonClicked(ActionEvent actionEvent) {
        System.out.println("Clicked exit");
        Platform.exit();
    }
}
