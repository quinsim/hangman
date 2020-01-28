/*
 * GameBoard.java
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

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The class that handles the actual gameplay for hangman.
 *  Determines when the game is over, whether the users entered
 *  letter is correct, and the current status of the game.
 * 
 * @author Quinten Simet
 * @author Andy Lok
 */
public class GameBoard {    
    
    private WordReader wordReader;
    private String hiddenWord;
    private char[] maskWord;
    private Set<Character> missedLetters = new HashSet<Character>();
    private int maxNumTries;

    /**
     * The Class constructor which is called upon instantiation
     *  Instantiates the WordReader class and picks the hidden word
     *  
     * @see WordReader#pickHiddenWord()
     * @see LevelMode#getDictFile()
     * @see LevelMode#getMaxNumTries()
     * 
     * @param mode
     */
    public GameBoard(LevelMode mode) {
        wordReader = new WordReader(mode.getDictFile());
        hiddenWord = wordReader.pickHiddenWord();

        maskWord = new char[hiddenWord.length()];
        Arrays.fill(maskWord, '*');

        maxNumTries = mode.getMaxNumTries();
        
    }

    /** 
     * Gets the users number of remaining tries
     * 
     * @return int remaining tries
     */
    private int getRemainingTries() {
        return maxNumTries - missedLetters.size();
    }
    
    /** 
     * Checks if the passed letter is located in the hangman word.
     *  Adds the letter to the list of missed letters if the guess is incorrect
     * 
     * @param guessesLetter guessed letter
     */
    public void enterLetter(char guessesLetter) {
        // Checks if guessed letter is located in hidden word
        if (hiddenWord.indexOf(guessesLetter) == -1) {
            missedLetters.add(guessesLetter);
        }
        else {
            //Loops entire hidden word to find all occurrences of guessedLetter
            for (int index = 0; index < hiddenWord.length(); index++) {
                if (hiddenWord.charAt(index) == guessesLetter) {
                    maskWord[index] = guessesLetter;
                }
            }
        }
    }
    
    /** 
     * The game is over when the number of remaining tries
     *  reaches 0 or the word has been guessed
     * 
     * @see GameBoard#getRemainingTries()
     * 
     * @return boolean True if the game has ended, false otherwise
     */
    public boolean gameOver() {
        int remainingTries = getRemainingTries();
        boolean result = true;
        // Loops through maskWord to check if the entire word has been guessed
        for (char character: maskWord) {
            if (character == '*') {
                result = false;
            }
        }
        // Determines if user has run out of remaining tries
        result |= remainingTries == 0;

        if (result && getRemainingTries() > 0) {
            System.out.println("You guessed the secret word!");
        }
        else if (result && getRemainingTries() <= 0) {
            System.out.println("Game Over! The secret word was " + hiddenWord);
        }
        
        return result;
    }
    
    /** 
     * Overrides the classes toString() function to return the
     *  current status of the game as a string.
     * 
     * Ex. 
     *      Word: *A****
     *      Misses: X, Y, Z
     *      Num. of Remaining Tries: 5
     *      Enter a letter:
     * 
     * @see GameBoard#getRemainingTries()
     * 
     * @return String the current status of the game
     */
    @Override
    public String toString() {
        int remainingTries = getRemainingTries();

        // Creates a friendly string of the Character Array maskWord
        String maskWordStr = "";
        for (int index = 0; index < maskWord.length; index++) {
            maskWordStr+= maskWord[index];
        }

        // Creates a friendly string of the Character Set missedLetters
        String missedLettersStr = "";
        for (Iterator<Character> value = missedLetters.iterator(); value.hasNext();) {
            missedLettersStr+= value.next() + ", ";
        }

        String current_status;
        current_status = "\nWord: " + maskWordStr + "\n";
        current_status+= "Misses: " + missedLettersStr + "\n";
        current_status+= "Num. of Remaining Tries: " + remainingTries + "\n";

        return current_status;
    }
}