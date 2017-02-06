package com.example.uli.mygammelgame.model.inventory;

import com.example.uli.mygammelgame.model.GameEntity;
import com.example.uli.mygammelgame.model.GameEntityType;


public class Item extends GameEntity {

    private ResourceMap price;
    private ResourceMap inputPerTurn;
    private ResourceMap outputPerTurn;

    public Item(String type,
                String displayName,
                ResourceMap price,
                ResourceMap inputPerTurn,
                ResourceMap outputPerTurn) {
        super(type, displayName);
        this.price = price;
        this.inputPerTurn = inputPerTurn;
        this.outputPerTurn = outputPerTurn;
    }

    public ResourceMap getPrice() {
        return price;
    }

    public ResourceMap getInputPerTurn() {
        return inputPerTurn;
    }

    public ResourceMap getOutputPerTurn() {
        return outputPerTurn;
    }
}
