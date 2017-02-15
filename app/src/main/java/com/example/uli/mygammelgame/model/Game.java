package com.example.uli.mygammelgame.model;

import com.example.uli.mygammelgame.model.config.GameConfig;
import com.example.uli.mygammelgame.model.tower.Tower;


public class Game {

    private GameState state;

    public enum GameState {IN_PROGRESS, FINISHED}

    private int turn;
    private int score;
    private Player player;

    private int maxTurns;


    public Game(Player player) {
        this.player = player;
        restart();
    }


    private void initSettings() {
        maxTurns = (Integer) GameConfig.getInstance().getConfig("GAME_MAX_TURNS");
        turn = 1;
        score = 0;
    }

    public void restart() {
        state = GameState.IN_PROGRESS;
        initSettings();
//        player.reset();
    }

    public void nextTurn() {
        // Turns can stay
        if (state == GameState.IN_PROGRESS) {
            // update turns
            turn = turn + 1;

            // update Player (who will update tower)
            player.nextTurn();

            // Last: Score
            score = calculateScore();

            if (turn >= maxTurns) state = GameState.FINISHED;
        }
    }

    public int calculateScore() {
        // TODO: better score calculation, based on levels, achievements, etc.
        return (player.getTower().getNumberOfFinishedLevels() * 100);
    }

    // GETTERS

    public GameState getState() {
        return state;
    }
    public int getScore() {
        return calculateScore();
    }
    public int getTurn() {
        return turn;
    }

    // TODO currently used in gridview
    public Tower getTower() {
        return player.getTower();
    }

    @Override
    public String toString() {
        String s =  "GAME turn: "+turn+
                    ", tower level: "+player.getTower().getCurrentLevel()+
                    ", tower buildings: "+player.getTower().getBuildings().size();
        return s;
    }

}
