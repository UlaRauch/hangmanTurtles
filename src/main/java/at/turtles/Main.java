package at.turtles;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


public class Main{

    public static void main(String[] args) {
        //launch(args);

        Hangman testMan = new Hangman();
        System.out.println(testMan.wordInProgress);

        testMan.game();

       // Platform.exit();
    }


}
