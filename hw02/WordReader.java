/*
 * WordReader.java
 *
 * Version:
 *     1.0
 *
 * Revisions:
 *     v1.0 01-15-20 - Initial write-up 
 */
package hangman.hw02;

import java.io.RandomAccessFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

/**
 * Class desc...
 * 
 * @author Quinten Simet
 * @author Andy Loc
 */
public class WordReader {
    public WordReader(String filename) {
        String[] dictionary;
    }
    
    private static String pickHiddenWord() {
        String hiddenWord;

        return "";
    }

    private static void readFile(String filename) {
        try {
            RandomAccessFile gameWords = new RandomAccessFile(filename, "r");
            int randomIdx = (int)(Math.random()*gameWords.length())+1;
            gameWords.seek(randomIdx);
            gameWords.readLine();
            String data = gameWords.readLine();   

        } catch (FileNotFoundException ex) {
            System.out.println("Failure to find file");

        } catch (IOException ex) {
            System.out.println("Failure to find file");
        }

        return data;
    }

    public static void main(String[] args) {
        
    }
}