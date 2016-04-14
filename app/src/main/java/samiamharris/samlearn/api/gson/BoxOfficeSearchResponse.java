package samiamharris.samlearn.api.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import samiamharris.samlearn.model.Movie;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class BoxOfficeSearchResponse {

    @SerializedName("movies")
    List<Movie> boxOfficeList;

    public List<Movie> getBoxOfficeMovies() {
        return boxOfficeList;
    }
}
