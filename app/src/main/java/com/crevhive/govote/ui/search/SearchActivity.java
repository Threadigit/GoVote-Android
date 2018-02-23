package com.crevhive.govote.ui.search;

/**
 * @author toluAdetuyi
 */

import android.Manifest;
import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.FloatingSearchView.OnSearchListener;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.crevhive.govote.R;
import com.crevhive.govote.model.Location;
import com.crevhive.govote.model.Search;
import com.crevhive.govote.ui.about.About;
import com.crevhive.govote.ui.search.SearchContract.Presenter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchActivity extends FragmentActivity implements OnMapReadyCallback, SearchContract.View {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private SearchContract.Presenter mPresenter;
    Snackbar statusSnackBar;
    List<Search> suggestions = new ArrayList<>();

    @BindView(R.id.search_text)
    FloatingSearchView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        init();

    }

    private void init() {

        checkversionForDisplay();
        changeStatusBarColor();
        initMap();
        initSearch();
        new SearchPresenter(this);

        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);

    }

    private void initMap() {

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        statusSnackBar = Snackbar.make(mapFragment.getView(), getString(R.string.loading),
                Snackbar.LENGTH_INDEFINITE);
    }

    private void initSearch() {

        mSearch.setActionMenuOverflowColor(R.color.white);
        mSearch.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {

                suggestions = new ArrayList<>();
                suggestions.add(new Search(newQuery));

                mSearch.swapSuggestions(suggestions);
            }
        });

        mSearch.setOnSearchListener(new OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

                showMessage(searchSuggestion.getBody());
                mSearch.clearSearchFocus();
                mSearch.clearQuery();
                mPresenter.searchLocation(searchSuggestion.getBody());

            }

            @Override
            public void onSearchAction(String currentQuery) {


            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style));

        LatLng lagos = new LatLng(6.5059457, 3.361695);

        // Showing the current location in Google Map
        mMap.addMarker(new MarkerOptions().position(lagos).title("Lagos, Nigeria"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lagos));
        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        checkPermission();
        mPresenter.searchLocation(mSearch.getQuery().toString());
    }

    private void checkPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{permission.ACCESS_FINE_LOCATION, permission.ACCESS_FINE_LOCATION},
                    000);
        } else {

            mMap.setMyLocationEnabled(true);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }

    }

    /**
     * Display full screen without bar
     */
    private void checkversionForDisplay() {

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().
                    setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void setPresenter(Presenter presenter) {

        mPresenter = presenter;
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLoader(boolean show) {

        if (show) statusSnackBar.show();
        else statusSnackBar.dismiss();

    }

    @Override
    public void onFetchLocations(List<Location> locations) {

        mMap.clear();
        for (Location location : locations) {

            if (location.getLocation() == null) continue;

            MarkerOptions options = new MarkerOptions().title(location.getArea())
                    .snippet(location.getAddress())
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.ic_launcher))
                    .position(location.getLocation());

            mMap.addMarker(options);


        }

        mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker mark) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + mark.getSnippet());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });

    }

    @OnClick(R.id.btn_share)
    public void shareApp(View v) {

        shareApp();
    }

    @OnClick(R.id.btn_about)
    public void aboutApp(View v) {

        new About().show(getSupportFragmentManager(), "");
    }

    @OnClick(R.id.btn_reload)
    public void reloadApp(View v) {

        mPresenter.searchLocation(null);

    }

    void shareApp() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = getResources().getString(R.string.playstore_download);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);

                }
            }

        }
    }
}

