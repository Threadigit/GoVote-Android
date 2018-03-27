package com.crevhive.govote.model;

/*******************************************************************************
 * LocationField
 * [Brief Description]
 *
 * Copyright 2018 by California Eastern Laboratories. All rights reserved.
 * Covered by the Cortet Mobile App License When Delivered as Source Code.
 *******************************************************************************/
public enum LocationField {
    ID("id"),
    NAME("name"),
    ADDRESS("address"),
    AREA("area"),
    LATITUDE("latitude"),
    LONGITUDE("longitude"),
    CONFIRMED("confirmed"),
    STATE("state"),
    CITY("city");

    private final String fieldString;

    LocationField(final String fieldString) {
        this.fieldString = fieldString;
    }

    @Override
    public String toString() {
        return fieldString;
    }

}
