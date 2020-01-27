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
import java.util.Scanner;

import java.lang.Math;

import java.util.ArrayList;
import java.lang.IllegalStateException;

/**
 * Class desc...
 * 
 * @author Quinten Simet
 * @author Andy Loc
 */
public class WordReader {

    private static ArrayList<String> dict = new ArrayList<String>();

    public WordReader(String filename) {}
    
    private static String pickHiddenWord() {
        if (dict.size() == 0) {
            throw new IllegalStateException();
        }
        else {
            int randomIdx = (int)(Math.random()*dict.size());
            String hiddenWord = dict.get(randomIdx);
            return hiddenWord;    
        }
    }

    private static void readFile(String filename) {
        File gameWords = null;
        Scanner myReader = null;
        try {
            gameWords = new File(filename);
            myReader = new Scanner(gameWords);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                dict.add(data);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Failure to find file");
        }
        myReader.close();
    }

    public static void main(String[] args) {
        String local_dir = System.getProperty("user.dir");
        readFile(local_dir + "/src/resource/dict.txt");
        System.out.println(pickHiddenWord());

    }
}