package com.project1.bekvff.simon;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SimonActivity extends AppCompatActivity implements MainFragment.MainFragmentListener{

    //Initialize buttons, model and views
    private static final String MAIN_FRAG_TAG = "MainFragment";
    private static final String KEY_INDEX = "index";
    private static final String KEY_CURRENT = "current";
    private static final String KEY_HIGH = "high";
    private MainFragment mMainFragment;
    private SimonModel model;
    private Button mGreenButton;
    private Button mRedButton;
    private Button mYellowButton;
    private Button mBlueButton;
    private Button mStartButton;
    private TextView mCurrentScoreView;
    private TextView mHighScoreView;

    private int mCurrentScore;
    private int mHighScore ;
    private int mCurrentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Set member variables to 0
        mHighScore = 0;
        mCurrentScore = 0;
        mCurrentIndex = 0;

        //This is where I set the member variables if the activity has been destroyed
        //Also set the views and updated the view
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mHighScore = savedInstanceState.getInt(KEY_HIGH, 0);
            mCurrentScore = savedInstanceState.getInt(KEY_CURRENT, 0);
            mCurrentScoreView = (TextView) findViewById(R.id.current_score_view);
            mHighScoreView = (TextView) findViewById(R.id.high_score_view);
            updateScoreView();
        }

        //Wire up buttons, model and views
        mGreenButton = (Button) findViewById(R.id.green_button);
        mRedButton = (Button) findViewById(R.id.red_button);
        mYellowButton = (Button) findViewById(R.id.yellow_button);
        mBlueButton = (Button) findViewById(R.id.blue_button);
        mStartButton = (Button) findViewById(R.id.start_button);

        //Set button background colors. Will be relegated to drawables shortly
   //     mGreenButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorGreen));
        mRedButton.setBackgroundColor(getResources().getColor(R.color.colorRed));
        mYellowButton.setBackgroundColor(getResources().getColor(R.color.colorYellow));
        mBlueButton.setBackgroundColor(getResources().getColor(R.color.colorBlue));

//        model = new SimonModel();

        mCurrentScoreView = (TextView) findViewById(R.id.current_score_view);
        mHighScoreView = (TextView) findViewById(R.id.high_score_view);

        //Creating the fragment manager and attaching the fragment (found by tag) to the
        //fragment manager. If the mainFragment is not already created, the mainFragment
        //variable is newed up, the transaction is started, and committed
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
//        mMainFragment.startSequence();
    }

    //This is where the member variables are stored to final strings
    //if the activity is destroyed
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putInt(KEY_CURRENT, mCurrentScore);
        outState.putInt(KEY_HIGH, mHighScore);
    }

    //This function updates the score view to the TextView of both
    //current score and high score
    public void updateScoreView() {
        mCurrentScoreView.setText(String.valueOf(mMainFragment.getCurrentScore()));
        mHighScoreView.setText(String.valueOf(mMainFragment.getHighScore()));
    }

    //For the 4 color buttons, when they are clicked, it is first checked
    //to see if the background fragment is running, so as not to call the
    //checkButtonPress function if it is; otherwise, call the checkButtonPress function
    public void greenButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            Log.d("TAG", "Button short circuited");
            return;
        }
        mMainFragment.checkButtonPress(R.id.green_button);
        updateScoreView();
    }

    public void redButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            Log.d("TAG", "Button short circuited");
            return;
        }
        mMainFragment.checkButtonPress(R.id.red_button);
        updateScoreView();
    }

    public void yellowButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            Log.d("TAG", "Button short circuited");
            return;
        }
        mMainFragment.checkButtonPress(R.id.yellow_button);
        updateScoreView();
    }

    public void blueButtonClicked(View v) {
        if(mMainFragment.fragmentIsRunning()) {
            Log.d("TAG", "Button short circuited");
            return;
        }
        mMainFragment.checkButtonPress(R.id.blue_button);
        updateScoreView();
    }

    //This function will be called when the Start button is clicked
    //and will reset the Array list and current score
    public void startResetClicked(View v) {
        mMainFragment.createNewList();
        updateScoreView();
    }

    private void animateColorChangeForButton(final Button button, int colorFrom, int colorTo) {
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(1000);
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                button.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });
        colorAnimation.start();
        colorAnimation.reverse();
    }

    @Override
    public void listenerMethod(int textResId) {
        Log.d("TAG", "Listener method was called");
        switch(textResId) {
            case R.id.green_button:
                animateColorChangeForButton(mGreenButton, getResources().getColor(R.color.colorGreen), getResources().getColor(R.color.colorGreenFlash));
                break;
            case R.id.red_button:
                animateColorChangeForButton(mRedButton, getResources().getColor(R.color.colorRed), getResources().getColor(R.color.colorRedFlash));
                break;
            case R.id.yellow_button:
                animateColorChangeForButton(mYellowButton, getResources().getColor(R.color.colorYellow), getResources().getColor(R.color.colorYellowFlash));
                break;
            case R.id.blue_button:
                animateColorChangeForButton(mBlueButton, getResources().getColor(R.color.colorBlue), getResources().getColor(R.color.colorBlueFlash));
                break;
            default:
                break;
        }
    }
}
