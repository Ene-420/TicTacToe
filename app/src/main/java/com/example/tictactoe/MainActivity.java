package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.gridlayout.widget.GridLayout;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int min = 0;
    int max = 1;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{0,3,6},{0,4,8},
                                {3,4,5},{1,4,7},{2,4,6},
                                {6,7,8},{2,5,8}};
    boolean gameActive;
    int count = 0;

    public void dropIn(View view) {
        int activePlayer = (int) Math.floor(Math.random() * (max - min + 1) + min);

        Log.i("info", "random int value= " + activePlayer);


        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        gameActive = true;
        if ((gameState[tappedCounter] == 2) &&(gameActive)) {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000);

            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.yellow);
                count+=1;
            } else {
                counter.setImageResource(R.drawable.red);
                count+=1;
            }

            counter.animate().translationYBy(1000).setDuration(500);

            for (int[] winningPositions : winningPositions) {
                if (gameState[winningPositions[0]] == gameState[winningPositions[1]] && gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]] != 2) {

                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!!!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
                else if((count== 9)&& (gameActive)){
                    gameActive = false;

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText("Draw!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view){
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i< gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i< gameState.length; i++){
            gameState[i] = 2;
        }
        boolean gameActive = true;
        count = 0;


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}