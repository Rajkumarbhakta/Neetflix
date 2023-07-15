package com.rkbapps.neetflix.models.movies

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rkbapps.neetflix.models.Genre

data class MovieModel(
    @SerializedName("adult")
    @Expose
    var adult: Boolean,

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String,

    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: Any,

    @SerializedName("budget")
    @Expose
    var budget: Long,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>,

    @SerializedName("homepage")
    @Expose
    var homepage: String,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("imdb_id")
    @Expose
    var imdbId: String,

    @SerializedName("original_language")
    @Expose
    var originalLanguage: String,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String,

    @SerializedName("overview")
    @Expose
    var overview: String,
    @SerializedName("popularity")
    @Expose
    var popularity: Double,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String,

    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>,

    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String,

    @SerializedName("revenue")
    @Expose
    var revenue: Long,

    @SerializedName("runtime")
    @Expose
    var runtime: Int,

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>,

    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("tagline")
    @Expose
    var tagline: String,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("video")
    @Expose
    var video: Boolean,

    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int,
) {
    val movieBudget: String
        get() = "" + budget / 1000000 + "M"
    val movieRevenue: String
        get() = "" + revenue / 1000000 + "M"
    val movieLength: String
        get() = "" + runtime / 60 + "h" + runtime % 60 + "m"
}


/*
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    private Object belongsToCollection;
    @SerializedName("budget")
    @Expose
    private Long budget;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompany> productionCompanies;
    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountry> productionCountries;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("revenue")
    @Expose
    private Long revenue;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> spokenLanguages;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
 */