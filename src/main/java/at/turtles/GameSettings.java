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

    public static String listPathOfChoice;
    public static char[] wordToGuess;
    public static String chosenAnimal;
    public static Stage stage;
    public static boolean won; //used in Finish when game object doesn't exist anymore

    /**
     * Shows window from specified .fxml file
     * @param filename     path to the .fxml file
     * @param windowTitle  title of the window
     * @throws IOException when filename doesn't exist
     */
    public static void showWindow(String filename, String windowTitle) throws IOException {
        Parent root = FXMLLoader.load(GameSettings.class.getResource(filename));
        Scene scene = new Scene(root, 1000, 500);
        GameSettings.stage.setScene(scene);
        GameSettings.stage.setTitle(windowTitle);
        GameSettings.stage.setResizable(false);
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
