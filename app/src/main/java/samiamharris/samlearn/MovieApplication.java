package samiamharris.samlearn;

import android.app.Application;

import samiamharris.samlearn.api.retrofit.DataManager;
import samiamharris.samlearn.dagger.ApplicationComponent;
import samiamharris.samlearn.dagger.ApplicationModule;
import samiamharris.samlearn.dagger.DaggerApplicationComponent;

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
