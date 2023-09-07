package com.rkbapps.neetflix.models.tvseries

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rkbapps.neetflix.models.Genre
import com.rkbapps.neetflix.models.movies.ProductionCompany
import com.rkbapps.neetflix.models.movies.ProductionCountry
import com.rkbapps.neetflix.models.movies.SpokenLanguage

data class TvSeriesModel(
    @SerializedName("adult")
    @Expose
    var adult: Boolean,

    @JvmField
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String?,

    @SerializedName("created_by")
    @Expose
    var createdBy: List<CreatedBy>,

    @SerializedName("episode_run_time")
    @Expose
    var episodeRunTime: List<Int>,

    @JvmField
    @SerializedName("first_air_date")
    @Expose
    var firstAirDate: String,

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>,

    @SerializedName("homepage")
    @Expose
    var homepage: String,

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("in_production")
    @Expose
    var inProduction: Boolean,

    @SerializedName("languages")
    @Expose
    var languages: List<String>,

    @JvmField
    @SerializedName("last_air_date")
    @Expose
    var lastAirDate: String,

    @SerializedName("last_episode_to_air")
    @Expose
    var lastEpisodeToAir: Episode,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("next_episode_to_air")
    @Expose
    var nextEpisodeToAir: Episode,

    @JvmField
    @SerializedName("networks")
    @Expose
    var networks: List<ProductionCompany>,

    @SerializedName("number_of_episodes")
    @Expose
    var numberOfEpisodes: Int,

    @SerializedName("number_of_seasons")
    @Expose
    var numberOfSeasons: Int,

    @SerializedName("origin_country")
    @Expose
    var originCountry: List<String>,

    @JvmField
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String,

    @SerializedName("original_name")
    @Expose
    var originalName: String,

    @JvmField
    @SerializedName("overview")
    @Expose
    var overview: String,

    @SerializedName("popularity")
    @Expose
    var popularity: Double,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String,

    @JvmField
    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>,

    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>,

    @JvmField
    @SerializedName("seasons")
    @Expose
    var seasons: List<Season>,

    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>,

    @JvmField
    @SerializedName("status")
    @Expose
    var status: String,

    @SerializedName("tagline")
    @Expose
    var tagline: String,

    @JvmField
    @SerializedName("type")
    @Expose
    var type: String,

    @JvmField
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double,

    @SerializedName("vote_count")
    @Expose
    var voteCount: Int,

//    val totalEpisode: String,
//    get(
//) = numberOfEpisodes.toString() + "E"
//val totalSeasons: String,
//get() = numberOfSeasons.toString() + "S"

) {
    fun getTotalEpisode(): String {
        return numberOfEpisodes.toString() + "E"
    }

    fun getTotalSeasons(): String {
        return numberOfSeasons.toString() + "S"
    }
}

/*
 @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("created_by")
    @Expose
    private List<CreatedBy> createdBy;
    @SerializedName("episode_run_time")
    @Expose
    private List<Integer> episodeRunTime;
    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("in_production")
    @Expose
    private Boolean inProduction;
    @SerializedName("languages")
    @Expose
    private List<String> languages;
    @SerializedName("last_air_date")
    @Expose
    private String lastAirDate;
    @SerializedName("last_episode_to_air")
    @Expose
    private Episode lastEpisodeToAir;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("next_episode_to_air")
    @Expose
    private Episode nextEpisodeToAir;
    @SerializedName("networks")
    @Expose
    private List<ProductionCompany> networks;
    @SerializedName("number_of_episodes")
    @Expose
    private Integer numberOfEpisodes;
    @SerializedName("number_of_seasons")
    @Expose
    private Integer numberOfSeasons;
    @SerializedName("origin_country")
    @Expose
    private List<String> originCountry;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_name")
    @Expose
    private String originalName;
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
    @SerializedName("seasons")
    @Expose
    private List<Season> seasons;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> spokenLanguages;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
 */