package at.turtles;

import java.util.HashSet;
import java.util.Scanner;

public class Hangman {

    final char[] WORDTOGUESS;
    HashSet<Character> alreadyGuessed = new HashSet<>();
    final int MAXNUMBEROFGUESSES = 6;
    int wrongGuesses = 0;
    char[] wordInProgress;

    //default constructor
    public Hangman() {
        String word = getRandomWordFromFile().toUpperCase();
        WORDTOGUESS = word.toCharArray();
        wordInProgress = word.replaceAll("[A-Z]", "_").toCharArray();   //turns letter to _
    }

    //returns random word
    //todo Lukas bereitet vor (: add method with parameter - later
    public String getRandomWordFromFile() {
        return "test word";
    }

    public char takeLetter() {
        System.out.println("Enter letter: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        return line.toUpperCase().charAt(0);
        //todo Kayla: für später Eingabe auf Sonderzeichen überprüfen und Loop einbauen
    }

    public void printCurrentGameState() {
        System.out.println("Game state: ");
        //todo Jess: print all the fun stuff
    }

    public boolean checkIfAlreadyTyped (char letter) {
        return false;
        //todo Ula: implement
    }

    public boolean existsInTheWord (char letter) {
        return true;
        //todo Lukas: implement
    }

    public void updateProgress(char letter){
        //todo Kayla: implement
    }

    public boolean checkIfWon(){
        //todo Jess: implement
        return false;
    }

    public void reaction(boolean won){
        //todo Ula: implement
    }

    public void game() {
        // Willkommen zum Spiel!;
        boolean weWon = false;
        while (wrongGuesses < MAXNUMBEROFGUESSES && !weWon) {
            printCurrentGameState();
            char letter = takeLetter();
            boolean alreadyTyped = checkIfAlreadyTyped(letter);
            if (alreadyTyped) {
                continue;
                //todo Jess: print Hinweis/Info
            }

            boolean correctGuess = existsInTheWord(letter);
            if(correctGuess){
                updateProgress(letter);
            }else{
                //todo Ula: Ausgabe + update counter wrongGuesses
            }

            weWon = checkIfWon();

        }

        reaction(weWon);
    }

}
