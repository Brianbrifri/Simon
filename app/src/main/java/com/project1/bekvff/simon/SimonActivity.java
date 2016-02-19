package com.project1.bekvff.simon;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SimonActivity extends AppCompatActivity {

    //Initialize buttons and views
    private Button mGreenButton;
    private Button mRedButton;
    private Button mYellowButton;
    private Button mBlueButton;
    private Button mStartButton;
    private TextView mCurrentScoreView;
    private TextView mHighScoreView;

    private int currentScore = 0;
    private int highScore = 0;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);

        //Wire up buttons and views
        mGreenButton = (Button) findViewById(R.id.green_button);
        mRedButton = (Button) findViewById(R.id.red_button);
        mYellowButton = (Button) findViewById(R.id.yellow_button);
        mBlueButton = (Button) findViewById(R.id.blue_button);
        mStartButton = (Button) findViewById(R.id.start_button);

        mCurrentScoreView = (TextView) findViewById(R.id.current_score_view);
        mHighScoreView = (TextView) findViewById(R.id.high_score_view);
        //Display the initial scores (both are zero at this point)
        updateScoreView();
    }

    //This function updates the score view to the TextView of both
    //current score and high score
    private void updateScoreView() {
        mCurrentScoreView.setText(String.valueOf(currentScore));
        mHighScoreView.setText(String.valueOf(highScore));
    }

    //This function increases the current and high score appropriately
    private void increaseScore() {
        currentScore++;
        if(currentScore > highScore) {
            highScore = currentScore;
        }
    }

    //This function will be called when the Start button is clicked
    //and will reset the LinkedList and current score
    private void startReset() {
        currentScore = 0;
    }
}
