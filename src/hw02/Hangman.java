/*
 * Hangman.java
 *
 * Version:
 *     1.1
 *
 * Revisions:
 *     v1.0 01-27-20 - Initial write-up
 *     v1.1 01-28-20 - Javadoc commenting
 * 
 */
package hw02;

import java.util.Scanner;

/**
 * The main program for the pen and paper game, Hangman.
 * https://en.wikipedia.org/wiki/Hangman_(game)
 * 
 * The progeam is run on the command line as: $java hw2.Hangman mode
 * 
 * The program will choose a word to guess. The player must guess the word
 * within a certain number of tries
 * 
 * The user may prematurely end the game by entering the number 0 instead
 * of letters to quit the game.
 * 
 * @author Quinten Simet
 * @author Andy Lok
 */
public class Hangman {

    private GameBoard gameBoard;

    /**
     * The Class constructor which is called upon instantiation.
     *  Instantiates the GameBoard, passing in the difficulty mode
     *  chosen using the command line argument.
     * 
     * @see GameBoard
     * 
     * @param mode difficulty mode 
     *  (e - easy, m - medium, h - hard)
     */
    public Hangman(LevelMode mode) {
        gameBoard = new GameBoard(mode);
    }
    /**
     * Where the actual game is played. The function will remain
     *  in a continuous loop, printing the current status of the game
     *  and taking in a letter to guess, until the number of tries left
     *  reaches 0, the word is guessed, or the user chooses to quit.
     * 
     * @see GameBoard#gameOver() - checks if the game is over
     * @see GameBoard#toString() - prints the current status of the game
     * @see Hangman#enterLetter(Scanner) - takes in the users guessed letter
     */
    public void play() {
        Scanner guesser = new Scanner(System.in);

        while (!gameBoard.gameOver()) {

            System.out.print(gameBoard.toString());

            if (!enterLetter(guesser)) {
                break;
            }
        }
    }
    
    /** 
     * A helper function used by the class for entering
     *  a letter and sending it to the GameBoard class.
     * 
     * @see GameBoard#enterLetter(char) - checks if the guessed letter is correct
     * 
     * @param in - for reading user input.
     * @return boolean whether the character was valid or
     *  not, e.g. the user quit.
     */
    private boolean enterLetter(Scanner in) {
        System.out.print("Enter a Letter: ");

        String guess = in.next();
        char letter = guess.charAt(0);

        // Validate input is a single character
        if (guess.length() > 1){
            System.out.println("Invalid input!");
        }

        // Checks if end game command was sent 
        else if (letter == '0') { 
            return false;
        }

        // Validates input is a letter
        else if (!Character.isLetter(letter)) {
            System.out.println("Invalid input!");
        }
        
        // Sends letter to GameBoard Class
        else {
            gameBoard.enterLetter(Character.toUpperCase(letter));
        }
        return true;

    }
    
    /** 
     * The main routine that handles the command line argument,
     *  instantiates the game, and plays the game.
     * 
     * @param args The difficulty level mode.
     *  (e - easy, m - medium, h - hard)
     */
    public static void main(String[] args) {
        Hangman hangman;
        // hangman = new Hangman(LevelMode.EASY);
        // hangman.play();
        if (args.length == 1) {
            switch(args[0]) {
                case "e":
                    hangman = new Hangman(LevelMode.EASY);
                    hangman.play();
                case "m":
                    hangman = new Hangman(LevelMode.MEDIUM);
                    hangman.play();
                case "h":
                    hangman = new Hangman(LevelMode.HARD);
                    hangman.play();
                default: System.out.println("Usage: java Hangman mode (e – easy, m – medium, h - hard)");
            }
        } else {
            System.out.println("Usage: java Hangman mode (e – easy, m – medium, h - hard)");
        }
    }
}