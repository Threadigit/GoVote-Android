package com.crevhive.govote.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by toluadetuyi on 2/19/18.
 */

public class CountDownTimer extends android.os.CountDownTimer {

    private CountDownTimerListener countDownTimerListener;

    public interface CountDownTimerListener {
        void onCountDownTimerTick(long d, long h, long m, long s);
        void onCountDownTimerFinish();
    }

    public CountDownTimer(long millisInFuture, long countDownInterval, CountDownTimerListener countDownTimerListener) {
        super(millisInFuture, countDownInterval);
        this.countDownTimerListener = countDownTimerListener;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
        millisUntilFinished -= TimeUnit.DAYS.toMillis(days);

        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
        millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);

        long min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
        millisUntilFinished -= TimeUnit.MINUTES.toMillis(min);

        long secs = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

        countDownTimerListener.onCountDownTimerTick(days, hours, min, secs);
    }

    @Override
    public void onFinish() {
        countDownTimerListener.onCountDownTimerFinish();
    }
}
