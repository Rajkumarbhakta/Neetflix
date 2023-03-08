package com.rkbapps.neetflix.models.person.tvseries;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WorkForSeries {
    @SerializedName("cast")
    @Expose
    private List<AsACastModel> cast;
    @SerializedName("crew")
    @Expose
    private List<AsACrewModel> crew;
    @SerializedName("id")
    @Expose
    private Integer id;

    public List<AsACastModel> getCast() {
        return cast;
    }

    public List<AsACrewModel> getCrew() {
        return crew;
    }

    public Integer getId() {
        return id;
    }
}
