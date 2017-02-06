package com.example.uli.mygammelgame.model.inventory;

import java.util.HashMap;

/**
 * Created by uli on 2/6/17.
 */

public class ItemMap extends HashMap<String, Item> {

    private ResourceMap resourceInput;
    private ResourceMap resourceOutput;

//    public void add(Item i) {
//        this.put(i.getGameId(), i);
//        updateResources();
//    }
//
//    public void remove(String itemId) {
//        if (this.containsKey(itemId)) {
//            this.remove(itemId);
//            updateResources();
//        }
//    }
    public void put (Item i) {
        super.put(i.getGameId(), i);
    }

    private void updateResources() {
        ResourceMap input = new ResourceMap();
        ResourceMap output = new ResourceMap();
        for (Item i: this.values()) {
            input.merge(i.getInputPerTurn());
            output.merge(i.getOutputPerTurn());
        }
        this.resourceInput = input;
        this.resourceOutput = output;
    }

    public ResourceMap getResourceInput() {
        updateResources();
        return resourceInput;
    }

    public ResourceMap getResourceOutput() {
        updateResources();
        return resourceOutput;
    }
}
