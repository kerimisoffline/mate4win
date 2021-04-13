/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import app.mate4win.gg.R;
import app.mate4win.gg.model.Cache;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.MemberLoggedTask;
import app.mate4win.gg.util.Config;
import app.mate4win.gg.util.Data;
import butterknife.BindView;

public class SplashActivity extends BaseActivity {

    @Override
    protected int setActivityIdentifier() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String check_logged = checkLogged();
        if(check_logged!=null) {
            Config.progressDialogMessage(SplashActivity.this,"...");
            getRunner().executeAsync(new MemberLoggedTask(SplashActivity.this, check_logged, new AsyncResponse() {
                @Override
                public void processFinish(Object output) {
                    Config.progressDialogMessage(null,null);
                    route();
                }
            }));
        }
        else
            route();
    }

    private void route(){
        startActivity(new Intent(SplashActivity.this, Data.member != null ? MainActivity.class : LoginActivity.class));

    }

    private String checkLogged() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        String json = settings.getString("login", null);
        return json;
    }
}
