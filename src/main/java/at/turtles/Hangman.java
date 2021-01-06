package at.turtles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Represents a single game of Hangman
 */
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


    /**
     * Constructor for Hangman
     * @param filename path to wordlist to choose random word from
     */
    public Hangman(String filename) {
        String word = getRandomWordFromFile(filename).toUpperCase().replaceAll("Ä", "AE")
                .replaceAll("Ö", "OE").replaceAll("Ü", "UE").replaceAll("ß", "SS");
        WORDTOGUESS = word.toCharArray();
        wordInProgress = word.replaceAll("[A-Z]", "_").toCharArray(); //turns letter to _
    }


    /**
     * returns random word from file
     * @param filename path to wordlist to choose random word from
     * @return         random word from file
     */
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

    /**
     * Scans System.in until a letter is entered
     * @return first entered letter as uppercase
     */
    public char takeLetter() {
        Scanner scanner = new Scanner(System.in);
        char letter;
        boolean enteredLetter = false;

        do {
            System.out.println("Enter letter: ");
            String line = scanner.nextLine();
            letter = line.toUpperCase().charAt(0);
            if (letter >= 'A' && letter <= 'Z') {
                enteredLetter = true;
            } else {
                System.out.println("Your entry was not a letter! ");
            }
        } while (!enteredLetter);

        return letter;
    }

    /**
     * Prints current progress of guessed letters and remaining wrong-guesses
     */
    public void printCurrentGameState() {
        System.out.println(wordInProgress);
        System.out.println("You have " + (MAXNUMBEROFGUESSES - wrongGuesses) + " guesses left");
    }


    /**
     * Checks if letter has already been guessed
     * @param letter letter to check
     * @return       if letter was already guessed
     */
    public boolean checkIfAlreadyTyped(char letter) {
        return alreadyGuessed.contains(letter);
    }


    /**
     * Checks if letter exists in word to guess
     * @param letter letter to check
     * @return       if word contains letter
     */
    public boolean existsInTheWord(char letter) {
        for (char c : WORDTOGUESS) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }


    /**
     * Updates WORDTOGUESS to show guessed letter in word
     * @param letter letter to show in WORDTOGUESS
     */
    public void updateProgress(char letter) {
        for (int i = 0; i < WORDTOGUESS.length; i++) {
            if (WORDTOGUESS[i] == letter) {
                wordInProgress[i] = letter;
            }
        }
    }


    /**
     * Checks if all letters have been guessed
     * @return if the game is won
     */
    public boolean checkIfWon() {
        for (int i = 0; i < WORDTOGUESS.length; i++) {
            if (WORDTOGUESS[i] != wordInProgress[i])

                return false;
        }
        return true;
    }


    /**
     * In GUI mode: sets comment variable depending on winning/losing
     * In Console mode: Outputs comment depending on winning/losing
     * @param won if game is won
     */
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


    /**
     * Outputs negative comment depending of current amount of wrong guesses
     */
    public void negativeComments() {
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


    /**
     * Outputs positive comment depending of current amount of wrong guesses
     */
    //TODO: Algorithmus verbessern!
    public void positiveComments() {
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


    /**
     * Outputs comment when letter has already been guessed
     * @param existsInWord if comment should be about a correctly guessed letter
     */
    public void sameLetterComments(boolean existsInWord) {
        if (existsInWord) {
            comments = "It only works once, now try something else.";
        } else {
            comments = "You've ALREADY tried this.";
        }
        System.out.println(comments);
    }

    /**
     * Hangman game logic for console version
     */
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
                    negativeComments();
                }
            }

            weWon = checkIfWon();

        }

        finalReaction(weWon);
    }

}
