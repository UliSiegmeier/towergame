package com.example.uli.mygammelgame.model;

import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ItemMap;
import com.example.uli.mygammelgame.model.inventory.ResourceMap;

import java.util.List;

/**
 * - Contains all player resources and buildings.
 * - Allows for player actions (buy building etc)
 * - has nextTurn() to advance model
 *
 * Created by uli on 2/6/17.
 */

public class Player extends GameEntity {

    private ItemMap buildings;
    private ResourceMap resources;

    public Player() {
        super("PLAYER", "Unknown Player");
        this.buildings = new ItemMap();
        this.resources = new ResourceMap();
    }

    public void setStartingResources(ResourceMap resources) {
        this.resources = resources;
    }

    public void setStartingBuildings(ItemMap buildings) {
        this.buildings = buildings;
    }


    // ACTIONS

    public boolean buyBuilding(Item building) {
        if (canBuyBuilding(building)) {
            buildings.put(building.getGameId(), building);
            resources.merge(building.getPrice()); // Amounts in prices ResourceMaps need to be negative!
            return true;
        }
        else
            return false;
    }

    public boolean canBuyBuilding(Item building) {
        return resources.containsRequiredResources(building.getPrice());
    }

    // UPDATES

    public void nextTurn() {
        applyBuildingInputsOutputs();
    }

    private void applyBuildingInputsOutputs() {
        resources.merge(buildings.getResourceInput());
        resources.merge(buildings.getResourceOutput());
    }

    public boolean hasNegativeResources() {
        // STUB
        return false;
    }

    // GETTERS

    public ResourceMap getResources() {
        return resources;
    }

    public ItemMap getBuildings() {
        return buildings;
    }

    public String toString() {
        return "Player id:"+this.getGameId()+
                "\n\t name:"+this.getDisplayName()+
                "\n\t resources:"+this.getResources()+
                "\n\t buildings:"+this.getBuildings();
    }

}
