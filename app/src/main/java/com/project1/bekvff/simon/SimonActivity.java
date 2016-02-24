package com.project1.bekvff.simon;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimonActivity extends AppCompatActivity implements MainFragment.MainFragmentListener{

    //Initialize buttons, model and views
    private static final String MAIN_FRAG_TAG = "MainFragment";
    private MainFragment mMainFragment;
    private SimonModel model;
    private Button mGreenButton;
    private Button mRedButton;
    private Button mYellowButton;
    private Button mBlueButton;
    private Button mStartButton;
    private TextView mCurrentScoreView;
    private TextView mHighScoreView;

    private int mCurrentScore = 0;
    private int mHighScore = 0;
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

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(MAIN_FRAG_TAG);
        mMainFragment = (MainFragment) fragmentManager.findFragmentByTag(MAIN_FRAG_TAG);
        if(mMainFragment == null) {
            mMainFragment = new MainFragment();
            fragmentManager.beginTransaction()
                    .add(mMainFragment, MAIN_FRAG_TAG)
                    .commit();
        }

        //Display the initial scores (both are zero at this point)
        updateScoreView();
    }

    //This function updates the score view to the TextView of both
    //current score and high score
    public void updateScoreView() {
        mCurrentScoreView.setText(String.valueOf(model.getCurrentScore()));
        mHighScoreView.setText(String.valueOf(model.getHighScore()));
    }

    //For the 4 color buttons, when they are clicked, it is first checked
    //to see if the background fragment is running, so as not to call the
    //checkButtonPress function if it is; otherwise, call the checkButtonPress functions
    //and call updateScoreView to keep view up to date
    public void greenButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            return;
        }
        model.checkButtonPress(R.id.green_button);
        updateScoreView();
    }

    public void redButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            return;
        }
        model.checkButtonPress(R.id.red_button);
        updateScoreView();
    }

    public void yellowButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            return;
        }
        model.checkButtonPress(R.id.yellow_button);
        updateScoreView();
    }

    public void blueButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            return;
        }
        model.checkButtonPress(R.id.blue_button);
        updateScoreView();
    }

    //This function will be called when the Start button is clicked
    //and will reset the Array list and current score
    public void startResetClicked(View v) {
        model.createNewList();
        updateScoreView();
    }

    private void animateColorChangeForButton(final Button button, int colorFrom, int colorTo) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(250);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });
    }

    @Override
    public void listenerMethod() {
        Log.d("TAG", "Listener method was called");
        for (int i = 0; i < 4; i++) {

        }
    }
}
