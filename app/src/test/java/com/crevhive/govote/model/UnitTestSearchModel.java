package com.crevhive.govote.model;

import android.os.Parcel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/*******************************************************************************
 * UnitTestSearchModel
 * [Brief Description]
 *
 * Copyright 2018 by California Eastern Laboratories. All rights reserved.
 * Covered by the Cortet Mobile App License When Delivered as Source Code.
 *******************************************************************************/
@RunWith(MockitoJUnitRunner.class)
public class UnitTestSearchModel {

    private static final String TEST_NAME = "this is my test name";
    private static final int TEST_IS_HISTORY_INT = 1;
    private static final int TEST_IS_NOT_HISTORY_INT = 0;
    private static final boolean TEST_IS_HISTORY = true;

    @Mock Parcel parcel;
    @Mock Parcel testWrite;

    @Test
    public void name_is_correct() throws Exception {
        Search searchUnderTest = new Search(TEST_NAME);
        assertEquals(TEST_NAME, searchUnderTest.getBody());
    }

    @Test
    public void parcel_constructor_is_correct_not_history() throws Exception{
        when(parcel.readString()).thenReturn(TEST_NAME);
        when(parcel.readInt()).thenReturn(TEST_IS_NOT_HISTORY_INT);

        Search searchUnderTest = new Search(parcel);

        assertEquals(TEST_NAME, searchUnderTest.getBody());
        assertEquals(!TEST_IS_HISTORY, searchUnderTest.getIsHistory());
    }

    @Test
    public void parcel_constructor_is_correct_is_history() throws Exception{
        when(parcel.readString()).thenReturn(TEST_NAME);
        when(parcel.readInt()).thenReturn(TEST_IS_HISTORY_INT);

        Search searchUnderTest = new Search(parcel);

        assertEquals(TEST_NAME, searchUnderTest.getBody());
        assertEquals(TEST_IS_HISTORY, searchUnderTest.getIsHistory());
    }



}
