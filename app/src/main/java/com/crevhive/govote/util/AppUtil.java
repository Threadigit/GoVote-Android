package com.crevhive.govote.util;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.crevhive.govote.R;

/**
 * Created by toluadetuyi on 2/19/18.
 */

public class AppUtil {
    public static void configureSheet(Dialog dialog, Activity activity) {


        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        int width = activity.getResources().getDimensionPixelSize(R.dimen.bottom_sheet_width);
        dialog.getWindow().setLayout(width > 0 ? width : ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

    }
}
