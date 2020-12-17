package at.turtles;

import java.util.HashSet;
import java.util.Scanner;

public class Hangman {

    final char[] WORDTOGUESS;
    HashSet<Character> alreadyGuessed = new HashSet<>(); //umbenennen in already used?
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
        return alreadyGuessed.contains(letter);
        //todo Ula OK: implement
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
        if (won) {
            System.out.println("Congratulations! It was hard brain work, but in the end you did it! The turtle lives!");
        } else {
            System.out.println("Oh no! You couldn't save the Frog from it's destiny. Maybe try a different strategy next time?");
        }
        //todo Ula OK: implement
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
                alreadyGuessed.add(letter); //add letter to already used letters
                wrongGuesses += 1;
                System.out.println("Wrong, try again!");
                //todo Ula OK: Ausgabe + update counter wrongGuesses + update alreadyGuessed
                //Kann nur getestet werden, wenn existsInTheWord = false!
            }

            weWon = checkIfWon();

        }

        reaction(weWon);
    }

}
