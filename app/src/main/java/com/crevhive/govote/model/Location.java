package com.crevhive.govote.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by toluadetuyi on 2/20/18.
 */

public class Location {

    private int id;
    private String name;
    private String address;
    private String area;
    private String latitude;
    private String longitude;
    private String confirmed;
    private String state;
    private String city;

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getArea() {
        return area;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getLatitude() {

        return latitude;
    }

    public LatLng getLocation() {

        if (latitude != null && longitude != null && !longitude.equals("") && !latitude.equals(""))
            return new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        else {
            return null;
        }
    }

    public String getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }
}
