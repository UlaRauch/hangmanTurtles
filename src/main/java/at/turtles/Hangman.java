package at.turtles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Hangman {

    final char[] WORDTOGUESS;
    HashSet<Character> alreadyGuessed = new HashSet<>(); //umbenennen in already used?
    final int MAXNUMBEROFGUESSES = 6;
    int wrongGuesses = 0;
    char[] wordInProgress;
    public static String comments;
    public int positiveCounter = 0;

    //default constructor
    public Hangman() {
        this("C:\\Users\\urauc\\Documents\\Ausbildung\\FH\\Programmieren\\testList.txt");//wenn das auf git landet, hat Ula was falsch gemacht, sorry!
    }

    public Hangman(String filename) {
        String word = getRandomWordFromFile(filename).toUpperCase().replaceAll("Ä", "AE")
                .replaceAll("Ö", "OE").replaceAll("Ü", "UE").replaceAll("ß", "SS");
        WORDTOGUESS = word.toCharArray();
        wordInProgress = word.replaceAll("[A-Z]", "_").toCharArray(); //turns letter to _
    }

    //returns random word
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

        for (int i = 0; i < MAXNUMBEROFGUESSES; ) {
            System.out.println("Enter letter: ");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine(); //scan entered letter

            if (line.charAt(0) >= 64) { // (ASCII) check if entry = letter (not character)
                checkIfAlreadyTyped(line.charAt(0));
                existsInTheWord(line.charAt(0));
                checkIfWon();
                i++;
                return line.toUpperCase().charAt(0);

            } else { //if entry = character
                while (i < MAXNUMBEROFGUESSES) {
                    System.out.println("Your entry was not a letter! ");
                    return 0;
                }
            }
        }
        return 0;
    }

    public void printCurrentGameState() {
        System.out.println(wordInProgress);
        System.out.println("You have " + (MAXNUMBEROFGUESSES - wrongGuesses) + " guesses left");
    }

    public boolean checkIfAlreadyTyped(char letter) {
        return alreadyGuessed.contains(letter);
    }

    public boolean existsInTheWord(char letter) {
        for (char c : WORDTOGUESS) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    public void updateProgress(char letter) {
        for (int i = 0; i < WORDTOGUESS.length; i++) {
            if (WORDTOGUESS[i] == letter) {
                wordInProgress[i] = letter;
            }
        }
    }

    public boolean checkIfWon() {
        for (int i = 0; i < WORDTOGUESS.length; i++) {
            if (WORDTOGUESS[i] != wordInProgress[i])

                return false;
        }
        return true;
    }


    //message at end of game
    public void finalReaction(boolean won) {
        System.out.println(WORDTOGUESS);
        if (GameSettings.chosenAnimal != null) {
            if (won) {
                comments = "Congratulations! It was hard brain work, but in the end you did it! " + GameSettings.chosenAnimal + " lives!";
            } else {
                comments = "Oh no! You couldn't save " + GameSettings.chosenAnimal + " from this horrible destiny. Maybe try a different strategy next time?";
            }
            System.out.println(comments);
        } else { //when played in console
            if (won) {
                System.out.println("Congratulations! It was hard brain work, but in the end you did it!");
            } else {
                System.out.println("Oh no! That took too long. Maybe try a different strategy next time?");
            }
        }
    }

    //comment false guesses
    public void negativeComments(int wrongGuesses) {
        switch (wrongGuesses) {
            case 2 -> {
                comments = "This doesn't work either.";
            }
            case 3 -> {
                comments = "Are you sure this is the right strategy?";
            }
            case 4 -> {
                comments = "Now you should really start thinking of a solution!";
            }
            case 5 -> {
                comments = "Honestly, are you here to save animals or to kill them?";
            }
            default -> {
                comments = "Nope, try again!";
            }
        }
        System.out.println(comments);
    }

    //comment right guesses (only for GUI)
    //TODO: Algorithmus verbessern!
    public void positiveComments(int wrongGuesses) {
        if (wrongGuesses == 0 && positiveCounter == 0) {
            comments = "Genius or beginner's luck?";
        } else if (wrongGuesses > 0 && positiveCounter == 0) {
            comments = "That's better";
        } else if (wrongGuesses < 4 && positiveCounter == 2) {
            comments = "Look at you!";
        } else if (wrongGuesses > 3 && positiveCounter == 4) {
            comments = "There is hope!";
        } else if (wrongGuesses > 4 && (positiveCounter % 2 != 0)) {
            if (GameSettings.chosenAnimal.equals("Tina")) {
                comments = "You can do it! The god of turtles is with you now!";
            } else {
                comments = "You can do it! The god of frogs is with you now!";
            }
        } else if (wrongGuesses > 4 && (positiveCounter % 2 == 0)) {
            comments = GameSettings.chosenAnimal + " believes in you!";
        } else {
            comments = "Nice";
        }
        System.out.println(comments);
        positiveCounter++;
    }

    public void sameLetterComments(boolean existsInWord) {
        if (existsInWord) {
            comments = "It only works once, now try something else.";
        } else {
            comments = "You've ALREADY tried this.";
        }
        System.out.println(comments);
    }

    public void game() {
        // Willkommen zum Spiel!;
        boolean weWon = false;
        while (wrongGuesses < MAXNUMBEROFGUESSES && !weWon) {
            printCurrentGameState();
            char letter = takeLetter();
            boolean alreadyTyped = checkIfAlreadyTyped(letter);
            if (alreadyTyped) {
                sameLetterComments(existsInTheWord(letter));
                System.out.println("Nice!");
                continue;
            }

            boolean correctGuess = existsInTheWord(letter);
            if (correctGuess) {
                updateProgress(letter);
            } else {
                alreadyGuessed.add(letter); //add letter to already used letters
                wrongGuesses += 1;
                if (wrongGuesses < MAXNUMBEROFGUESSES) {
                    negativeComments(wrongGuesses);
                }
            }

            weWon = checkIfWon();

        }

        finalReaction(weWon);
    }

}
