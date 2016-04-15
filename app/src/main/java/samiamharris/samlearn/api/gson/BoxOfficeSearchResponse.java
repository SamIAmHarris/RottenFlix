package samiamharris.samlearn.api.gson;

import android.databinding.ObservableList;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rx.Observable;
import samiamharris.samlearn.model.Movie;

/**
 * Created by SamMyxer on 4/14/16.
 */
public class BoxOfficeSearchResponse {

    @SerializedName("movies")
    List<Movie> boxOfficeList;

    public Observable<List<Movie>> getBoxOfficeMovies() {
        return Observable.just(boxOfficeList);
    }
}
