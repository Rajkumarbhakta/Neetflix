package com.rkbapps.neetflix.models.person;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalIds {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("facebook_id")
    @Expose
    private String facebookId;
    @SerializedName("instagram_id")
    @Expose
    private String instagramId;
    @SerializedName("twitter_id")
    @Expose
    private String twitterId;

    public Integer getId() {
        return id;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public String getInstagramId() {
        return instagramId;
    }

    public String getTwitterId() {
        return twitterId;
    }
}
