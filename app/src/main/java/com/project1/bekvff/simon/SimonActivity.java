package com.project1.bekvff.simon;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimonActivity extends AppCompatActivity implements MainFragment.MainFragmentListener{

    //Initialize buttons, model and views
    private SimonModel model;
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

        //Wire up buttons, model and views
        mGreenButton = (Button) findViewById(R.id.green_button);
        mRedButton = (Button) findViewById(R.id.red_button);
        mYellowButton = (Button) findViewById(R.id.yellow_button);
        mBlueButton = (Button) findViewById(R.id.blue_button);
        mStartButton = (Button) findViewById(R.id.start_button);

        model = new SimonModel();

        mCurrentScoreView = (TextView) findViewById(R.id.current_score_view);
        mHighScoreView = (TextView) findViewById(R.id.high_score_view);
        //Display the initial scores (both are zero at this point)
        updateScoreView();
    }

    //This function updates the score view to the TextView of both
    //current score and high score
    private void updateScoreView() {
        mCurrentScoreView.setText(String.valueOf(model.getCurrentScore()));
        mHighScoreView.setText(String.valueOf(model.getHighScore()));
    }

    //This function will be called when the Start button is clicked
    //and will reset the LinkedList and current score
    public void startResetClicked(View v) {
        model.createNewList();

    }

    @Override
    public void listenerMethod() {
        Log.d("TAG", "Listener method was called");
        for (int i = 0; i < 4; i++) {

            final Button view;
            if (i == 0) {
                Animator animator = AnimatorInflater.loadAnimator(this, R.animator.change_green_color);
                animator.setStartDelay(i * 50);
                animator.setTarget(mGreenButton);
                view = mGreenButton;
            }
        }
    }
}
