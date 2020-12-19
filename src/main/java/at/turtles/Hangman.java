package at.turtles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Hangman {

    final char[] WORDTOGUESS;
    HashSet<Character> alreadyGuessed = new HashSet<>(); //umbenennen in already used?
    final int MAXNUMBEROFGUESSES = 6;
    int wrongGuesses = 0;
    char[] wordInProgress;

    //default constructor
    public Hangman() {
        this("build/resources/main/testWords.txt");
    }

    public Hangman(String filename) {
        String word = getRandomWordFromFile(filename).toUpperCase();
        WORDTOGUESS = word.toCharArray();
        wordInProgress = word.replaceAll("[A-Z]", "_").toCharArray(); //turns letter to _
    }

    //returns random word
    //done todo Lukas bereitet vor (: add method with parameter - later
    public String getRandomWordFromFile(String filename) {
        ArrayList<String> wordList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = br.readLine()) != null) {
                wordList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList.get(new Random().nextInt(wordList.size()));
    }

    public char takeLetter() {

        for(int i = 0; i < MAXNUMBEROFGUESSES;){
            System.out.println("Enter letter: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine(); //scan entered letter

            if(line.charAt(0) >= 64){ // (ASCII) check if entry = letter (not character)
                checkIfAlreadyTyped(line.charAt(0));
                existsInTheWord(line.charAt(0));
                checkIfWon();
                i++;
                return line.toUpperCase().charAt(0);

            }else{ //if entry = character
                while(i < MAXNUMBEROFGUESSES){
                    System.out.println("Your entry was not a letter! ");
                    return 0;
                }
            }
        }
        return 0;
        //done todo Kayla: für später Eingabe auf Sonderzeichen überprüfen und Loop einbauen
    }

    public void printCurrentGameState() {
        System.out.println(wordInProgress);
        System.out.println("You have " + (MAXNUMBEROFGUESSES - wrongGuesses) + " guesses left");
        //done todo Jess: print all the fun stuff
    }

    public boolean checkIfAlreadyTyped (char letter) {
        return alreadyGuessed.contains(letter);
        //done todo Ula: implement
    }

    public boolean existsInTheWord (char letter) {
        for (char c : WORDTOGUESS) {
            if (c == letter) {
                return true;
            }
        }
        return false;
        //done todo Lukas: implement
    }

    public void updateProgress(char letter){
        alreadyGuessed.add(letter);
        String.valueOf(WORDTOGUESS).replaceAll(String.valueOf(letter), "");

        //done todo Kayla: implement
    }

    public boolean checkIfWon() {
        for (int i = 0; i < MAXNUMBEROFGUESSES; i++) {
            if (WORDTOGUESS == wordInProgress)

                return false;
        }
        return true;
    }
        //done - I hope todo Jess: implement


    public void reaction(boolean won){
        if (won) {
            System.out.println("Congratulations! It was hard brain work, but in the end you did it! The turtle lives!");
        } else {
            System.out.println("Oh no! You couldn't save the Frog from it's destiny. Maybe try a different strategy next time?");
        }
        //done todo Ula: implement
    }

    public void game() {
        // Willkommen zum Spiel!;
        boolean weWon = false;
        while (wrongGuesses < MAXNUMBEROFGUESSES && !weWon) {
            printCurrentGameState();
            char letter = takeLetter();
            boolean alreadyTyped = checkIfAlreadyTyped(letter);
            if (alreadyTyped) {
                System.out.println("You've ALREADY typed this.");
                continue;
               //done todo Jess: print Hinweis/Info
            }

            boolean correctGuess = existsInTheWord(letter);
            if(correctGuess){
                updateProgress(letter);
            }else{
                alreadyGuessed.add(letter); //add letter to already used letters
                wrongGuesses += 1;
                System.out.println("Wrong, try again!");
                //done todo Ula: Ausgabe + update counter wrongGuesses + update alreadyGuessed
                //Kann nur getestet werden, wenn existsInTheWord = false!
            }

            weWon = checkIfWon();

        }

        reaction(weWon);
    }

}
