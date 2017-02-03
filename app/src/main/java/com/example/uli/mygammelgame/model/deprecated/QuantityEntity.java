package com.example.uli.mygammelgame.model.deprecated;

import com.example.uli.mygammelgame.model.GameEntity;

/**
 * Created by uli on 2/3/17.
 */

public class QuantityEntity extends GameEntity {

    private int quantity;

    public QuantityEntity(String type, String displayName, int quantity) {
        super(type, displayName);
        this.quantity = quantity;
    }

    public QuantityEntity(String type, String displayName) {
        super(type, displayName);
        this.quantity = 0;
    }


    public QuantityEntity changeQuantity(int amount) {
        quantity = quantity + amount;
        return this;
    }

    public QuantityEntity setQuantity(int count) {
        this.quantity = count;
        return this;
    }
    public int getQuantity() {
        return quantity;
    }

    public QuantityEntity copy() {
        return new QuantityEntity(type,displayName,quantity);
    }

    @Override
    public String toString() { return "QuantityEntity id: "+gameId+", type:"+type+", displayname:"+displayName+", quantity:"+quantity; }

}
