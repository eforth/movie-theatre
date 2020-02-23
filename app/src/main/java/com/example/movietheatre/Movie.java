package com.example.movietheatre;

import android.graphics.drawable.Drawable;

import org.json.JSONObject;

public class Movie {

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String plot;
    private String poster;
    private String IMDBRating;
    private String IMDBID;
    private Drawable drawable;
    private boolean isShowing;

    public Movie(String title, String year, String rated,
                 String released, String runtime, String plot,
                 String poster, String IMDBRating, String IMDBID,
                 boolean isShowing) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.plot = plot;
        this.poster = poster;
        this.IMDBRating = IMDBRating;
        this.IMDBID = IMDBID;
        this.isShowing = isShowing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getIMDBRating() {
        return IMDBRating;
    }

    public void setIMDBRating(String IMDBRating) {
        this.IMDBRating = IMDBRating;
    }

    public String getIMDBID() {
        return IMDBID;
    }

    public void setIMDBID(String IMDBID) {
        this.IMDBID = IMDBID;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public boolean isShowing() {
        return isShowing;
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }

    public static Movie fromJSON(JSONObject obj, boolean showing) {

        if (obj == null) return null;

        return new Movie(
                obj.optString("Title"),
                obj.optString("Year"),
                obj.optString("Rated"),
                obj.optString("Released"),
                obj.optString("Runtime"),
                obj.optString("Plot"),
                obj.optString("Poster"),
                obj.optString("imdbRating"),
                obj.optString("imdbID"),
                showing
        );
    }
}
