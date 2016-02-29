package com.project1.bekvff.simon;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
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
    private Button mGreenButton;
    private Button mRedButton;
    private Button mYellowButton;
    private Button mBlueButton;
    private Button mStartButton;
    private TextView mCurrentScoreView;
    private TextView mHighScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simon);
//        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        //Wire up buttons and views
        mGreenButton = (Button) findViewById(R.id.green_button);
        mRedButton = (Button) findViewById(R.id.red_button);
        mYellowButton = (Button) findViewById(R.id.yellow_button);
        mBlueButton = (Button) findViewById(R.id.blue_button);
        mStartButton = (Button) findViewById(R.id.start_button);
        mCurrentScoreView = (TextView) findViewById(R.id.current_score_view);
        mHighScoreView = (TextView) findViewById(R.id.high_score_view);

        //Disable buttons on launch of game
        disableButtons();


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
        if(!enabledCheck()) {
            Log.d("TAG", "Button short circuited");
            return;
        }
        mMainFragment.checkButtonPress(R.id.green_button);
        updateScoreView();
    }

    public void redButtonClicked(View v) {
        if(!enabledCheck()) {
            Log.d("TAG", "Button short circuited");
            return;
        }
        mMainFragment.checkButtonPress(R.id.red_button);
        updateScoreView();
    }

    public void yellowButtonClicked(View v) {
        if(!enabledCheck()) {
            Log.d("TAG", "Button short circuited");
            return;
        }
        mMainFragment.checkButtonPress(R.id.yellow_button);
        updateScoreView();
    }

    public void blueButtonClicked(View v) {
        if(!enabledCheck()) {
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
        enableButtons();
        updateScoreView();
        mMainFragment.startSequence();
    }

    private boolean enabledCheck() {
        if(!mGreenButton.isEnabled()) {
            return false;
        }
        else
            return true;
    }

    public void enableButtons() {
        mGreenButton.setEnabled(true);
        mGreenButton.setClickable(true);
        mRedButton.setEnabled(true);
        mRedButton.setClickable(true);
        mYellowButton.setEnabled(true);
        mYellowButton.setClickable(true);
        mBlueButton.setEnabled(true);
        mBlueButton.setClickable(true);
    }

    public void disableButtons() {
        mGreenButton.setEnabled(false);
        mGreenButton.setClickable(false);
        mRedButton.setEnabled(false);
        mRedButton.setClickable(false);
        mYellowButton.setEnabled(false);
        mYellowButton.setClickable(false);
        mBlueButton.setEnabled(false);
        mBlueButton.setClickable(false);
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
        colorAnimation.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void listenerMethod(int textResId) {
        Log.d("TAG", "Listener method was called");
        switch(textResId) {
            case R.id.green_button:
                animateColorChangeForButton(mGreenButton, ContextCompat.getColor(this, R.color.colorGreen), getResources().getColor(R.color.colorGreenFlash));
                animateColorChangeForButton(mGreenButton, ContextCompat.getColor(this, R.color.colorGreenFlash), getResources().getColor(R.color.colorGreen));
                Log.d("TAG", "Green button animated");
                mGreenButton.setBackground(getDrawable(R.drawable.green_selector));
                break;
            case R.id.red_button:
                animateColorChangeForButton(mRedButton, ContextCompat.getColor(this, R.color.colorRed), getResources().getColor(R.color.colorRedFlash));
                animateColorChangeForButton(mRedButton, ContextCompat.getColor(this, R.color.colorRedFlash), getResources().getColor(R.color.colorRed));
                Log.d("TAG", "Red button animated");
                mRedButton.setBackground(getDrawable(R.drawable.red_selector));
                break;
            case R.id.yellow_button:
                animateColorChangeForButton(mYellowButton, ContextCompat.getColor(this, R.color.colorYellow), getResources().getColor(R.color.colorYellowFlash));
                animateColorChangeForButton(mYellowButton, ContextCompat.getColor(this, R.color.colorYellowFlash), getResources().getColor(R.color.colorYellow));
                Log.d("TAG", "Yellow button animated");
                mYellowButton.setBackground(getDrawable(R.drawable.yellow_selector));
                break;
            case R.id.blue_button:
                animateColorChangeForButton(mBlueButton, ContextCompat.getColor(this, R.color.colorBlue), getResources().getColor(R.color.colorBlueFlash));
                animateColorChangeForButton(mBlueButton, ContextCompat.getColor(this, R.color.colorBlueFlash), getResources().getColor(R.color.colorBlue));
                Log.d("TAG", "Blue button animated");
                mBlueButton.setBackground(getDrawable(R.drawable.blue_selector));
                break;
            default:
                break;
        }
    }
}
