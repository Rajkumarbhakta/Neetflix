package com.rkbapps.neetflix.models.images;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagesModel {
    @SerializedName("backdrops")
    @Expose
    private List<Backdrop> backdrops;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("posters")
    @Expose
    private List<Poster> posters;

    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    public Integer getId() {
        return id;
    }

    public List<Poster> getPosters() {
        return posters;
    }
}
