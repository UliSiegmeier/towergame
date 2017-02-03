package com.example.uli.mygammelgame;

import android.util.Log;
import com.example.uli.mygammelgame.model.GameIdGenerator;
// import org.junit.Test;


public class ModelTestsMain {

    public static void main(String[] args) {
        gameIdGenerator_generation();
    }

    protected static void gameIdGenerator_generation() {
        GameIdGenerator generator = GameIdGenerator.getInstance();

        Log.d("HOUSE1", generator.generateId("HOUSE"));
        Log.d("HOUSE2", generator.generateId("HOUSE"));
        Log.d("HOUSE3", generator.generateId("HOUSE"));
    }

}
