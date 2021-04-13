/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Cache {
    public final static String KEY_APP_DETAILS = "app_details";
    public final static String KEY_HOMES = "home_layout";
    public final static String KEY_POSTS = "posts";
    public final static String KEY_LOGGED_MEMBER = "logged_member";
    public final static String KEY_CATEGORIES = "categories";
    public final static String KEY_MAINCATEGORY = "main_category";
    public final static String KEY_BOOKMARKS = "bookmarks";
    public final static String KEY_SEARCH = "search_"; //phrase
    public final static String KEY_LOG_LISTENING = "log_listening";
    public final static String KEY_NOTIFICATION = "notifications";

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public Cache(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setCache(String key, String value){
        editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getCache(String key){
        return preferences.getString(key, null);
    }

}
