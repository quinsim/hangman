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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;


public enum LevelMode {
    EASY, MEDIUM, HARD;

    private static final String CONFIG = "/src/resource/config.properties";
    private static final String DEFAULT_DICTIONARY_FILE = "\\resource\\dict.txt";
    private static final int DEFAULT_MAXIMUM_NUM_TRIES = 6;
    private String dictFile;
    private int maxNumTries;
    private static Properties properties;
    
    private LevelMode() {
        init();
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
    
    public String getDictFile() {
        String cmd = toString() + ".dict";
        dictFile = properties.getProperty(cmd);
        if (dictFile == null) {
            return DEFAULT_DICTIONARY_FILE;    
        }
        else {
            return dictFile;
        }
    }

    public int getMaxNumTries() {
        String cmd = toString() + ".tries";
        String maxNumTriesStr = properties.getProperty(cmd);
        if (maxNumTriesStr == null) {
            return DEFAULT_MAXIMUM_NUM_TRIES;    
        }
        else {
            maxNumTries = Integer.parseInt(maxNumTriesStr);
            return maxNumTries;
        }
    }

    @Override 
    public String toString() { 
        switch (this) { 
            case EASY: return "easy"; 
            case MEDIUM: return "medium"; 
            case HARD: return "hard"; 
            default: throw new IllegalStateException(); 
        } 
    }
}