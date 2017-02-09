package com.example.uli.mygammelgame.model.tower;

import com.example.uli.mygammelgame.model.inventory.Item;

/**
 * Created by uli on 2/9/17.
 */

public class BuildingGenerator {


    public BuildingGenerator() {

    }

    public static Item generateBuilding(Item templateBuilding) {
        Item building = new Item(   templateBuilding.getType(),
                                    templateBuilding.getDisplayName(),
                                    templateBuilding.getPrice(),
                                    templateBuilding.getInputPerTurn(),
                                    templateBuilding.getOutputPerTurn());
        return building;
    }

}
