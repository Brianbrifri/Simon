package com.project1.bekvff.simon;

/**
 * Created by b-kizzle on 2/18/16.
 */
public class SequenceButton {
    private int mTextResId;
//    private int mPrimaryColor;
//    private int mFlashColor;

    public SequenceButton (int resId) {
        mTextResId = resId;
//        setBothColors(resId);
    }

    public int getTextResId() {
        return mTextResId;
    }

/*    public int getPrimaryColor() {
        return mPrimaryColor;
    }

    public int getFlashColor() {
        return mFlashColor;
    }

    private void setBothColors(int resId) {
        if(resId == R.id.green_button) {
            mPrimaryColor = R.color.colorGreen;
            mFlashColor = R.color.colorGreenFlash;
        }

        else if(resId == R.id.blue_button) {
            mPrimaryColor = R.color.colorBlue;
            mFlashColor = R.color.colorBlueFlash;
        }

        else if(resId == R.id.yellow_button) {
            mPrimaryColor = R.color.colorYellow;
            mFlashColor = R.color.colorYellowFlash;
        }

        else {
            mPrimaryColor = R.color.colorRed;
            mFlashColor = R.color.colorRedFlash;
        }
    }
*/
}
