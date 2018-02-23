package com.crevhive.govote.repo.response;

import com.crevhive.govote.model.Location;

import java.util.ArrayList;

/**
 * Created by toluadetuyi on 2/20/18.
 */

public class LocationResponse {

    private String message;
    private ArrayList<Location> data;

    public String getMessage() {

        return message;
    }

    public ArrayList<Location> getData() {

        return data;
    }
}
