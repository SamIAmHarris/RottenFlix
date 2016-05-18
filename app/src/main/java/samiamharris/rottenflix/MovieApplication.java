package samiamharris.rottenflix;

import android.app.Application;

import samiamharris.rottenflix.api.retrofit.DataManager;
import samiamharris.rottenflix.dagger.ApplicationComponent;
import samiamharris.rottenflix.dagger.ApplicationModule;
import samiamharris.rottenflix.dagger.DaggerApplicationComponent;

/**
 * Created by SamMyxer on 5/17/16.
 */
public class MovieApplication extends Application {

    ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, new DataManager()))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}
