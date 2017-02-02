package com.example.uli.mygammelgame;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uli.mygammelgame.model.Game;

import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {

    protected static Game game;

    private TextView turnLabel;
    private TextView scoreLabel;
    private TextView goldLabel;
    private TextView populationLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnLabel = (TextView) findViewById(R.id.counter_turns);
        scoreLabel = (TextView) findViewById(R.id.counter_score);
        goldLabel = (TextView) findViewById(R.id.counter_gold);
        populationLabel = (TextView) findViewById(R.id.counter_population);

        if (game == null) {
            game = new Game();
        }
        updateLabels();
    }

    private void updateLabels() {
        turnLabel.setText       ( getString(R.string.label_turn_text,       game.getTurn()) );
        scoreLabel.setText      ( getString(R.string.label_score_text,      game.getScore()) );
        goldLabel.setText       ( getString(R.string.label_gold_text,       game.getGold()) );
        populationLabel.setText ( getString(R.string.label_population_text, game.getPopulation()) );
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
