package samiamharris.rottenflix.api.retrofit.service;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import samiamharris.rottenflix.model.Movie;
import samiamharris.rottenflix.util.Constants;

/**
 * Created by SamMyxer on 4/28/16.
 */
public interface MovieService {

    @GET("movies/{id}.json?apikey=" + Constants.API_KEY)
    Observable<Movie> getIndividualMovie(@Path("id") int id);

}
