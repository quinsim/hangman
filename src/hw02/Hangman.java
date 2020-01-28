/*
 * Hangman.java
 *
 * Version:
 *     1.0
 *
 * Revisions:
 *     v1.0 01-15-20 - Initial write-up 
 */
package hw02;
import hw02.LevelMode;

import java.util.Scanner;

import hw02.GameBoard;
/**
 * Class desc...
 * 
 * @author Quinten Simet
 * @author Andy Loc
 */
public class Hangman {

    private GameBoard gameBoard;

    public Hangman(LevelMode mode) {
        gameBoard = new GameBoard(mode);
    }

    public void play() {
        Scanner guesser = new Scanner(System.in);
        while (!gameBoard.gameOver()) {
            System.out.print(gameBoard.toString());
            if (!enterLetter(guesser)) {
                break;
            }
        }
    }

    private boolean enterLetter(Scanner in) {
        char guess = in.next().charAt(0);
        if (guess == '0') {
            return false;
        } else {
            gameBoard.enterLetter(Character.toUpperCase(guess));
            return true;
        }

    }
    public static void main(String[] args) {
        Hangman hangman;
        hangman = new Hangman(LevelMode.EASY);
        hangman.play();
        // if (args.length == 1) {
        //     switch(args[0]) {
        //         case "e":
        //             hangman = new Hangman(LevelMode.EASY);
        //             hangman.play();
        //         case "m":
        //             hangman = new Hangman(LevelMode.MEDIUM);
        //             hangman.play();
        //         case "h":
        //             hangman = new Hangman(LevelMode.HARD);
        //             hangman.play();
        //         default: System.out.println("Usage: java Hangman mode (e – easy, m – medium, h - hard)");
        //     }
        // } else {
        //     System.out.println("Usage: java Hangman mode (e – easy, m – medium, h - hard)");
        // }
    }
}