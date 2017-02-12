package com.example.uli.mygammelgame.model.tower;

import com.example.uli.mygammelgame.model.config.GameConfig;
import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ResourceMap;

/**
 * Created by uli on 2/9/17.
 */

public class BuildingGenerator {

    private static BuildingGenerator instance;

    private static Item template_house;
    private static Item template_field;
    private static Item template_mine;

    private BuildingGenerator() {
        initTemplates();
    }

    public static BuildingGenerator getInstance() {
        if (instance == null)
            instance = new BuildingGenerator();
        return instance;
    }

    private void initTemplates() {
        this.template_house = new Item(  "HOUSE",
                (String) GameConfig.getInstance().getConfig("HOUSE_NAME"),
                (ResourceMap) GameConfig.getInstance().getConfig("HOUSE_PRICE"),
                (ResourceMap) GameConfig.getInstance().getConfig("HOUSE_INPUT"),
                (ResourceMap) GameConfig.getInstance().getConfig("HOUSE_OUTPUT"));
        this.template_field = new Item(  "FIELD",
                (String) GameConfig.getInstance().getConfig("FIELD_NAME"),
                (ResourceMap) GameConfig.getInstance().getConfig("FIELD_PRICE"),
                (ResourceMap) GameConfig.getInstance().getConfig("FIELD_INPUT"),
                (ResourceMap) GameConfig.getInstance().getConfig("FIELD_OUTPUT"));
        this.template_mine = new Item(  "MINE",
                (String) GameConfig.getInstance().getConfig("MINE_NAME"),
                (ResourceMap) GameConfig.getInstance().getConfig("MINE_PRICE"),
                (ResourceMap) GameConfig.getInstance().getConfig("MINE_INPUT"),
                (ResourceMap) GameConfig.getInstance().getConfig("MINE_OUTPUT"));
    }


    public static Item generateBuilding(Item templateBuilding) {
        Item building = new Item(   templateBuilding.getType(),
                                    templateBuilding.getDisplayName(),
                                    templateBuilding.getPrice(),
                                    templateBuilding.getInputPerTurn(),
                                    templateBuilding.getOutputPerTurn());
        return building;
    }

    public static Item generateHouse() {
        return generateBuilding(template_house);
    }

    public static Item generateField() {
        return generateBuilding(template_field);
    }

    public static Item generateMine() {
        return generateBuilding(template_mine);
    }

}
