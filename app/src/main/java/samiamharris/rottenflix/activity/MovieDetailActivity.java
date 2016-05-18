package samiamharris.rottenflix.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import samiamharris.rottenflix.MovieApplication;
import samiamharris.rottenflix.api.retrofit.DataManager;
import samiamharris.rottenflix.api.retrofit.service.MovieService;
import samiamharris.rottenflix.model.Movie;

/**
 * Created by SamMyxer on 4/28/16.
 */
public class MovieDetailActivity extends AppCompatActivity {

    private final static String ID_EXTRA = "moviedetailactivity.id";
    int id;
    Subscription subscription;

    @Inject
    DataManager dataManager;

    public static Intent newIntent(Context context, int id) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(ID_EXTRA, id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieApplication)getApplication()).getComponent().inject(this);

        id = getIntent().getIntExtra(ID_EXTRA, 0);

        MovieService movieService = dataManager.getMovieService();

        subscription =
                movieService.getIndividualMovie(id)
                        .distinct()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<Movie>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("MovieDetailActivity", e.toString());
                            }

                            @Override
                            public void onNext(Movie movie) {
                                Toast.makeText(getBaseContext(),
                                        movie.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }

    }
}
