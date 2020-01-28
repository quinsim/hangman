/*
 * WordReader.java
 *
 * Version:
 *     1.0
 *
 * Revisions:
 *     v1.0 01-15-20 - Initial write-up 
 */
package hw02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import java.lang.Math;

import java.util.ArrayList;
import java.lang.IllegalStateException;

public class WordReader {

    private static ArrayList<String> dict = new ArrayList<String>();

    public WordReader(String filename) {
        readFile(filename);
    }
    
    public String pickHiddenWord() {
        if (dict.size() == 0) {
            throw new IllegalStateException();
        }
        else {
            int randomIdx = (int)(Math.random()*dict.size());
            String hiddenWord = dict.get(randomIdx);
            return hiddenWord;    
        }
    }

    private void readFile(String filename) {
        InputStream gameWords = null;
        Scanner myReader = null;
        gameWords = this.getClass().getResourceAsStream("/resource/dict.txt");
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
}