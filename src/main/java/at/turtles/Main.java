package at.turtles;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Parent root = FXMLLoader.load(getClass().getResource("/start.fxml"));
        Scene scene = new Scene(root, 500, 500);
        GameSettings.stage = primaryStage;
        primaryStage.setTitle("Save the Animals");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

/*
Anleitung für Fenster:
- Jede Controller Klasse muss folgende Konstanten definieren:
    - int WIDTH, int HEIGHT, String WINDOWTITLE (Beispiel in GameController)
- zum Aufrufen anderer Fenster:
    - GameSettings.showWindow(filename, width, height, title); (Beispiel: in Finish.tryAgain() )

TODO für alle: Buttons in anderen Fenster mit eigenen Fenstern verknüpfen
    (Beispiel: ChooseList wird von ChooseFighters und GameController aufgerufen -> Ulas Aufgabe)

TODO Jess: Choose your Fighter - Auswahl in Variable speichern
TODO Ula: Done - Wortliste auswählen
TODO Lukas: Main Game Window
TODO Kayla: Finish Window

Quellen:
turtle names:
https://www.schildkroetenwelt.com/schildkroeten-arten/frog names:
http://www.lexikon-froesche.de/froesche_z.shtml

 */