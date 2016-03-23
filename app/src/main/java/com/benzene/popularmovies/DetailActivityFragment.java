package com.benzene.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.benzene.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;

public class DetailActivityFragment extends Fragment {
    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("com.benzene.PopularMovies.model.Movie")) {
            final Movie mMovie = intent.getParcelableExtra("com.benzene.PopularMovies.model.Movie");
            final ImageView mMovieThumbnail =
                    (ImageView) rootView.findViewById(R.id.movie_thumbnail);
            final TextView mMovieTitle =
                    (TextView) rootView.findViewById(R.id.movie_title);
            final TextView mMovieOverview = (TextView) rootView.findViewById(R.id.movie_overview);
            final TextView mMovieReleaseDate =
                    (TextView) rootView.findViewById(R.id.movie_release_date);
            final TextView mMovieRating = (TextView) rootView.findViewById(R.id.movie_rating);

            Picasso.with(getActivity())
                    .load(mMovie.getAbsolutePosterPath())
                    .fit()
                    .into(mMovieThumbnail);
            mMovieTitle.setText(mMovie.getTitle());
            mMovieOverview.setText(mMovie.getOverview());
            mMovieReleaseDate.setText(DateFormat.getDateInstance().format(mMovie.getReleaseDate()));
            mMovieRating.setText(String.valueOf(mMovie.getVoteAverage()));
        }
        return rootView;
    }
}
