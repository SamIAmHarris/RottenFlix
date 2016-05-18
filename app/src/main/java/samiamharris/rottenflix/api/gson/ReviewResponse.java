package samiamharris.rottenflix.api.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rx.Observable;
import samiamharris.rottenflix.model.Review;

/**
 * Created by SamMyxer on 4/28/16.
 */
public class ReviewResponse {

    @SerializedName("reviews")
    List<Review> reviewsList;

    public Observable<List<Review>> getReviewsAsObservable() {
        return Observable.just(reviewsList);
    }

    public List<Review> getReviewsList() {
        return reviewsList;
    }
}
