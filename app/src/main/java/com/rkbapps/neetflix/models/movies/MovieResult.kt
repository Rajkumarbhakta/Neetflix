package com.rkbapps.neetflix.models.movies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResult {
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public MovieResult() {
    }

    public MovieResult(Boolean adult, Integer id, String posterPath, String releaseDate, String title, Double voteAverage) {
        this.adult = adult;
        this.id = id;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.voteAverage = voteAverage;
    }

    public Boolean getAdult() {
        return adult;
    }

    public Integer getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

}
