package samiamharris.rottenflix.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import samiamharris.rottenflix.api.retrofit.DataManager;

/**
 * Created by SamMyxer on 6/5/16.
 */
@Module
public class DataModule {

    private DataManager dataManager;

    public DataModule(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Provides
    @Singleton
    public DataManager provideDataManager() {
        return dataManager;
    }

}
