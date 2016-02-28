package com.project1.bekvff.simon;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Brian Koehler on 2/22/2016.
 */
public class MainFragment extends Fragment {

    private SimonModel model;
    private MainFragmentListener mListener;
    private android.os.Handler mHandler;
    private Boolean isRunning = false;
    private int mCurrentScore;
    private int mHighScore ;
    private int mCurrentIndex;

    interface MainFragmentListener {
        void listenerMethod(int textResId);
    }

    //This function sets the retain instance to true so it will
    //persist through activity destroys. Also sets listener to the
    //activity
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Activity activity = getActivity();
        if (activity instanceof MainFragmentListener) {
            mListener = (MainFragmentListener) activity;
            Log.d("TAG", "Activity is a listener");
        }
        model = new SimonModel();
    }

    public int getCurrentScore() {
        return mCurrentScore;
    }

    public int getHighScore() {
        return mHighScore;
    }

    public int getCurrentIndex() {
        return mCurrentIndex;
    }

    public boolean fragmentIsRunning() {
        return isRunning;
    }

    public void createNewList() {
        model.createNewList();
        mCurrentScore = 0;
        mCurrentIndex = 0;
    }

    //This function will increment the current score by one as well as the
    //high score if the current score is greater
    private void increaseScores() {
        mCurrentScore++;
        if(mCurrentScore > mHighScore) {
            mHighScore = mCurrentScore;
        }
    }

    //This function increments the index by 1. If it reaches the end of the array list,
    //it sets the index back to 0, increases the score (because you have pressed all
    //buttons correctly), updates the score view, and adds a new button to the sequence
    private void incrementIndex() {
        mCurrentIndex++;
        if(mCurrentIndex >= model.getListSize()) {
            mCurrentIndex = 0;
            increaseScores();
            model.createNewSequenceButton();
            startSequence();
        }
    }

    //This function is called when a button is clicked. The resId of the button
    //is passed through and checked against the sequence at the current index.
    //If it is correct, the index is incremented; otherwise, a new list is created,
    //index and current score are reset, score view is updated, and a toast is displayed
    public void checkButtonPress(int resIdOfButtonPressed) {
        SequenceButton button = model.getSequenceButtonAtIndex(mCurrentIndex);
        if(resIdOfButtonPressed == button.getTextResId()) {
            incrementIndex();
        }
        else {
            model.createNewList();
            mCurrentIndex = 0;
            mCurrentScore = 0;
            Toast.makeText(getActivity(), R.string.fail_toast, Toast.LENGTH_SHORT).show();
        }
    }

    //Function to remove callbacks whenever the fragment stops running
    @Override
    public void onStop() {
        super.onStop();
        mListener = null;
        if (isRunning) {
            mHandler.removeCallbacks(runnable);
        }
        Log.d("TAG", "OnStop fragment");
    }

    //Starts the fragment on "this" activity
    @Override
    public void onStart() {
        super.onStart();
        Log.d("TAG", "onStart fragment");
        Activity activity = getActivity();
        if (activity instanceof MainFragmentListener) {
            mListener = (MainFragmentListener) activity;
        }
        if (isRunning) {
            mHandler.postDelayed(runnable, 1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy called");
        if(mHandler != null) {
            mHandler.removeCallbacks(runnable);
        }
    }

    public void startSequence() {
        int i;
        if (mHandler == null) {
            mHandler = new Handler();
//            mHandler.postDelayed(runnable, 1000);
            for(i = 0; i < model.getListSize(); i++) {
                Log.d("TAG", "IsRunning is: " +isRunning);
                isRunning = true;
                final int finalI = i;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListener.listenerMethod(model.getSequenceButtonAtIndex(finalI).getTextResId());
                        Log.d("TAG", "FinalI is: " +finalI);
                    }
                }, i * 1000);
            }
            isRunning = false;
            Log.d("TAG", "IsRunning is: " +isRunning);
        }
    }

    public void stopSequence() {
        if (mHandler != null) {
            mHandler.removeCallbacks(runnable);
            isRunning = false;
            mHandler = null;
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (mListener != null) {
                mListener.listenerMethod(model.getSequenceButtonAtIndex(0).getTextResId());
            }
            Log.d("TAG", "Runnable ran");
        }
    };
}
