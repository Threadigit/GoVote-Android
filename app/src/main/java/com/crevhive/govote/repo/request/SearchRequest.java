package com.crevhive.govote.repo.request;

/**
 * Created by toluadetuyi on 2/20/18.
 */

public class SearchRequest {

    private String query;
    private String key = "k9ihbvse57fvsujbsvsi5362WE$NFD2";

    public SearchRequest(String query) {

        this.query = query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
