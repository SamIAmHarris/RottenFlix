package samiamharris.samlearn.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class Ratings {

    @SerializedName("critics_rating")
    String criticsRating;
    @SerializedName("critics_score")
    int criticsScore;
    @SerializedName("audience_rating")
    String audienceRating;
    @SerializedName("audience_score")
    int audeinceScore;
}
