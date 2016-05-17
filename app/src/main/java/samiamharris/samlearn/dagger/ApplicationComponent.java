package samiamharris.samlearn.dagger;

import javax.inject.Singleton;

import dagger.Component;
import samiamharris.samlearn.MovieApplication;
import samiamharris.samlearn.activity.MainActivity;
import samiamharris.samlearn.activity.MovieDetailActivity;
import samiamharris.samlearn.activity.ReviewActivity;
import samiamharris.samlearn.activity.SearchActivity;
import samiamharris.samlearn.activity.SimilarMovieActivity;

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
