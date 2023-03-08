package com.rkbapps.neetflix.models.images;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonImageModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("profiles")
    @Expose
    private List<Profile> profiles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }
}
