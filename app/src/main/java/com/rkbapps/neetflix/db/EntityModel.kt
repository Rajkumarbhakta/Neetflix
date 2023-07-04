package com.rkbapps.neetflix.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "content")
public class EntityModel {

    public static final int MOVIE = 0;
    public static final int SERIES = 1;

    @ColumnInfo(name = "contentId")
    @PrimaryKey(autoGenerate = true)
    private int contentId;
    @ColumnInfo(name = "adult")
    private boolean adult;
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "posterPath")
    private String posterPath;

    @ColumnInfo(name = "releaseDate")
    private String releaseDate;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "voteAverage")
    private double voteAverage;

    @ColumnInfo(name = "type")
    private int type;

    public EntityModel(int type, boolean adult, int id, String posterPath, String releaseDate, String title, double voteAverage) {
        this.type = type;
        this.adult = adult;
        this.id = id;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.voteAverage = voteAverage;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
