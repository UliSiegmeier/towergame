package com.example.uli.mygammelgame.model.tower;

import java.util.ArrayList;

/**
 * Contains all building/item slots on a level of fixed width.
 *
 * Created by uli on 2/7/17.
 */

public class Level<Item> extends ArrayList {

    private int width;

    public Level(int width) {
        this.width = width;
        initWithEmptySlots();
    }

    private void initWithEmptySlots() {
        for (int i=0; i<width; i++) {
            this.add(null);
        }
    }


}
