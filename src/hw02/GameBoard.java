/*
 * GameBoard.java
 *
 * Version:
 *     1.0
 *
 * Revisions:
 *     v1.0 01-15-20 - Initial write-up 
 */
package hw02;

import hw02.WordReader;
import hw02.LevelMode;

import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class GameBoard {    
    
    private WordReader wordReader;
    private String hiddenWord;
    private char[] maskWord;
    private Set<Character> missedLetters = new HashSet<Character>();
    private int maxNumTries;

    public GameBoard(LevelMode mode) {
        wordReader = new WordReader(mode.getDictFile());
        hiddenWord = wordReader.pickHiddenWord();

        maskWord = new char[hiddenWord.length()];
        Arrays.fill(maskWord, '*');

        maxNumTries = mode.getMaxNumTries();
        
    }

    public void enterLetter(char guessesLetter) {
        System.out.println(hiddenWord);
        if (hiddenWord.indexOf(guessesLetter) == -1) {
            missedLetters.add(guessesLetter);
        }
        else {
            for (int index = 0; index < hiddenWord.length(); index++) {
                if (hiddenWord.charAt(index) == guessesLetter) {
                    maskWord[index] = guessesLetter;
                }
            }
        }
    }

    public boolean gameOver() {
        int remainingTries = getRemainingTries();
        boolean result = true;
        for (char character: maskWord) {
            if (character == '*') {
                result = false;
            }
        }
        result |= remainingTries == 0;

        if (result && getRemainingTries() > 0) {
            System.out.println("You guessed the secret word!");
            return true;
        }
        else if (result && getRemainingTries() <= 0) {
            System.out.println("Game Over! The secret word was " + hiddenWord);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        int remainingTries = getRemainingTries();
        String maskWordStr = "";
        for (int index = 0; index < maskWord.length; index++) {
            maskWordStr+= maskWord[index];
        }
        String missedLettersStr = "";
        for (Iterator<Character> value = missedLetters.iterator(); value.hasNext();) {
            missedLettersStr+= value.next() + ", ";
        }

        String current_status;
        current_status = "\nWord: " + maskWordStr + "\n";
        current_status+= "Misses: " + missedLettersStr + "\n";
        current_status+= "Num. of Remaining Tries: " + remainingTries + "\n";
        current_status+= "Enter a leter: ";

        return current_status;
    }

    private int getRemainingTries() {
        return maxNumTries - missedLetters.size();
    }
}