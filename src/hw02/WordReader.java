/*
 * WordReader.java
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

import java.io.InputStream;

import java.util.Scanner;
import java.util.ArrayList;

import java.lang.Math;
import java.lang.IllegalStateException;

/**
 * The class that handles reading dictionary file and
 *  picking the word for the hangman game.
 * 
 * @author Quinten Simet
 * @author Andy Lok
 */
public class WordReader {

    private static ArrayList<String> dict = new ArrayList<String>();

    /**
     * The Class constructor which is called upon instantiation.
     *  Immediately reads the file passed to the class as a parameter.
     * 
     * @see WordReader#readFile(String) - reads the file
     * 
     * @param filename dictionary file name.
     *  Ex /resource/dict.txt
     */
    public WordReader(String filename) {
        readFile(filename);
    }
    
    /** 
     * Reads the file passsed as a parameter and populates
     * the ArrayList dict with a list of words.
     * 
     * @param filename dictionary file name.
     *  Ex /resource/dict.txt
     */
    private void readFile(String filename) {

        InputStream gameWords = null;
        Scanner myReader = null;
        gameWords = this.getClass().getResourceAsStream(filename); // Gets full filepath for dict file
        if (gameWords == null) {
            System.out.println("File " + filename + " not found");
        }
        else {
            myReader = new Scanner(gameWords);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dict.add(data);
            }
            myReader.close();
        }

    }
    /**
     * Picks a random word from a list of words for the user
     * to use in the hangman game.
     * 
     * @return String random word for the hangman game.
     * @throws IllegalStateException the file was not correctly read
     */
    public String pickHiddenWord() throws IllegalStateException {
        // Validates dictionary has been populated
        if (dict.size() == 0) {
            throw new IllegalStateException();
        }
        else {
            int randomIdx = (int)(Math.random()*dict.size()); // Gets random int [0, dict.size)
            String hiddenWord = dict.get(randomIdx);
            return hiddenWord;
        }
    }
}