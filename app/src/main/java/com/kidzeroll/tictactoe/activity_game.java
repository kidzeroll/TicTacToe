package com.kidzeroll.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class activity_game extends AppCompatActivity implements View.OnClickListener {

    TextView playerOneScore, playerTwoScore, playerStatus;
    Button buttons [] = new Button[9];
    ImageButton resetGame;
    int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8}, //row
            {0,3,6},{1,4,7},{2,5,8}, //columns
            {0,4,8},{2,4,6} // cross
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playerOneScore = findViewById(R.id.playerOneScore);
        playerTwoScore = findViewById(R.id.playerTwoScore);
        playerStatus = findViewById(R.id.playerStatus);
        resetGame = findViewById(R.id.btnReset);

        for (int i=0; i<buttons.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;
    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        String buttonID = v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        if (activePlayer){
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#3473FB"));
            gameState[gameStatePointer] = 0;
        } else {
            ((Button)v).setText("O");
            ((Button)v).setTextColor(Color.parseColor("#F67033"));
            gameState[gameStatePointer] = 1;
        }
        rountCount++;

        if (checkWinner()){
            if (activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Pllayer 1 Win!", Toast.LENGTH_SHORT).show();
                playAgain();
            } else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Pllayer 2 Win!", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        } else if (rountCount == 9){
            playAgain();
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        } else {
            activePlayer = !activePlayer;
        }

        if (playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Player 1 is Winning!");
        } else if (playerTwoScoreCount > playerOneScoreCount){
            playerStatus.setText("Player 2 is Winning");
        }else {
            playerStatus.setText("");
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });
    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for (int [] winningPosition : winningPositions){
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        rountCount = 0;
        activePlayer =true;

        for (int i=0; i<buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }

    public void howToPlay(View view) {
        startActivity(new Intent(this, activity_how_to_play.class));
    }
}