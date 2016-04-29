package samiamharris.samlearn.api.retrofit.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import samiamharris.samlearn.api.gson.ReviewResponse;
import samiamharris.samlearn.util.Constants;

/**
 * Created by SamMyxer on 4/28/16.
 */
public interface ReviewService {

    @GET("movies/{id}/reviews.json?apikey=" + Constants.API_KEY)
    Observable<ReviewResponse> getReviews(@Path("id") int id);

}
