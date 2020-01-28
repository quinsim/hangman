/*
 * LevelMode.java
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

import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Properties;

/**
 * Enumeration dealing with which game mode the game is in,
 *  which dictionary file to use, and the maximum number of
 *  tries the user gets
 * 
 * @author Quinten Simet
 * @author Andy Lok
 */
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
    
    /**
     * The private class constructor that initializes the class
     *  and stores the class description
     * 
     * @see LevelMode#init()
     * 
     * @param description the description of the specific LevelMode
     *  which is used when parsing the properties file
     */
    private LevelMode(String description) {
        this.description = description;
        init();
    }
    
    /**
     * Initializes the class by loading and parsing the properties file
     *  to store the dictionary file path and the max number of tries
     */
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
    /**
     * Gets the dictionary file path which will be used by WordReader
     * 
     * @see WordReader
     * 
     * @return dictionary file
     */
    public String getDictFile() {
        return dictFile;
    }

    /**
     * Gets the max number of tries which will be used by GameMode
     * 
     * @see GameBoard
     * 
     * @return max number of tries
     */
    public int getMaxNumTries() {
        return maxNumTries;
    }

    /**
     * Overrides the toString() method to return the
     *  enumerations description which will be used
     * 
     * @return the class description
     */
    @Override
    public String toString() {
        return description;
    }
}