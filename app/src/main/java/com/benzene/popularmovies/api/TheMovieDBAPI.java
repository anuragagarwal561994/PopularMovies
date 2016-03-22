package com.benzene.popularmovies.api;

import com.benzene.popularmovies.model.PopularMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMovieDBAPI {
    @GET("/3/movie/popular")
    Call<PopularMovies> getPopular(@Query("api_key") String api_key);
}
