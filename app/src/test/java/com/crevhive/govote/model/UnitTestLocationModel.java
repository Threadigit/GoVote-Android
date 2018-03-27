package com.crevhive.govote.model;

import android.util.Log;
import com.google.android.gms.maps.model.LatLng;
import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;


/*******************************************************************************
 * UnitTestLocationModel
 * [Brief Description]
 *
 * Copyright 2018 by California Eastern Laboratories. All rights reserved.
 * Covered by the Cortet Mobile App License When Delivered as Source Code.
 *******************************************************************************/
public class UnitTestLocationModel {

    private static final String LOGGING_TAG = UnitTestLocationModel.class.getSimpleName();

    private static final int TEST_ID = 8008;
    private static final String TEST_LAT_STR = "26.512620";
    private static final String TEST_LON_STR = "-77.077651";
    private static final double TEST_LAT = 26.512620;
    private static final double TEST_LON = -77.077651;
    private static final double TEST_LAT_LNG_DELTA = 0.0; // No room for loss here!


    /**
     * Basic test to make sure compilation and reflection worked
     * @throws Exception
     */
    @Test
    public void id_is_correct() throws Exception {
        Location locationUnderTest = getLocationUnderTest();
        assertEquals(TEST_ID, locationUnderTest.getId());
    }

    /**
     * Test proper LatLng value for latitude and longitude String members
     * @throws Exception
     */
    @Test
    public void lat_lng_is_correct() throws Exception {
        Location locationUnderTest = getLocationUnderTest();
        LatLng testLatLng = locationUnderTest.getLocation();
        assertEquals(TEST_LAT, testLatLng.latitude, TEST_LAT_LNG_DELTA);
        assertEquals(TEST_LON, testLatLng.longitude, TEST_LAT_LNG_DELTA);
    }


    /**
     * Use reflection to set up Location object to test. If there is an exception log it and return
     * an empty Location object to cause test failures.
     * @return
     */
    private Location getLocationUnderTest() {

        Class<?> clazz = Location.class;
        try {
            Location reflectLocation = (Location) clazz.newInstance();

            Field field = reflectLocation
                    .getClass()
                    .getDeclaredField(LocationField.ID.toString());
            field.setAccessible(true);
            field.set(reflectLocation, TEST_ID);

            field = reflectLocation
                    .getClass()
                    .getDeclaredField(LocationField.LATITUDE.toString());
            field.setAccessible(true);
            field.set(reflectLocation, TEST_LAT_STR);

            field = reflectLocation
                    .getClass()
                    .getDeclaredField(LocationField.LONGITUDE.toString());
            field.setAccessible(true);
            field.set(reflectLocation, TEST_LON_STR);

            return reflectLocation;

        } catch (NoSuchFieldException e) {
            Log.e(LOGGING_TAG, e.getLocalizedMessage());
        } catch (InstantiationException e) {
            Log.e(LOGGING_TAG, e.getLocalizedMessage());
        } catch (IllegalAccessException e) {
            Log.e(LOGGING_TAG, e.getLocalizedMessage());
        }

        return new Location();
    }
}
