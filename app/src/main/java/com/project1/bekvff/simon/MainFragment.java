package com.project1.bekvff.simon;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


/**
 * Created by Brian Koehler on 2/22/2016.
 */
public class MainFragment extends Fragment {

    private MainFragmentListener mListener;
    private android.os.Handler mHandler;
    private Boolean isRunning = false;

    interface MainFragmentListener {
        void listenerMethod();
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
    }

    public boolean fragmentIsRunning() {
        return isRunning;
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
        if (mHandler == null) {
            mHandler = new Handler();
            mHandler.postDelayed(runnable, 1500);
            isRunning = true;
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
                mListener.listenerMethod();
            }
            Log.d("TAG", "Runnable ran");
            mHandler.postDelayed(runnable, 1000);
        }
    };
}
