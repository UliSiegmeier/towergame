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

    private Game game;

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

        game = new Game();
        updateLabels();
    }

    private void updateLabels() {
        turnLabel.setText("Turns: "+game.getTurn());
        turnLabel.setText("Score: "+game.getScore());
        goldLabel.setText("Gold: "+game.getGold());
        populationLabel.setText("Population "+game.getPopulation());
    }

    public void onNextTurnClicked(View v) {
        if (game.getState() == Game.GameState.IN_PROGRESS) {
            game.nextTurn();
            updateLabels();
        }
        else
            Toast.makeText(getApplicationContext(), "The Game has ended!", Toast.LENGTH_LONG).show();
    }

    public void onResetClicked(View v) {
        if (game.getState() == Game.GameState.IN_PROGRESS) {
            game.restart();
            updateLabels();
            Toast.makeText(getApplicationContext(), "New Game!", Toast.LENGTH_LONG).show();
        }
    }
}
