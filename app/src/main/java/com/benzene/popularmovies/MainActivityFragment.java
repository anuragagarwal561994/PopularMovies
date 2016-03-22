package com.benzene.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.benzene.popularmovies.api.TheMovieDBAPI;
import com.benzene.popularmovies.model.PopularMovies;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements retrofit2.Callback<PopularMovies> {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public void onStart() {
        super.onStart();
        updatePopularMovies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    private void updatePopularMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMovieDBAPI theMovieDBAPI = retrofit.create(TheMovieDBAPI.class);
        Call<PopularMovies> call = theMovieDBAPI.getPopular(BuildConfig.THE_MOVIEDB_API_KEY);

        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
        PopularMovies popularMovies = response.body();
        Log.d(LOG_TAG, popularMovies.getTotalPages().toString());
    }

    @Override
    public void onFailure(Call<PopularMovies> call, Throwable t) {
        Toast.makeText(
                getActivity(),
                getString(R.string.error_data_fetching),
                Toast.LENGTH_SHORT).show();
    }
}
