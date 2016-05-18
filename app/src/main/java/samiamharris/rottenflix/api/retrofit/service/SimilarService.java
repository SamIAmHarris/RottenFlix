package samiamharris.rottenflix.api.retrofit.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import samiamharris.rottenflix.api.gson.BoxOfficeSearchResponse;
import samiamharris.rottenflix.util.Constants;

/**
 * Created by SamMyxer on 4/29/16.
 */
public interface SimilarService {

    @GET("movies/{id}/similar.json?apikey=" + Constants.API_KEY)
    Observable<BoxOfficeSearchResponse>
    getSimilarMovies(@Path("id") int id, @Query("limit") int limit);
}

