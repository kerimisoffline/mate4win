/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.util;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import app.mate4win.gg.R;
import app.mate4win.gg.ui.view.NoConnectionView;

import static android.content.Context.ACTIVITY_SERVICE;

public class Config {

    public static LoadImage loadImage;
    private static Dialog progressDialogMessages = null;

    public static void progressDialogMessage(Context context, String message) {
        try {
            if (progressDialogMessages != null) {
                progressDialogMessages.dismiss();
                progressDialogMessages = null;
            }

            /*if (context!=null && isInForeground(context) && progressDialogMessages == null && message != null) {
                progressDialogMessages = new ProgressDialog(context);
                progressDialogMessages.setMessage(message);
                progressDialogMessages.setCancelable(false);

                progressDialogMessages.show();
            }*/

            if (context != null && progressDialogMessages == null && message != null) {
                progressDialogMessages = new Dialog(context);
                progressDialogMessages.setContentView(R.layout.loader);
                progressDialogMessages.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                progressDialogMessages.show();
            }

            context = null;
            message = null;
        } catch (Exception ex) {
            //
        }
    }

    public static void AlertDialog(Context context, String title, String message){
        if(context!=null && isInForeground(context) && (isNotNull(title) || isNotNull(message))) {
            if(isNotNull(message) && message.equals(context.getResources().getString(R.string.connection))){
                NoConnectionView noConnectionView = new NoConnectionView(context, R.style.TransparentBottomSheetDialog);
                noConnectionView.createDialog();
            }else {
                final AlertDialog.Builder builder =
                        new AlertDialog.Builder(context).
                                setTitle(isNotNull(title) ? title : "").
                                setMessage(message).
                                setPositiveButton(context.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    public static boolean isInForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                    && appProcess.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotNull(String str) {
        return str != null && !str.trim().equals("") && !str.trim().equals("null") && !str.trim().equals("[]");
    }

    public static String rtrim(String s) {
        if(s!=null) {
            int i = s.length() - 1;
            while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
                i--;
            }
            return s.substring(0, i + 1);
        }else{
            return "";
        }
    }

    public static String ltrim(String s) {
        if(s!=null) {
            int i = 0;
            while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
                System.out.println("s.charAt(i)  " + s.charAt(i));
                i++;
            }
            return s.substring(i);
        }else{
            return "";
        }
    }

}
