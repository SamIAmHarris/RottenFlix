package samiamharris.rottenflix.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class Movie {
    int id;
    String title;
    int year;
    @SerializedName("mpaa_rating")
    String mpaaRating;
    String runtime;
    @SerializedName("critics_consensus")
    String criticsConsensus;
    String synopsis;
    Ratings ratings;
    ReleaseDates releaseDates;
    @SerializedName("abridged_cast")
    ArrayList<CastMember> abridgedCast;
    Posters posters;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getCriticsConsensus() {
        return criticsConsensus;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public ReleaseDates getReleaseDates() {
        return releaseDates;
    }

    public ArrayList<CastMember> getAbridgedCast() {
        return abridgedCast;
    }

    public Posters getPosters() {
        return posters;
    }
}
