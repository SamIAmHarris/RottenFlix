package samiamharris.samlearn.dagger;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import samiamharris.samlearn.api.retrofit.DataManager;

/**
 * Created by SamMyxer on 5/17/16.
 */
@Module
public class ApplicationModule {

    private Application application;
    private DataManager dataManager;

    public ApplicationModule(Application application, DataManager dataManager) {
        this.application = application;
        this.dataManager = dataManager;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    public DataManager provideDataManager() {
        return dataManager;
    }

}
