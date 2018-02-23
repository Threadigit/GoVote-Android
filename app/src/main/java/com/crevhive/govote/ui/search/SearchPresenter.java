package com.crevhive.govote.ui.search;

import android.app.Activity;
import android.util.Log;

import com.crevhive.govote.App;
import com.crevhive.govote.R;
import com.crevhive.govote.model.Location;
import com.crevhive.govote.repo.response.LocationResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by Tolu Adetuyi on 3/23/2017.
 */

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private CompositeSubscription mSubscriptions;

    public SearchPresenter(SearchContract.View view) {

        mView = view;
        mView.setPresenter(this);


    }

    @Override
    public void subscribe() {

        mSubscriptions = new CompositeSubscription();


    }

    @Override
    public void searchLocation(String area) {

        if (!App.getInstance().isOnline()) {

            mView.showMessage("No internet access");
            return;

        }

        if (area == null || area.equals("")) area = "lagos";
        mView.showLoader(true);
        App.getBusinessService().fetchLocation(area,
                getView().getString(R.string.pvc_api_key)).enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {

                if (response.isSuccessful() && response.body().getData() != null) {

                    ArrayList<Location> locations = response.body().getData();
                    String message = response.body().getMessage();

                    mView.showMessage(message);
                    mView.onFetchLocations(locations);

                } else {

                    mView.showMessage(getView().getString(R.string.loc_error_prompt));
                }

                mView.showLoader(false);


            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {

                mView.showMessage(getView().getString(R.string.loc_error_prompt));
                mView.showLoader(false);
            }
        });
    }

    @Override
    public void unsubscribe() {

        mSubscriptions.clear();
        mSubscriptions.unsubscribe();
    }


    @Override

    public Activity getView() {
        return (Activity) mView;
    }


}
