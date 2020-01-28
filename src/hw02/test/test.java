package hw02.test;

import hw02.LevelMode;

public class test {
    public static void main(String[] args) {
        LevelMode game = LevelMode.EASY;
        System.out.println(game.getDictFile());
        System.out.println(game.getMaxNumTries());
    }
}