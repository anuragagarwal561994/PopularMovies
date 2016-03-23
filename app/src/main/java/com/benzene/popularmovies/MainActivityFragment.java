package com.benzene.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.benzene.popularmovies.api.TheMovieDBAPI;
import com.benzene.popularmovies.model.Movie;
import com.benzene.popularmovies.model.MovieResults;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements retrofit2.Callback<MovieResults> {
    private static final String LOG_TAG = MainActivityFragment.class.getSimpleName();
    private MovieListAdapter mMovieListAdapter;
    private TheMovieDBAPI theMovieDBAPI;

    public MainActivityFragment() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        theMovieDBAPI = retrofit.create(TheMovieDBAPI.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovieList();
    }

    public void updateMovieList() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = prefs.getString(
                getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_popular));

        if (sortOrder.equals(getString(R.string.pref_sort_popular))) {
            getPopularMoviesCall().enqueue(this);
        } else if (sortOrder.equals(getString(R.string.pref_sort_top_rated))) {
            getTopRatedMoviesCall().enqueue(this);
        } else {
            Log.d(LOG_TAG, getString(R.string.sort_order_not_found) + ": " + sortOrder);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        gridView.setNumColumns(Math.round(displayMetrics.widthPixels / TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 185, displayMetrics)));

        mMovieListAdapter = new MovieListAdapter(getActivity());
        gridView.setAdapter(mMovieListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = (Movie) mMovieListAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("com.benzene.PopularMovies.model.Movie", movie);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private Call<MovieResults> getTopRatedMoviesCall() {
        return theMovieDBAPI.getTopRated(BuildConfig.THE_MOVIEDB_API_KEY);
    }

    private Call<MovieResults> getPopularMoviesCall() {
        return theMovieDBAPI.getPopular(BuildConfig.THE_MOVIEDB_API_KEY);
    }

    @Override
    public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
        //TODO: show an image when there are no images
        mMovieListAdapter.clear();
        mMovieListAdapter.addAll(response.body().getResults());
    }

    @Override
    public void onFailure(Call<MovieResults> call, Throwable t) {
        Toast.makeText(
                getActivity(),
                getString(R.string.error_data_fetching),
                Toast.LENGTH_SHORT).show();
    }
}
