package at.turtles;


import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{

    public static void main(String[] args) {
        launch(args);

//        Hangman testMan = new Hangman();
//        System.out.println(testMan.wordInProgress);
//
//        testMan.game();

        //Platform.exit();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        GameSettings.stage = primaryStage;
        GameSettings.showWindow("/start.fxml", StartController.WIDTH, StartController.HEIGHT, StartController.WINDOWTITLE);
    }

}
