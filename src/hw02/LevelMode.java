/*
 * LevelMode.java
 *
 * Version:
 *     1.0
 *
 * Revisions:
 *     v1.0 01-15-20 - Initial write-up 
 */
package hw02;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;


public enum LevelMode {
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private static final String CONFIG = "/resource/config.properties";
    private static final int DEFAULT_MAXIMUM_NUM_TRIES = 6;
    private static final String DEFAULT_DICTIONARY_FILE = "/resource/dict.txt";
    public static Properties properties;
    private String dictFile;
    private int maxNumTries;
    private final String description;
    
    private LevelMode(String description) {
        this.description = description;
        init();
    }
    
    private void init() {
        // Read and store properties file
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(CONFIG));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Set dict file
        dictFile = properties.getProperty(description + ".dict");
        if (dictFile == null) {
            dictFile = DEFAULT_DICTIONARY_FILE;
        }

        // Set max num of tries
        String maxNumTriesStr = properties.getProperty(description + ".tries");
        if (maxNumTriesStr == null) {
            maxNumTries = DEFAULT_MAXIMUM_NUM_TRIES;
        } else {
            maxNumTries = Integer.parseInt(maxNumTriesStr);
        }
    }

    public String getDictFile() {
        return dictFile;
    }

    public int getMaxNumTries() {
        return maxNumTries;
    }

    @Override
    public String toString() {
        return description;
    }
}