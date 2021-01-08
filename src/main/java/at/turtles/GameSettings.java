package at.turtles;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * GameSettings stores static variables for use in different windows
 *              provides methods used in different windows
 */
public class GameSettings {
    //TODO: check if correctly set variables

    public static String listPathOfChoice;
    public static char[] wordToGuess;
    public static String chosenAnimal;
    public static Stage stage;
    public static boolean won;

    /**
     * Shows window from specified .fxml file
     * @param filename     path to the .fxml file
     * @param width        width of the window
     * @param height       height of the window
     * @param windowTitle  title of the window
     * @throws IOException when filename doesn't exist
     */
    public static void showWindow(String filename, int width, int height, String windowTitle) throws IOException {
        Parent root = FXMLLoader.load(GameSettings.class.getResource(filename));
        Scene scene = new Scene(root, width, height);
        GameSettings.stage.setScene(scene);
        GameSettings.stage.setTitle(windowTitle);
        GameSettings.stage.show();
    }


    /**
     * Marks one button as selected, all others as unselected
     * @param selected    button to mark as selected
     * @param unselected  optional buttons to mark as unselected
     */
    public static void updateSelectedButtons(Button selected, Button... unselected) {
        selected.setStyle("-fx-underline: true");
        for (Button b: unselected) {
            b.setStyle("-fx-underline: false");
        }
    }
}
