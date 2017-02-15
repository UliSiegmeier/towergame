package com.example.uli.mygammelgame;

import android.app.Application;
import android.app.IntentService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.uli.mygammelgame.model.Game;
import com.example.uli.mygammelgame.model.Player;
import com.example.uli.mygammelgame.model.config.GameConfig;
import com.example.uli.mygammelgame.model.inventory.Item;
import com.example.uli.mygammelgame.model.inventory.ResourceMap;
import com.example.uli.mygammelgame.model.inventory.ResourceType;
import com.example.uli.mygammelgame.model.tower.BuildingGenerator;
import com.example.uli.mygammelgame.model.tower.Tower;



public class MainActivity extends AppCompatActivity {

    protected static Tower tower;
    protected static Player player;
    protected static Game game;

    private TextView turnLabel;
    private TextView scoreLabel;
    private TextView foodLabel;
    private TextView populationLabel;
    private TextView stoneLabel;

    private GridView gridview;
    private GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (game == null) {
            initGame();
        }

        turnLabel = (TextView) findViewById(R.id.counter_turns);
        scoreLabel = (TextView) findViewById(R.id.counter_score);
        foodLabel = (TextView) findViewById(R.id.counter_food);
        populationLabel = (TextView) findViewById(R.id.counter_population);
        stoneLabel = (TextView) findViewById(R.id.counter_stone);

        gridview = (GridView) findViewById(R.id.gridview);
        this.gridViewAdapter = new GridViewAdapter(this, game.getTower());
        gridview.setAdapter(this.gridViewAdapter);
        gridview.setNumColumns(this.tower.getWidth());
        gridview.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String clickText;
                Item slot = tower.getBuildingsArray()[position];
                if (slot == null) {
                    clickText = "BUILDING SOMETHING";
                    emptySlotClicked(position);
                }
                else {
                    clickText = "This is a " + slot.getDisplayName();
                    Toast.makeText(getApplicationContext(), clickText, Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        tower.buildBuilding(0, BuildingGenerator.getInstance().generateField());
        tower.buildBuilding(1, BuildingGenerator.getInstance().generateField());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateField());

        tower.buildBuilding(0, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(1, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateMine());

        tower.buildBuilding(0, BuildingGenerator.getInstance().generateHouse());
        tower.buildBuilding(1, BuildingGenerator.getInstance().generateHouse());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateHouse());

        tower.buildBuilding(0, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(1, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateMine());

        tower.buildBuilding(0, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(1, BuildingGenerator.getInstance().generateMine());
        tower.buildBuilding(2, BuildingGenerator.getInstance().generateMine());

        tower.buildBuilding(1, BuildingGenerator.getInstance().generateHouse());

        // GAME
        this.player = new Player(tower);
        this.game = new Game(player);
    }

    private void updateLabels() {
        turnLabel.setText       ( getString(R.string.label_turn_text,       game.getTurn()) );
        scoreLabel.setText      ( getString(R.string.label_score_text,      game.getScore()) );

        foodLabel.setText       ( getString(R.string.label_food_text,       player.getResources().get(ResourceType.FOOD)) );
        populationLabel.setText ( getString(R.string.label_population_text, player.getResources().get(ResourceType.POPULATION)) );
        stoneLabel.setText      ( getString(R.string.label_stone_text,      player.getResources().get(ResourceType.STONE)) );
    }

    private void gridViewOnItemClickListener() {}

    public void onNextTurnClicked(View v) {
        if (game.getState() == Game.GameState.IN_PROGRESS) {
            game.nextTurn();
            updateLabels();
            Log.d("player", player.getResources().toString());
        }
        else
            Toast.makeText(getApplicationContext(), getString(R.string.toast_game_ended), Toast.LENGTH_SHORT).show();
    }

    public void onResetClicked(View v) {
//        if (game.getState() == Game.GameState.IN_PROGRESS) {
//            game.restart();
            initGame();
            updateUI();
            Toast.makeText(getApplicationContext(), getString(R.string.toast_new_game), Toast.LENGTH_LONG).show();
    }

    public void emptySlotClicked(int gridPosition) {

        int slot = gridPositionToSlotPosition(gridPosition);
//        Toast.makeText(getApplicationContext(), "slot index = "+slot, Toast.LENGTH_SHORT).show();

        // TODO Show building picker
        int randomNumber = (int) Math.floor(Math.random()*2);
        if (randomNumber < 0)
            tryToBuildBuilding(slot, BuildingGenerator.generateField());
        else if (randomNumber < 1)
            tryToBuildBuilding(slot, BuildingGenerator.generateHouse());
        else if (randomNumber < 2)
            tryToBuildBuilding(slot, BuildingGenerator.generateMine());

        updateUI();
    }

    private int gridPositionToSlotPosition(int gridPosition) {
        if (gridPosition == 0)
            return 0;
        else
            return (int) gridPosition % tower.getWidth();
    }

    private void updateUI() {
        // refresh grid view
        this.gridViewAdapter.notifyDataSetChanged();
//        this.gridview.invalidateViews();

        // update scores
        updateLabels();
    }

    private void tryToBuildBuilding(int gridPosition, Item building) {
        if (player.canBuyBuilding(building) == false) {
            showErrorMessage("Not enough resources to build "+building.getDisplayName());
        }
        else {
            player.buyBuilding(gridPositionToSlotPosition(gridPosition), building);
        }
    }

    private void showErrorMessage(String errorText) {
        Toast.makeText(getApplicationContext(), errorText, Toast.LENGTH_LONG).show();
    }


}
