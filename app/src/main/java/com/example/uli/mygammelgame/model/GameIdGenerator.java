package com.example.uli.mygammelgame.model;

/**
 * Created by uli on 2/3/17.
 */

public class GameIdGenerator {

    private static GameIdGenerator instance;
    private static int idCounter;

    private GameIdGenerator() {
        idCounter = 0;
    }

    public static GameIdGenerator getInstance() {
        if (instance==null)
                instance = new GameIdGenerator();
        return instance;
    }

    public String generateId(String entityType) {
        String id = entityType + "_"+idCounter;
        idCounter++;
        return id;
    }


}
