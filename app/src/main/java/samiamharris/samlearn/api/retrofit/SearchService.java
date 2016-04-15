package samiamharris.samlearn.api.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import samiamharris.samlearn.api.gson.BoxOfficeSearchResponse;
import samiamharris.samlearn.model.Movie;
import samiamharris.samlearn.util.Constants;

/**
 * Created by SamMyxer on 4/15/16.
 */
public interface SearchService {

    @GET("movies.json?apikey=" + Constants.API_KEY)
    Call<BoxOfficeSearchResponse>
    searchMovies(@Query("q") String query, @Query("page_limit") int pageLimit);

}
