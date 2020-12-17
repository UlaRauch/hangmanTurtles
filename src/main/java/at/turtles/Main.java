package at.turtles;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        //launch(args);

    Hangman testMan = new Hangman();
        System.out.println(testMan.wordInProgress);

        Platform.exit();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
