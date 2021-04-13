/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.data.persistentCookie.cache;

import java.util.Collection;

import okhttp3.Cookie;

public interface CookieCache extends Iterable<Cookie> {

    /**
     * Add all the new cookies to the session, existing cookies will be overwritten.
     *
     * @param cookies
     */
    void addAll(Collection<Cookie> cookies);

    /**
     * Clear all the cookies from the session.
     */
    void clear();
}
