/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import app.mate4win.gg.data.persistentCookie.PersistentCookieJar;
import app.mate4win.gg.data.persistentCookie.cache.SetCookieCache;
import app.mate4win.gg.data.persistentCookie.persistence.SharedPrefsCookiePersistor;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

public class DataConfig {

    public static String serviceURL = "http://192.168.1.103:8888/read.php";

    public static String baseURL = "http://192.168.1.103:8888/";

    public static int call_count = 10;

    public static Boolean hasHttpsService = false;

    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient httpclient;

    public static int CallCount = 10;

    public static OkHttpClient getClient(final Context context) {
        if(okHttpClient == null) {

            CookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));

            int cacheSize = 10 * 1024 * 1024;

            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .allEnabledTlsVersions()
                    .allEnabledCipherSuites()
                    .build();

            if (hasHttpsService) {
                okHttpClient = new OkHttpClient.Builder()
                        .cache(new Cache(getDirectory(), Long.parseLong(String.valueOf(cacheSize))))
                        .retryOnConnectionFailure(true)
                        .connectionSpecs(Collections.singletonList(spec))
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .cookieJar(cookieJar)
                        .build();
            } else {
                okHttpClient = new OkHttpClient.Builder()
                        .cache(new Cache(getDirectory(), Long.parseLong(String.valueOf(cacheSize))))
                        .retryOnConnectionFailure(true)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .cookieJar(cookieJar)
                        .build();
            }
        }

        return okHttpClient;

    }

    public static File getDirectory() {
        final File root = new File(Environment.getDataDirectory().getPath() + File.separator + "UCC" + File.separator);
        root.mkdirs();
        final String fname = "tmp";
        final File sdImageMainDirectory = new File(root, fname);
        return sdImageMainDirectory;
    }

}
