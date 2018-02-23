package com.crevhive.govote;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.crevhive.govote.repo.PVCService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class handles the com.crevhive.govote.App Application class
 *
 * @author ToluAdetuyi
 */

public class App extends Application {

    public static final String TAG = App.class.getSimpleName();
    private static Retrofit retrofit = null;
    private static App mInstance;

    @Override
    public void onCreate() {

        super.onCreate();

        mInstance = this;
        initRetrofit();
    }

    /**
     * This activate the instance of the com.crevhive.govote.App
     *
     * @return {@link App} instance
     */
    public static synchronized App getInstance() {
        return mInstance;
    }

    /**
     * Setup retrofit and cache
     */
    void initRetrofit() {
        //  Cache cache = new Cache(getCacheDir(), 1024);
        OkHttpClient client = new OkHttpClient.Builder()

                .connectTimeout(4, TimeUnit.MINUTES)
                .readTimeout(4, TimeUnit.MINUTES)
                .writeTimeout(4, TimeUnit.MINUTES)
                .build();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(getResources().getString(R.string.baseUrl))
                .client(client)
                .build();

    }

    /**
     * Grant access to Service endpoint
     *
     * @return the Retrofit Rest Api Service for PVC
     */
    public static PVCService getBusinessService() {

        return retrofit.create(PVCService.class);
    }

    /**
     * This check if the phone is connected online
     *
     * @return true or false
     */
    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                mInstance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenet = connectivityManager.getActiveNetworkInfo();
        return activenet != null;
    }

}
