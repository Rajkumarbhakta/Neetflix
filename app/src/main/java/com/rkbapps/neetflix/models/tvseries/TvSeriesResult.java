package com.rkbapps.neetflix.models.tvseries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvSeriesResult {
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("vote_average")
    @Expose
    private Integer voteAverage;

    public TvSeriesResult() {
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Integer voteAverage) {
        this.voteAverage = voteAverage;
    }
}
