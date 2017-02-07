package com.example.uli.mygammelgame.model.tower;

import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.tower.Level;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by uli on 2/7/17.
 */

public class Tower implements TowerInterface {

    private int width;
    private Stack<Level> levels ;

    public Tower(int width) {
        this.width = width;
        this.levels = new Stack <Level> ();
        addLevel(new Level(width));
    }

    
    public void addLevel(Level level) {
        this.levels.push(level);
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
}
