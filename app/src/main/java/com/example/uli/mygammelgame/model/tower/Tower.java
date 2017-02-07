package com.example.uli.mygammelgame.model.tower;

import com.example.uli.mygammelgame.model.inventory.Item;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds levels of building slots
 * Created by uli on 2/7/17.
 */

public class Tower {

    private int width;
    private int maxLevel;
    private Item[][] buildingSlots;
//    private int activeLevel;

    public Tower(int width, int maxHeight) {
        this.width = width;
        this.maxLevel = maxHeight;
        this.buildingSlots = new Item[maxHeight][width];
//        activeLevel = 0;
    }

    public int getActiveLevel() {
        Item slot;
        int level = 0;
        for (level = 0; level < maxLevel; level++) {
            for (int slotNumber=0; slotNumber < width; slotNumber++)
            {
                slot = buildingSlots[level][slotNumber];
                if (slot == null)
                    return level; // height starts at 1. Returning here returns the level
            }
        }
        return level;
    }

    public int getWidth() {
        return width;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Item[][] getBuildingSlots() {
        return buildingSlots;
    }
}
