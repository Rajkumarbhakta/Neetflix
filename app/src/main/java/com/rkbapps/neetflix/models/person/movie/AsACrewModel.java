package com.rkbapps.neetflix.models.person.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AsACrewModel {
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
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
    @SerializedName("department")
    @Expose
    private String department;
    @SerializedName("job")
    @Expose
    private String job;

    public Boolean getAdult() {
        return adult;
    }

    public Integer getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
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

    public String getDepartment() {
        return department;
    }

    public String getJob() {
        return job;
    }
}
