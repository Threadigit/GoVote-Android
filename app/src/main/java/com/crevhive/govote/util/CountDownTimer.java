package com.crevhive.govote.util;

/**
 * Created by toluadetuyi on 2/19/18.
 */

public class CountDownTimer extends android.os.CountDownTimer {

    int days,hours, min, secs;
    public CountDownTimer(long millisInFuture, long countDownInterval) {


        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {

    }
}
