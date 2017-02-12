package com.example.uli.mygammelgame;

import android.app.Application;
import android.app.IntentService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridView;

import com.example.uli.mygammelgame.model.Game;
import com.example.uli.mygammelgame.model.Player;
import com.example.uli.mygammelgame.model.config.GameConfig;
import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ResourceMap;
import com.example.uli.mygammelgame.model.tower.BuildingGenerator;
import com.example.uli.mygammelgame.model.tower.Tower;


public class MainActivity extends AppCompatActivity {

    protected static Tower tower;
    protected static Player player;
    protected static Game game;

    private TextView turnLabel;
    private TextView scoreLabel;
    private GridView gridview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (game == null) {
            initGame();
        }

        turnLabel = (TextView) findViewById(R.id.counter_turns);
        scoreLabel = (TextView) findViewById(R.id.counter_score);

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new GridViewAdapter(this, game.getTower()));
        gridview.setNumColumns(this.tower.getWidth());

        updateLabels();
    }

    private void initGame() {
        // TODO THis is a dummy! - Load from somewhere

        // TOWER
        int towerWidth = 3;
        this.tower = new Tower(towerWidth);

        tower.buildBuilding(0, BuildingGenerator.getInstance().generateField());
        tower.buildBuilding(1, BuildingGenerator.getInstance().generateHouse());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateHouse());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(1, BuildingGenerator.getInstance().generateField());
        tower.buildBuilding(0, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateField());

        // GAME
        Player player = new Player(tower);
        this.game = new Game(player);
    }

    private void updateLabels() {
        turnLabel.setText       ( getString(R.string.label_turn_text,       game.getTurn()) );
        scoreLabel.setText      ( getString(R.string.label_score_text,      game.getScore()) );
    }

    public void onNextTurnClicked(View v) {
        if (game.getState() == Game.GameState.IN_PROGRESS) {
            game.nextTurn();
            updateLabels();
        }
        else
            Toast.makeText(getApplicationContext(), getString(R.string.toast_game_ended), Toast.LENGTH_SHORT).show();
    }

    public void onResetClicked(View v) {
        if (game.getState() == Game.GameState.IN_PROGRESS) {
            game.restart();
            updateLabels();
            Toast.makeText(getApplicationContext(), getString(R.string.toast_new_game), Toast.LENGTH_LONG).show();
        }
    }
}
