package com.rkbapps.neetflix.models;

import com.rkbapps.neetflix.models.movies.MovieResult;
import com.rkbapps.neetflix.models.tvseries.TvSeriesResult;

import java.util.List;

public class MovieList {

    public static final int TV_SERIES = 1;
    public static final int MOVIE = 0;

    private int viewType;
    private String type;

    private List<MovieResult> movieList;

    private List<TvSeriesResult> tvSeriesList;


    public MovieList(int viewType, String type, List<MovieResult> movieList, int i) {
        this.viewType = viewType;
        this.type = type;
        this.movieList = movieList;
    }

    public MovieList(int viewType, String type, List<TvSeriesResult> tvSeriesList) {
        this.viewType = viewType;
        this.type = type;
        this.tvSeriesList = tvSeriesList;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<MovieResult> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieResult> movieList) {
        this.movieList = movieList;
    }

    public List<TvSeriesResult> getTvSeriesList() {
        return tvSeriesList;
    }

    public void setTvSeriesList(List<TvSeriesResult> tvSeriesList) {
        this.tvSeriesList = tvSeriesList;
    }
}
