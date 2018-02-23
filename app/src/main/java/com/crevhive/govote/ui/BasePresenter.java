/*
 * @author Adetuyi Tolu
 * Base presenter class
 *
 */

package com.crevhive.govote.ui;

import android.app.Activity;

public interface BasePresenter {

    void subscribe();

    void unsubscribe();

    Activity getView();
}
