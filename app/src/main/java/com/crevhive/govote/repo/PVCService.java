package com.crevhive.govote.repo;


import com.crevhive.govote.repo.response.LocationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author toluadetuyi
 *         PVC Service
 */

public interface PVCService {

    @GET("search")
    Call<LocationResponse> fetchLocation(@Query("query") String area, @Query("key") String key);
}
