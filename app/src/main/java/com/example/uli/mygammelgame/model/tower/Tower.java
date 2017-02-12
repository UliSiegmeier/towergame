package com.example.uli.mygammelgame.model.tower;

import android.util.Log;

import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ItemMap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Stack;

/**
 * Allows the building of buildings onto the current level.
 * When the current level is full (all slots are filled), the next level is unlocked.
 *
 * Created by uli on 2/7/17.
 */

public class Tower {

    private int width;
    private Stack<Level> levels;
    private ItemMap buildings;
    private ArrayList <Item> buildingsGridArray;

    public Tower(int width) {
        this.width = width;
        this.levels = new Stack <Level> ();
        this.buildings = new ItemMap();
        this.buildingsGridArray = new ArrayList<Item>();
        createNewEmptyLevel();
    }


    private void createNewEmptyLevel() {
        Level newLevel = new Level(width);
        this.levels.push(newLevel);
//        createEmptyLevelSlots();
    }

//    private void createEmptyLevelSlots() {
//        for (int slot=0; slot<width; slot++) {
//            addBuildingToGridArray(slot, null);
//        }
//    }

    // try to add building to current level. create new level if current level is full
    public boolean buildBuilding(int slotNumber, Item building) {
        if (getCurrentLevel().isFull())
            createNewEmptyLevel();

        if ((getCurrentLevel().get(slotNumber) != null) || buildings.containsKey(building.getGameId()))
            // TODO throw Exception
            return false;
        else {
            buildings.put(building);
//            addBuildingToGridArray(slotNumber, building);
            getCurrentLevel().set(slotNumber, building);
            return true;
        }
    }

//    private void addBuildingToGridArray(int slot, Item building) {
//        int insertPosition;
//        if (getNumberOfFinishedLevels() == 0)
//            insertPosition = slot;
//        else
//            insertPosition = (getNumberOfFinishedLevels() * width) + slot;
//        buildingsGridArray.add(slot, building);
//
//    }

    public Level getCurrentLevel() {
        return levels.peek();
    }

    public int getNumberOfFinishedLevels() {
        return levels.size()-1;
    }

    public Level getLevel(int height) {
        return levels.get(height);
    }

    // TODO redundant lists of buildings
    public ItemMap getBuildings() {
        return buildings;
    }

    public Item[] getBuildingsArray() {
        int numberOfSlots = width*levels.size();
        Item[] buildingArray = new Item[numberOfSlots];

        // Normal order
//        int slotIndex = 0;
//        for (Level level: levels) {
//            for (int slot=0; slot<width; slot++) {
//                Log.d("model_tower", slotIndex+"/"+(numberOfSlots-1)+"="+(Item)level.get(slot));
//                buildingArray[slotIndex] = (Item)level.get(slot);
//                slotIndex++;
//            }
//        }

        // Reverse order
        int slotIndex = 0;
        Level level;
        for (int i=levels.size()-1; i>=0; i--) {
            level = levels.get(i);
            for (int slot=0; slot<width; slot++) {
                Log.d("model_tower", slotIndex+"/"+(numberOfSlots-1)+"="+(Item)level.get(slot));
                buildingArray[slotIndex] = (Item)level.get(slot);
                slotIndex++;
            }
        }

        return buildingArray;
    }


    public int getWidth() {
        return width;
    }

    // necessary to expose to public? Depends on rendering.
    public Stack<Level> getLevels() {
        return levels;
    }
}
