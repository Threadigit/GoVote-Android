package com.crevhive.govote.model;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * InstrumentedTestSearchModel
 * [Brief Description]
 *
 * Copyright 2018 by California Eastern Laboratories. All rights reserved.
 * Covered by the Cortet Mobile App License When Delivered as Source Code.
 *******************************************************************************/
@RunWith(AndroidJUnit4.class)
public class InstrumentedTestSearchModel {

    private static final String TEST_NAME = "this is my test name";
    private static final int TEST_IS_HISTORY_INT = 1;
    private static final int TEST_IS_NOT_HISTORY_INT = 0;
    private static final boolean TEST_IS_HISTORY = true;

    /**
     * Testing that writeToParcel writes the correct value - specifically for a mIsHistory = false
     * @throws Exception
     */
    @Test
    public void write_to_parcel_is_correct_not_history() throws Exception {
        Search searchUnderTest  = new Search(TEST_NAME);

        Parcel testResultParcel = Parcel.obtain();
        searchUnderTest.writeToParcel(testResultParcel, 0);
        testResultParcel.setDataPosition(0);

        assertEquals(TEST_NAME, testResultParcel.readString());
        assertEquals(TEST_IS_NOT_HISTORY_INT, testResultParcel.readInt());
    }

    /**
     * Testing that writeToParcel writes the correct value - specifically for a mIsHistory = true
     * @throws Exception
     */
    @Test
    public void write_to_parcel_is_correct_is_history() throws Exception {
        Search searchUnderTest  = new Search(TEST_NAME);
        searchUnderTest.setIsHistory(true);

        Parcel testResultParcel = Parcel.obtain();
        searchUnderTest.writeToParcel(testResultParcel, 0);
        testResultParcel.setDataPosition(0);

        assertEquals(TEST_NAME, testResultParcel.readString());
        assertEquals(TEST_IS_HISTORY_INT, testResultParcel.readInt());
    }

    /**
     * Testing the search Creator properly constructs a Search object from a valid Parcel
     * @throws Exception
     */
    @Test
    public void search_parcle_creator_test() throws Exception {
        Search searchUnderTest = new Search(TEST_NAME);
        searchUnderTest.setIsHistory(false);

        Parcel testResultParcel = Parcel.obtain();
        searchUnderTest.writeToParcel(testResultParcel, 0);
        testResultParcel.setDataPosition(0);

        Search testResultSearch = Search.CREATOR.createFromParcel(testResultParcel);

        assertEquals(TEST_NAME, testResultSearch.getBody());
        assertEquals(!TEST_IS_HISTORY, testResultSearch.getIsHistory());

    }
}
