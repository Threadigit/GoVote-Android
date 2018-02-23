package com.crevhive.govote.ui.search;

import com.crevhive.govote.model.Location;
import com.crevhive.govote.ui.BasePresenter;
import com.crevhive.govote.ui.BaseView;

import java.util.List;

/**
 * @author toluadetuyi
 *         This is the contract class for search
 */

public interface SearchContract {

    interface View extends BaseView<Presenter> {

        void showMessage(String message);

        void showLoader(boolean show);

        void onFetchLocations(List<Location> locations);

    }

    interface Presenter extends BasePresenter {

        void searchLocation(String area);

    }
}
