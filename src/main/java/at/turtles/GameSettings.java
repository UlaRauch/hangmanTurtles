package at.turtles;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSettings {
    //TODO: check if correctly set variables
    public static String listPathOfChoice;
    public static char[] wordToGuess;
    public static String chosenAnimal;


    public static Stage stage;

    public static void showWindow(String filename, int width, int height, String windowTitle) throws IOException {
        Parent root = FXMLLoader.load(GameSettings.class.getResource(filename));
        Scene scene = new Scene(root, width, height);
        GameSettings.stage.setScene(scene);
        GameSettings.stage.setTitle(windowTitle);
        GameSettings.stage.show();
    }
}
