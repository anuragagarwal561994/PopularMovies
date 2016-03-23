package com.benzene.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.benzene.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieListAdapter extends ArrayAdapter {
    private static final String LOG_TAG = MovieListAdapter.class.getSimpleName();
    private Context mContext;
    private LayoutInflater inflater;

    public MovieListAdapter(Context context) {
        super(context, R.layout.gridview_image_item);

        this.mContext = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_image_item, parent, false);
        }

        Picasso.with(mContext)
                .load(((Movie) this.getItem(position)).getAbsolutePosterPath())
                .fit()
                .into((ImageView) convertView);

        return convertView;
    }
}
