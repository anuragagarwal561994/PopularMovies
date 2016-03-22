package com.benzene.popularmovies.model;

import java.util.Date;

public class Movie {
    Integer id;
    String poster_path;
    Boolean adult;
    String overview;
    Date release_date;
    String original_title;
    String original_language;
    String title;
    String backdrop_path;
    Float popularity;
    Integer vote_count;
    Boolean video;
    Float vote_average;

    public Integer getId() {
        return id;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return release_date;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public Float getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public Float getVoteAverage() {
        return vote_average;
    }
}
