package michaelpoole.diceapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int diceArray[]={R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4,
                            R.drawable.dice5, R.drawable.dice6};
    private GameController diceController;
    private int playerScore = 0;
    private int computerScore = 0;
    private TextView scoreBoard;
    private TextView currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Dice game baby", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        scoreBoard = (TextView) findViewById(R.id.scoreBoard);
        currentScore = (TextView) findViewById(R.id.currentScore);
        diceController = new GameController();
        getResources().getText(R.string.default_score).toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void rollDiceListener(View view) {
        rollDice();
    }

    public void rollDice(){
        ImageView diceImage = (ImageView) findViewById(R.id.diceImageView);
        int num = diceController.diceRoll();
        assert diceImage != null;
        diceImage.setImageResource(diceArray[num]);
        if(num == 0){
            holdDice();
        }
        else{
            currentScore.setText(diceController.scoreTrack());
        }
    }

    public void holdDiceListener(View view) {
        holdDice();
    }
    public void holdDice(){
        scoreBoard.setText(diceController.diceHold());
        currentScore.setText(diceController.scoreTrack());
        if(!diceController.isPlayerTurn()){
            computerTurn();
        }
    }

    public void resetDice(View view) {
        diceController.reset();
        scoreBoard.setText("Your Score: 0 Computer Score: 0");
        currentScore.setText(diceController.scoreTrack());
    }

    private void computerTurn(){
        Button roll = (Button) findViewById(R.id.button);
        Button hold = (Button) findViewById(R.id.button2);
        Button reset = (Button) findViewById(R.id.button3);

        roll.setEnabled(false);
        hold.setEnabled(false);
        //try to fix later
        reset.setEnabled(false);

        computerRolls();
    }

    private void computerRolls(){
        final Button roll = (Button) findViewById(R.id.button);
        final Button hold = (Button) findViewById(R.id.button2);
        final Button reset = (Button) findViewById(R.id.button3);
        final Handler timerHandler = new Handler();
        final Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                rollDice();
                if(diceController.getCurrentScore()<20 && !diceController.isPlayerTurn()){
                    timerHandler.postDelayed(this, 500);
                }
                else{
                    if(!diceController.isPlayerTurn()) { holdDice(); }
                    roll.setEnabled(true);
                    hold.setEnabled(true);
                    //try to fix later
                    reset.setEnabled(true);
                }
            }
        };
        timerRunnable.run();
    }
}
