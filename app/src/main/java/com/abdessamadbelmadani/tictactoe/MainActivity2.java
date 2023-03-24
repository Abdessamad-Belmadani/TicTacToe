package com.abdessamadbelmadani.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class MainActivity2 extends AppCompatActivity {

    // Define the winning combinations
   private int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
    };

    // Check if a player has won
    private boolean[] checkForWinOrDraw(int player, List<Button> btns) {
        String symbol = (player == 1) ? "X" : "O";
        boolean hasEmptyCell = false;
        boolean[] result = new boolean[2]; // Array to hold the result values, initialized to false by default
        for (int[] combination : winningCombinations) {
            if (btns.get(combination[0]).getText().equals(symbol)
                    && btns.get(combination[1]).getText().equals(symbol)
                    && btns.get(combination[2]).getText().equals(symbol)) {
                result[0] = true; // Set the first element to true if a winning combination is found
                return result;
            }
        }
        for (Button btn : btns) {
            if (btn.getText().toString().isEmpty()) {
                hasEmptyCell = true; // Set hasEmptyCell to true if an empty cell is found
                break;
            }
        }
        if (!hasEmptyCell) {
            result[1] = true; // Set the second element to true if there are no empty cells left (i.e. a draw)
            return result;
        }
        return result; // Return the default result array if neither a winning combination nor a draw is found
    }


    private void resetBoard(List<Button> buttons,int[] playerTurn){
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               // Reset the game
               for (Button btn : buttons) {
                   btn.setText("");
                   btn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
               }
               playerTurn[0] = 1;
           }
       },1000);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        TextView player1_score=findViewById(R.id.player1_score);
        TextView player2_score=findViewById(R.id.player2_score);


        Button btn_00 = findViewById(R.id.button_00);
        Button btn_01 = findViewById(R.id.button_01);
        Button btn_02 = findViewById(R.id.button_02);
        Button btn_10 = findViewById(R.id.button_10);
        Button btn_11 = findViewById(R.id.button_11);
        Button btn_12 = findViewById(R.id.button_12);
        Button btn_20 = findViewById(R.id.button_20);
        Button btn_21 = findViewById(R.id.button_21);
        Button btn_22 = findViewById(R.id.button_22);


        List<Button> btns = new Vector<Button>(Arrays.asList(btn_00, btn_01, btn_02, btn_10, btn_11, btn_12, btn_20, btn_21, btn_22));
        final int[] PlayerTurn = {new Random().nextInt(2)};




        for (Button btn : btns) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (btn.getText().toString().isEmpty()) {
                        Drawable drawable;

                        if (PlayerTurn[0] == 1) {
                            drawable = VectorDrawableCompat.create(getResources(), R.drawable.x_solid, null);
                            btn.setText("X");
                        } else {
                            drawable = VectorDrawableCompat.create(getResources(), R.drawable.o_solid, null);
                            btn.setText("O");
                        }

                        btn.setCompoundDrawablesWithIntrinsicBounds(null, null, null, drawable);

                        // Call the checkForWinOrDraw function to check if the game has ended in a win or a draw


                     // Create a toast message for a win or a draw

                            if (checkForWinOrDraw(PlayerTurn[0], btns)[0]) {
                                Toast.makeText(getApplicationContext(), "Player " + PlayerTurn[0] + " wins!", Toast.LENGTH_LONG).show();
                                //updating score
                                if(PlayerTurn[0]==1){
                                    int s=Integer.parseInt(player1_score.getText().toString().substring(10));
                                    s++;
                                    player1_score.setText(player1_score.getText().toString().substring(0,10)+s);

                                }else{
                                    int s=Integer.parseInt(player2_score.getText().toString().substring(10));
                                    s++;
                                    player2_score.setText(player2_score.getText().toString().substring(0,10)+s);
                                }
                                resetBoard(btns,PlayerTurn);
                                } else if(checkForWinOrDraw(PlayerTurn[0], btns)[1]){
                                Toast.makeText(MainActivity2.this, "Draw", Toast.LENGTH_LONG).show();
                                resetBoard(btns,PlayerTurn);
                            }




                        PlayerTurn[0] = PlayerTurn[0] == 1 ? 2 : 1;
                    }
                }
            });
        }



        //refresh btn

        Button bnt_refresh=findViewById(R.id.reset);
        bnt_refresh.setOnClickListener(view -> {
            resetBoard(btns,PlayerTurn);
            player1_score.setText("Player 1: 0");
            player2_score.setText("Player 2: 0");
        });

    }
}