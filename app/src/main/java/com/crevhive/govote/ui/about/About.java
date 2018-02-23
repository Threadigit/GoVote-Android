package com.crevhive.govote.ui.about;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crevhive.govote.R;
import com.crevhive.govote.util.AppUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by toluadetuyi on 2/21/18.
 */

public class About extends BottomSheetDialogFragment {

    @BindView(R.id.about)
    TextView about;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.BottomSheetDialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.diag_about, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)

            about.setText(Html.fromHtml(getString(R.string.about_app), Html.FROM_HTML_MODE_COMPACT));

        else

            about.setText(Html.fromHtml(getString(R.string.about_app)));

    }

    @Override
    public void onStart() {

        super.onStart();
        AppUtil.configureSheet(getDialog(), getActivity());
    }

}
