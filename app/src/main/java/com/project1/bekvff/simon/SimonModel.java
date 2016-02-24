package com.project1.bekvff.simon;

import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by b-kizzle on 2/18/16.
 */
public class SimonModel {

    private List list;
    private int mCurrentIndex;
    private int mCurrentScore;
    private int mHighScore;

    //Constructor creates a new Linked List
    public SimonModel() {
        createNewList();
    }

    //Function returns list itself
    public int getListSize() {
        return list.size();
    }

    //This function returns a Sequence Button at the specified index
    public SequenceButton getSequenceButtonAtIndex(int index) {
        SequenceButton sequenceButtonAtIndex;
        sequenceButtonAtIndex = (SequenceButton) list.get(index);
        return sequenceButtonAtIndex;

    }

    //This function creates a new linked list and initializes the
    //first element with a new Sequence Button
    public void createNewList() {
        mCurrentIndex = 0;
        list = new ArrayList();
        createNewSequenceButton();
        setCurrentScore(0);
    }


    //This function takes in the current round as the location for
    //the next node insert into the linked list and seeds it with
    //the R.id._____ for corresponding color
    public void createNewSequenceButton() {
        list.add(new SequenceButton(pickRandomColor()));
    }

    //This function generates a random int, mods it with 4,
    //and used that result to pick a color then returns the
    //R.id._____ for the corresponding color
    private int pickRandomColor() {
        int color;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(1000) % 4;
        switch (randomNumber) {
            case 0:
                color = R.id.green_button;
                break;
            case 1:
                color = R.id.red_button;
                break;
            case 2:
                color = R.id.yellow_button;
                break;
            case 3:
                color = R.id.blue_button;
                break;
            default:
                color = R.id.green_button;
                break;
        }
        return color;
    }

    //Returns the current score
    public int getCurrentScore() {
        return mCurrentScore;
    }

    //Returns the high score
    public int getHighScore() {
        return mHighScore;
    }

    //Sets the current score with input value
    public void setCurrentScore(int currentScore) {
        mCurrentScore = currentScore;
    }

    //Increases the current score by one and sets the high score to the
    //current score if the current score is higher than the high score
    private void increaseScores() {
        mCurrentScore++;
        if(mCurrentScore > mHighScore) {
            mHighScore = mCurrentScore;
        }
    }

    //Increments index to check next button press
    //If the index goes out of bounds, the index is reset to 0
    //and the score is update
    private void incrementIndex() {
        mCurrentIndex++;
        if(mCurrentIndex >= list.size()) {
            mCurrentIndex = 0;
            increaseScores();
            createNewSequenceButton();
        }
    }

    //Takes in the resId of the button that is pressed, checks it against
    //the button stored at the current index, if true, increment index
    public void checkButtonPress(int resIdOfButtonPressed) {
        SequenceButton button = getSequenceButtonAtIndex(mCurrentIndex);
        if(resIdOfButtonPressed == button.getTextResId()) {
            incrementIndex();
        }
        else {
            createNewList();
        }
    }
}

