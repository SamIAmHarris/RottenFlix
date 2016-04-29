package samiamharris.samlearn.api.retrofit.service;

import retrofit2.Call;
import retrofit2.http.GET;
import samiamharris.samlearn.api.gson.BoxOfficeSearchResponse;
import samiamharris.samlearn.util.Constants;

/**
 * Created by SamMyxer on 4/14/16.
 */
public interface BoxOfficeService {
    @GET("lists/movies/box_office.json?apikey=" + Constants.API_KEY)
    Call<BoxOfficeSearchResponse> getBoxOfficeMovies();

}
