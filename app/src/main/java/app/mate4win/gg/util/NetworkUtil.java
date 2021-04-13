/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

public class NetworkUtil {
    public static Boolean connectedOrConnecting(Context context) {
        Boolean hasConnectedOrConnecting = false;
        Boolean hasNullPointer = false;
        if(context!=null) {
            try {
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Network nw = cm.getActiveNetwork();
                    if (nw == null) {
                        hasNullPointer = true;
                        return false;
                    }
                    NetworkCapabilities actNw = cm.getNetworkCapabilities(nw);
                    return actNw !=null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
                }else{
                    if(cm!=null && cm.getActiveNetworkInfo()!=null) {
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting())
                            hasConnectedOrConnecting = true;
                        else {
                            hasNullPointer = true;
                            hasConnectedOrConnecting = false;
                        }
                    }else {
                        hasNullPointer = true;
                        hasConnectedOrConnecting = false;
                    }
                }

            }catch (Exception ex){
                hasNullPointer = true;
                hasConnectedOrConnecting = false;
            }
        }
        return hasConnectedOrConnecting;
    }

    public static Boolean isOnline(Context context) {
        Boolean hasConnectedOrConnecting = false;
        Boolean hasNullPointer = false;
        if(context!=null) {
            try {
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Network nw = cm.getActiveNetwork();
                    if (nw == null) {
                        hasNullPointer = true;
                        return false;
                    }
                    NetworkCapabilities actNw = cm.getNetworkCapabilities(nw);
                    return actNw !=null && (actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ||
                            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH));
                }else{
                    if(cm!=null && cm.getActiveNetworkInfo()!=null) {
                        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                        if(activeNetwork != null && activeNetwork.isConnectedOrConnecting())
                            hasConnectedOrConnecting = true;
                        else {
                            hasNullPointer = true;
                            hasConnectedOrConnecting = false;
                        }
                    }else {
                        hasNullPointer = true;
                        hasConnectedOrConnecting = false;
                    }

                }
            }catch (Exception ex){
                hasNullPointer = true;
                hasConnectedOrConnecting = false;
            }
        }
        return hasConnectedOrConnecting;
    }
}