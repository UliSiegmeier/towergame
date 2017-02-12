package com.example.uli.mygammelgame.model;

import com.example.uli.mygammelgame.model.config.GameConfig;
import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ItemMap;
import com.example.uli.mygammelgame.model.inventory.ResourceMap;
import com.example.uli.mygammelgame.model.tower.Tower;

import java.util.List;

/**
 * - Contains all player resources and tower.
 * - Allows for player actions (buy building etc)
 * - has nextTurn() to advance model
 *
 * Created by uli on 2/6/17.
 */

public class Player extends GameEntity {

    private Tower tower;
    private ResourceMap resources;

    public Player(Tower tower) {
        super("PLAYER", "Unknown Player");
        this.tower = tower;
//        this.tower = new Tower((Integer) GameConfig.getInstance().getConfig("TOWER_WIDTH"));
        this.resources = new ResourceMap();
    }

    public void setStartingResources(ResourceMap resources) {
        this.resources = resources;
    }

//    public void setStartingBuildings(ItemMap buildings) {
//        tower. = buildings;
//    }


    // ACTIONS

    public boolean buyBuilding(int slot, Item building) {
        if (canBuyBuilding(building)) {
            tower.buildBuilding(slot, building);
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
        resources.merge(tower.getBuildings().getResourceInput());
        resources.merge(tower.getBuildings().getResourceOutput());
    }

    public boolean hasNegativeResources() {
        // STUB
        // TODO handle negative resources
        return false;
    }

    public void reset() {

    }

    // GETTERS

    public ResourceMap getResources() {
        return resources;
    }

    public Tower getTower() {
        return tower;
    }

    public String toString() {
        return "Player id:"+this.getGameId()+
                "\n\t name:"+this.getDisplayName()+
                "\n\t resources:"+this.getResources()+
                "\n\t tower level:"+this.tower.getCurrentLevel()+
                "\n\t buildings:"+this.tower.getBuildings();
    }

}
