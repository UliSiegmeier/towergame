package com.example.uli.mygammelgame.model.tower;

import com.example.uli.mygammelgame.model.inventory.Item;

/**
 * Created by uli on 2/7/17.
 */

public interface TowerInterface {

    public void addLevel(Level level);

    public Level getCurrentLevel();

    public int getNumberOfFinishedLevels();

    public Level getLevel(int height);

}
