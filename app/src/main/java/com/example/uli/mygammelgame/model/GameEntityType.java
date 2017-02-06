package com.example.uli.mygammelgame.model;

/**
 * Created by uli on 2/6/17.
 */

public class GameEntityType {

    public static final String BUILDING_HOUSE = "BUILDING_HOUSE";
    public static final String BUILDING_MINE = "BUILDING_MINE";
    public static final String BUILDING_VILLAGE = "BUILDING_VILLAGE";
    public static final String BUILDING_LALALA = "BUILDING_LALALA";

    public final String type;

    public GameEntityType(String type) {
        this.type = type;
    }
}
