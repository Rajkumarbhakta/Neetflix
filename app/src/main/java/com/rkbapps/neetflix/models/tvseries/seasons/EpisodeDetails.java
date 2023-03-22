package com.rkbapps.neetflix.models.tvseries.seasons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rkbapps.neetflix.models.castandcrew.Cast;

import java.io.Serializable;
import java.util.List;


public class EpisodeDetails implements Serializable {
    @SerializedName("air_date")
    @Expose
    private String airDate;
    @SerializedName("episode_number")
    @Expose
    private Integer episodeNumber;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("season_number")
    @Expose
    private Integer seasonNumber;
    @SerializedName("show_id")
    @Expose
    private Integer showId;
    @SerializedName("still_path")
    @Expose
    private String stillPath;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("guest_stars")
    @Expose
    private List<Cast> guestStars;

    public String getAirDate() {
        return airDate;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public Integer getShowId() {
        return showId;
    }

    public String getStillPath() {
        return stillPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public List<Cast> getGuestStars() {
        return guestStars;
    }

    @Override
    public String toString() {
        return "EpisodeDetails{" +
                "airDate='" + airDate + '\'' +
                ", episodeNumber=" + episodeNumber +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", runtime=" + runtime +
                ", seasonNumber=" + seasonNumber +
                ", showId=" + showId +
                ", stillPath='" + stillPath + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", guestStars=" + guestStars +
                '}';
    }
}
