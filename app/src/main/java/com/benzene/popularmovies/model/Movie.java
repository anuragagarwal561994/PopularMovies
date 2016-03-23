package com.benzene.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Movie implements Parcelable {
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

    public String getAbsolutePosterPath() {
        return "http://image.tmdb.org/t/p/w185/" + getPosterPath();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.poster_path);
        dest.writeValue(this.adult);
        dest.writeString(this.overview);
        dest.writeLong(release_date != null ? release_date.getTime() : -1);
        dest.writeString(this.original_title);
        dest.writeString(this.original_language);
        dest.writeString(this.title);
        dest.writeString(this.backdrop_path);
        dest.writeValue(this.popularity);
        dest.writeValue(this.vote_count);
        dest.writeValue(this.video);
        dest.writeValue(this.vote_average);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.poster_path = in.readString();
        this.adult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.overview = in.readString();
        long tmpRelease_date = in.readLong();
        this.release_date = tmpRelease_date == -1 ? null : new Date(tmpRelease_date);
        this.original_title = in.readString();
        this.original_language = in.readString();
        this.title = in.readString();
        this.backdrop_path = in.readString();
        this.popularity = (Float) in.readValue(Float.class.getClassLoader());
        this.vote_count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.video = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.vote_average = (Float) in.readValue(Float.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
