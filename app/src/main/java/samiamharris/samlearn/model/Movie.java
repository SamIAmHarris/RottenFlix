package samiamharris.samlearn.model;

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
    int runtime;
    @SerializedName("critics_consensus")
    String criticsConsensus;
    String synopsis;
    Ratings ratings;
    ReleaseDates releaseDates;
    @SerializedName("abridged_cast")
    ArrayList<CastMember> abridgedCast;
    Posters posters;
}
