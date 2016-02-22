package com.project1.bekvff.simon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by b-kizzle on 2/18/16.
 */
public class SimonModel {

    private List list;
    private int mCurrentScore;
    private int mHighScore;

    //Constructor creates a new Linked List
    public SimonModel() {
        createNewList();
    }

    //Function returns list itself
    public List getList() {
        return list;
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
        list = new ArrayList();
        createNewSequenceButton(0);
        setCurrentScore(0);
    }


    //This function takes in the current round as the location for
    //the next node insert into the linked list and seeds it with
    //the R.id._____ for corresponding color
    public void createNewSequenceButton(int currentRound) {
        list.add(currentRound, new SequenceButton(pickRandomColor()));
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

    public int getCurrentScore() {
        return mCurrentScore;
    }

    public void setCurrentScore(int currentScore) {
        mCurrentScore = currentScore;
    }

    public void increaseScores() {
        mCurrentScore++;
        if(mCurrentScore > mHighScore) {
            mHighScore = mCurrentScore;
        }
    }

    public int getHighScore() {
        return mHighScore;
    }
}

