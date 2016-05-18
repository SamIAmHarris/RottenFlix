package samiamharris.rottenflix.dagger;

import javax.inject.Singleton;

import dagger.Component;
import samiamharris.rottenflix.MovieApplication;
import samiamharris.rottenflix.activity.MainActivity;
import samiamharris.rottenflix.activity.MovieDetailActivity;
import samiamharris.rottenflix.activity.ReviewActivity;
import samiamharris.rottenflix.activity.SearchActivity;
import samiamharris.rottenflix.activity.SimilarMovieActivity;

/**
 * Created by SamMyxer on 5/17/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MovieApplication target);
    void inject(MainActivity target);
    void inject(MovieDetailActivity target);
    void inject(ReviewActivity target);
    void inject(SearchActivity target);
    void inject(SimilarMovieActivity target);

}
