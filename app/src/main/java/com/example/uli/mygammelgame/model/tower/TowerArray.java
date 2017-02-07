package com.example.uli.mygammelgame.model.tower;

import com.example.uli.mygammelgame.model.inventory.Item;

/**
 * Holds levels of building slots
 * Created by uli on 2/7/17.
 */

public class TowerArray {

    private int width;
    private int maxLevel;
    private Item[][] allSlots;
//    private int activeLevel;

    public TowerArray(int width, int maxHeight) {
        this.width = width;
        this.maxLevel = maxHeight;
        this.allSlots = new Item[maxHeight][width];
//        activeLevel = 0;
    }

    public int getActiveLevel() {
        Item slot;
        int level = 0;
        for (level = 0; level < maxLevel; level++) {
            for (int slotNumber=0; slotNumber < width; slotNumber++)
            {
                slot = allSlots[level][slotNumber];
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

    public Item[][] getAllSlots() {
        return allSlots;
    }

    public Item[][] getCurrentSlots() {
        // This is all crap
        return null;
    }
}
