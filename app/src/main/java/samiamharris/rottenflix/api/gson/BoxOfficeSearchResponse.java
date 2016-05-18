package samiamharris.rottenflix.api.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rx.Observable;
import samiamharris.rottenflix.model.Movie;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class BoxOfficeSearchResponse {

    @SerializedName("movies")
    List<Movie> boxOfficeList;

    public Observable<List<Movie>> getBoxOfficeMoviesObservable() {
        return Observable.just(boxOfficeList);
    }

    public List<Movie> getBoxOfficeMovies() {
        return boxOfficeList;
    }
}
