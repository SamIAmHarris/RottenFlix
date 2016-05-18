package samiamharris.rottenflix.util;

import android.content.SharedPreferences;

/**
 * Created by SamMyxer on 5/17/16.
 */
public class SharedPrefManager {

    SharedPreferences prefs;

    public SharedPrefManager(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    public void setMostRecentSearch(String recentSearch) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SPConstants.MOST_RECENT_SEARCH, recentSearch);
        editor.apply();
    }

    public String getMostRecentSearch() {
        return prefs.getString(SPConstants.MOST_RECENT_SEARCH, "No Recent Searches");
    }

    public class SPConstants {
        public static final String MOST_RECENT_SEARCH = "most_recent_search";
    }

}
