package com.noela.gameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // 0 is for O, and 1 is for X
    int activePlayer = 0;

    // 2 is for unpleyed cells;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};


    int[][] winningPostions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    boolean gameisActive = true;


    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if (gameState[tappedCounter] == 2 && gameisActive) {

            gameState[tappedCounter] = activePlayer;


            if (activePlayer == 0) {
                counter.setTranslationY(-1000f);

                counter.setImageAlpha(0);

                counter.setImageResource(R.drawable.oimages);

                activePlayer = 1;
            } else {
                counter.setTranslationY(-1000f);

                counter.setImageResource(R.drawable.ximage);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1000f).setDuration(300);



            for (int[] winningPosition : winningPostions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    //someone  has won!


                    gameisActive = false;

                    String winner = "X";

                    if (gameState[winningPosition[0]] == 0) {

                        winner = "Ou";
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner + "  has won!");


                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);


                } else {
                    boolean gameIsOver = true;

                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsOver = false;
                    }

                    if (gameIsOver) {

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a draw!");


                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }

                }

            }
        }
    }

    public void playAgain(View view) {

        gameisActive = true;


        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;


        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 2;

        }


        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayoutId);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }


    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}