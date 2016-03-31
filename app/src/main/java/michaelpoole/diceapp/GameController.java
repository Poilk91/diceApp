package michaelpoole.diceapp;

import java.util.Random;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


/**
 * Created by Michael on 3/29/2016.
 */
public class GameController {
    private Integer player1Score = 0;
    private Integer player2Score = 0;
    //I refuse to add 2 ints for current score
    private Integer currentScore = 0;
    private Integer numSides = 6;
    private Integer diceValue = 1;
    private boolean isPlayerTurn = true;


    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }

    public void setIsPlayerTurn(boolean isPlayerTurn) {
        this.isPlayerTurn = isPlayerTurn;
    }

    public Integer getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(Integer player1Score) {
        this.player1Score = player1Score;
    }

    public Integer getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(Integer player2Score) {
        this.player2Score = player2Score;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getNumSides() {
        return numSides;
    }

    public void setNumSides(Integer numSides) {
        this.numSides = numSides;
    }

    public Integer getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(Integer diceValue) {
        this.diceValue = diceValue;
    }
    //end getters and setters

    public int diceRoll(){
        Random rnd = new Random();
        int num = rnd.nextInt(numSides);
        setDiceValue(num + 1);
        if(getDiceValue() == 1){
            setCurrentScore(0);
        }
        else{
            setCurrentScore(getCurrentScore() + getDiceValue());
        }
        Log.d("dice","roll " + currentScore);
        return num;
        //update current score
        //check for roll 1
    }

    public String diceHold(){
        Log.d("dice","hold " + currentScore);
        if(isPlayerTurn()){
            setPlayer1Score(getPlayer1Score() + getCurrentScore());
            Log.d("dice", "player " + player1Score);
        }
        else{
            setPlayer2Score(getPlayer2Score() + getCurrentScore());
            Log.d("dice", "computer " + player2Score);
        }
        setCurrentScore(0);
        setIsPlayerTurn(!isPlayerTurn());
        return "Your score: " + getPlayer1Score() + " Computer score: " + getPlayer2Score();
    }

    public String scoreTrack(){
        if(isPlayerTurn()){
            return "Player current score : " + getCurrentScore();
        }
        else {
            return "Computer current score : " + getCurrentScore();
        }
    }

    public void reset(){
        setIsPlayerTurn(true);
        setPlayer2Score(0);
        setPlayer1Score(0);
        setCurrentScore(0);
    }
}
