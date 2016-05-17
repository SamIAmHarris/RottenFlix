package samiamharris.samlearn.dagger;

import javax.inject.Singleton;

import dagger.Component;
import samiamharris.samlearn.MovieApplication;
import samiamharris.samlearn.activity.MainActivity;

/**
 * Created by SamMyxer on 5/17/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MovieApplication target);
    void inject(MainActivity target);
}
