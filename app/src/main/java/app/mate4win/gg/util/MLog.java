/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.util;

import android.util.Log;

public class MLog {

    public static final String TAG = "Mate4Win";
    public static final Boolean has_log = false;

    public static void setLog(String message){
        if(!has_log || message == null || message.equals(""))
            return;

        Log.d(TAG, message);
    }
}
