package com.rkbapps.neetflix.models.person.tvseries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AsACastModel {
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("character")
    @Expose
    private String character;
    @SerializedName("episode_count")
    @Expose
    private Integer episodeCount;

    public Boolean getAdult() {
        return adult;
    }

    public Integer getId() {
        return id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public String getName() {
        return name;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getCharacter() {
        return character;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }
}
