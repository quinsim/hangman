/*
 * LevelMode.java
 *
 * Version:
 *     1.0
 *
 * Revisions:
 *     v1.0 01-15-20 - Initial write-up 
 */
package hangman.hw02;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.System.Logger.Level;
import java.util.Properties;


enum LevelMode {
    EASY, MEDIUM, HARD;

    private static final String CONFIG = "/resource/config.properties";
    private static final String DEFAULT_DICTIONARY_FILE = "\\resource\\dict.txt";
    private static final int DEFAULT_MAXIMUM_NUM_TRIES = 6;
    private String dictFile;
    private int maxNumTries;
    private static Properties properties;
    
    private LevelMode() {
    }
    
    public static String getDictFile() {
        return DEFAULT_DICTIONARY_FILE;
    }

    public static int getMaxNumTries() {
        return DEFAULT_MAXIMUM_NUM_TRIES;
    }
    
    private static void init() {
        String local_dir = System.getProperty("user.dir");
        try {
            properties = new Properties();
            InputStream file = new FileInputStream(new File(local_dir + CONFIG));
            properties.load(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        init();
        System.out.println(properties);
    }
}