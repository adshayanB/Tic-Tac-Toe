package ads.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow 1 = red

    int activePlayer = 0;

    boolean gameActive = true;

    //2 means unplayed
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.x);

                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).setDuration(200);

            for (int[] winningPosition : winningPositions) {

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
//Game over
                    String winner = "X";
                    gameActive = false;
                    if (gameState[winningPosition[0]] == 0) {

                        winner = "O";
                    }


                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    LinearLayout layout = findViewById(R.id.playAgain);
                    layout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameOver = true;
                    for (int counterState : gameState) {

                        if (counterState == 2)
                            gameOver = false;

                    }
                    if (gameOver) {
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It is a draw!");
                        LinearLayout layout = findViewById(R.id.playAgain);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }

    }

    public void playAgain(View view) {


        LinearLayout layout = findViewById(R.id.playAgain);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        gameActive = true;


        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

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
