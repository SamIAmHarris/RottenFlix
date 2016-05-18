package samiamharris.rottenflix.dagger;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import samiamharris.rottenflix.api.retrofit.DataManager;
import samiamharris.rottenflix.util.SharedPrefManager;

/**
 * Created by SamMyxer on 5/17/16.
 */
@Module
public class ApplicationModule {

    private Application application;
    private DataManager dataManager;
    private SharedPrefManager sharedPrefManager;

    public ApplicationModule(Application application, DataManager dataManager) {
        this.application = application;
        this.dataManager = dataManager;
        this.sharedPrefManager = new SharedPrefManager(
                PreferenceManager.getDefaultSharedPreferences(application));
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public DataManager provideDataManager() {
        return dataManager;
    }

    @Provides
    @Singleton
    public SharedPrefManager provideSharedPrefManager() {
        return sharedPrefManager;
    }

}
