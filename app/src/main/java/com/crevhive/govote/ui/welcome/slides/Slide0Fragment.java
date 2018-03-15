package com.crevhive.govote.ui.welcome.slides;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crevhive.govote.R;
import com.crevhive.govote.util.CountDownTimer;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Slide0Fragment extends Fragment implements CountDownTimer.CountDownTimerListener {


    private TextView txtCountdownDays;
    private TextView txtCountdownHours;
    private TextView txtCountdownMins;
    private TextView txtCountdownSecs;
    private CountDownTimer countDownTimer;
    private Calendar startCalendar;
    private Calendar endCalendar;

    public Slide0Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startCalendar = Calendar.getInstance();
        endCalendar = Calendar.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.welcome_slide0, container, false);
        txtCountdownDays = mView.findViewById(R.id.txt_countdown_days);
        txtCountdownHours = mView.findViewById(R.id.txt_countdown_hours);
        txtCountdownMins = mView.findViewById(R.id.txt_countdown_mins);
        txtCountdownSecs = mView.findViewById(R.id.txt_countdown_secs);

        return mView;
    }


    @Override
    public void onCountDownTimerTick(long d, long h, long m, long s) {
        txtCountdownDays.setText(d < 10 ? "0" + String.valueOf(d) : String.valueOf(d) );
        txtCountdownHours.setText(h < 10 ? "0" + String.valueOf(h) : String.valueOf(h));
        txtCountdownMins.setText(m < 10 ? "0" + String.valueOf(m) : String.valueOf(m));
        txtCountdownSecs.setText(s < 10 ? "0" + String.valueOf(s) : String.valueOf(s));
    }

    @Override
    public void onCountDownTimerFinish() {
        txtCountdownDays.setText(R.string.default_time_text);
        txtCountdownHours.setText(R.string.default_time_text);
        txtCountdownMins.setText(R.string.default_time_text);
        txtCountdownSecs.setText(R.string.default_time_text);
    }


    @Override
    public void onResume() {
        super.onResume();
        startCalendar.setTime(new Date());
        endCalendar.set(2019,1,16,1, 0, 0);

        long startTimeMillis = startCalendar.getTimeInMillis();
        long endTimeMillis = endCalendar.getTimeInMillis();
        long totalTimeMillis = endTimeMillis - startTimeMillis;

        countDownTimer = new CountDownTimer(totalTimeMillis, 1000, this);
        countDownTimer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }
}
