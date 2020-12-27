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
        Parent root = FXMLLoader.load(getClass().getResource("/chooseList.fxml"));
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Save the Animals");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

/*
Anleitung für neues Fenster:
- in resources neue xyz.fxml erstellen
- zu Beginn bei fx:controller Namen ausbessern
- XyzController.java erstellen - wird angezeigt wenn man mit Maus drauffährt (ggf. in Package verschieben)
- in Main.java in start(Stage ....) die gewünschte Datei laden (Parent root), Größe/Title einstellen
- bei offenen Punkten todos einfügen

TODO Jess: Choose your Fighter - Auswahl in Variable speichern
TODO Ula: Done - Wortliste auswählen
TODO Lukas: Main Game Window
TODO Kayla: Finish Window

Quellen:
turtle names:
https://www.schildkroetenwelt.com/schildkroeten-arten/frog names:
http://www.lexikon-froesche.de/froesche_z.shtml

 */