package com.example.uli.mygammelgame.model;

/**
 * Created by uli on 2/2/17.
 */

public class Game {

    private GameState state;
    private enum GameState {IN_PROGRESS, FINISHED};

    private int turn;
    private int gold;
    private double population;
    private int populationRounded;

    // TODO: Store these in settings
    private int maxTurns;
    private float populationGrowthPerTurn;
    private float goldPerPopulation;


    public Game () {
        // TODO: init settings
        restart();
    }

    private void initSettings() {
        maxTurns = 100;
        populationGrowthPerTurn = 0.01f;
        goldPerPopulation = 1;
    }

    public void restart() {
        state = GameState.IN_PROGRESS;
        initSettings();
        turn = 1;
        gold = 0;
        population = 1;
        populationRounded = 1;
    }

    public void nextTurn() {
        if (state == GameState.IN_PROGRESS) {
            // update turns
            turn = turn + 1;

            // update population
            population = population + (population * populationGrowthPerTurn);
            populationRounded = (int) Math.floor(population);

            // update gold
            gold = (int) Math.floor(gold + (populationRounded * goldPerPopulation));

            if (turn >= maxTurns) state = GameState.FINISHED;
        }
    }

    // GETTERS
    public int getTurn() { return turn; }
    public double getPopulation() { return population; }
    public int getPopulationRounded() { return populationRounded; }
    public int getGold() { return gold; }

    @Override
    public String toString() {
        String s =  "GAME turn:"+turn+
                    " pop:"+population+
                    " popRounded:"+populationRounded+
                    " gold:"+gold;
        return s;
    }

}
