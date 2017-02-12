package com.example.uli.mygammelgame.model.config;

import com.example.uli.mygammelgame.model.inventory.ResourceMap;
import com.example.uli.mygammelgame.model.inventory.ResourceType;

import java.util.HashMap;


/**
 * Load and access game configs and rules from anywhere (singleton)
 *
 * Created by uli on 2/9/17.
 */

public class GameConfig {

    private static GameConfig instance;
    private HashMap<String, Object> configurations;

    private GameConfig(String configFileName) {
        configurations = new HashMap<String, Object>();
        loadConfigFromFile(configFileName);
    }

    public static GameConfig getInstance() {
        if (instance == null)
            instance = new GameConfig(null); // TODO: Pass a config filename
        return instance;
    }

    private void loadConfigFromFile(String filename) {
        // TODO: Actually load from file, overwrite or merge current Dictionary

        // stub
        configurations.put("GAME_MAX_TURNS", new Integer(20));

        configurations.put("TOWER_WIDTH",  new Integer(3));

        configurations.put("FIELD_NAME",   "RICE TERRACES");
        configurations.put("FIELD_PRICE",  new ResourceMap(ResourceType.POPULATION, -1));
        configurations.put("FIELD_INPUT",  new ResourceMap());
        configurations.put("FIELD_OUTPUT", new ResourceMap(ResourceType.FOOD, 2));

        configurations.put("HOUSE_NAME",   "HUTS");
        configurations.put("HOUSE_PRICE",  new ResourceMap(ResourceType.FOOD, -3));
        configurations.put("HOUSE_INPUT",  new ResourceMap(ResourceType.FOOD, -1));
        configurations.put("HOUSE_OUTPUT", new ResourceMap(ResourceType.POPULATION, 1));

        configurations.put("MINE_NAME",    "WORKSHOP");
        ResourceMap minePrice = new ResourceMap();
        minePrice.add(ResourceType.FOOD, 2);
        minePrice.add(ResourceType.POPULATION, 2);
        configurations.put("MINE_PRICE",   minePrice);
        configurations.put("MINE_INPUT",   new ResourceMap(ResourceType.FOOD, -1));
        configurations.put("MINE_OUTPUT",  new ResourceMap(ResourceType.STONE, 1));

    }


    public Object getConfig(String key) {
        return configurations.get(key);
    }

}
