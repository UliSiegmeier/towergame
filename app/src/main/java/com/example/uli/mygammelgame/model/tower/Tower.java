package com.example.uli.mygammelgame.model.tower;

import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ItemMap;
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

    public Tower(int width) {
        this.width = width;
        this.levels = new Stack <Level> ();
        this.buildings = new ItemMap();
        createNewEmptyLevel();
    }


    private void createNewEmptyLevel() {
        Level newLevel = new Level(width);
        this.levels.push(newLevel);
    }

    // try to add building to current level. create new level if current level is full
    public void buildBuilding(int slotNumber, Item building) {
        if ((getCurrentLevel().get(slotNumber) != null) || buildings.containsKey(building.getGameId()))
            return;
        else {
            buildings.put(building);
            getCurrentLevel().set(slotNumber, building);
            if (getCurrentLevel().isFull())
                createNewEmptyLevel();
        }
    }

    public Level getCurrentLevel() {
        return levels.peek();
    }

    public int getNumberOfFinishedLevels() {
        return levels.size()-1;
    }

    public Level getLevel(int height) {
        return levels.get(height);
    }

    public ItemMap getBuildings() {
        return buildings;
    }

    public int getWidth() {
        return width;
    }

    // necessary to expose to public? Depends on rendering.
    public Stack<Level> getLevels() {
        return levels;
    }
}
