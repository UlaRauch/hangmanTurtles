package at.turtles;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameSettings {
    //TODO: check if correctly set variables
    public static String listPathOfChoice;
    public static char[] wordToGuess;
    public static String chosenAnimal;//TODO: auf Tina/Franklin setzen, statt turtle/frog


    public static Stage stage;

    public static void showWindow(String filename, int width, int height, String windowTitle) throws IOException {
        Parent root = FXMLLoader.load(GameSettings.class.getResource(filename));
        Scene scene = new Scene(root, width, height);
        GameSettings.stage.setScene(scene);
        GameSettings.stage.setTitle(windowTitle);
        GameSettings.stage.show();
    }

    public static void updateSelectedButtons(Button selected, Button unselected) {
        selected.setStyle("-fx-underline: true");
        unselected.setStyle("-fx-underline: false");
    }

    public static void updateSelectedButtons(Button selected, Button unselected1, Button unselected2) {
        selected.setStyle("-fx-underline: true");
        unselected1.setStyle("-fx-underline: false");
        unselected2.setStyle("-fx-underline: false");
    }

    public static void updateSelectedButtons(Button selected, Button unselected1, Button unselected2, Button unselected3) {
        selected.setStyle("-fx-underline: true");
        unselected1.setStyle("-fx-underline: false");
        unselected2.setStyle("-fx-underline: false");
        unselected3.setStyle("-fx-underline: false");
    }
}
