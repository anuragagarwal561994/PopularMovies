package com.benzene.popularmovies.model;

import java.util.List;

public class PopularMovies {
    Integer page;
    List<Movie> results;
    Integer total_results;
    Integer total_pages;

    public Integer getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public Integer getTotalResults() {
        return total_results;
    }

    public Integer getTotalPages() {
        return total_pages;
    }
}
