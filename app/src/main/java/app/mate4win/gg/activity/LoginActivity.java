/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import app.mate4win.gg.R;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.MemberLoggedTask;
import app.mate4win.gg.task.MemberLoginTask;
import app.mate4win.gg.util.Config;
import app.mate4win.gg.util.Data;
import butterknife.BindView;

import static app.mate4win.gg.util.Config.AlertDialog;
import static app.mate4win.gg.util.Config.isNotNull;
import static app.mate4win.gg.util.Config.progressDialogMessage;

public class LoginActivity extends BaseActivity {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @BindView(R.id.edt_email)
    AppCompatEditText edt_email;
    @BindView(R.id.edt_pass) AppCompatEditText edt_password;

    @Override
    protected int setActivityIdentifier() {
        return R.layout.activity_login;
    }

    public void forgot_password(View view) {
    }
    public void next(View view) {
        String email = edt_email.getText() !=null ? edt_email.getText().toString() : null;
        String password = edt_password.getText() !=null ? edt_password.getText().toString() : null;

        Config.progressDialogMessage(this,"...");
        getRunner().executeAsync(new MemberLoginTask(this, email, password, new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                String out = (String) output;
                if(!isNotNull(out)){
                    getRunner().executeAsync(new MemberLoggedTask(LoginActivity.this, email,new AsyncResponse() {
                        @Override
                        public void processFinish(Object output) {
                            String out = (String) output;
                            if(isNotNull(out)){
                                progressDialogMessage(null,null);
                                AlertDialog(LoginActivity.this,"",out);
                            } else if (Data.member!=null) {
                                settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                editor = settings.edit();
                                editor.remove("login");
                                editor.apply();
                                editor.putString("login", email);
                                editor.apply();

                                finish();

                                Intent mainIntent = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(mainIntent);

                                progressDialogMessage(null, null);
                            } else {
                                progressDialogMessage(null, null);

                            }
                        }
                    }));
                } else {
                    progressDialogMessage(null, null);
                    AlertDialog(LoginActivity.this,"",out);
                }
            }
        }));
    }
}
