/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.profile;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.LoginActivity;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.activity.SplashActivity;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.Config;
import app.mate4win.gg.util.Data;
import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static app.mate4win.gg.util.Data.member;


public class ProfileFragment extends BaseFragment {

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @BindView(R.id.img_filter) ImageView img_filter;
    @BindView(R.id.img_user) ImageView img_user;
    @BindView(R.id.txt_name) TextView txt_name;
    @BindView(R.id.txt_mail) TextView txt_mail;
    @BindView(R.id.txt_dc) TextView txt_dc;
    @BindView(R.id.txt_phone) TextView txt_phone;



    public static ProfileFragment fragment;

    public ProfileFragment(){
        layoutId = R.layout.fragment_profile;
    }

    @OnClick(R.id.btn_logout) void click_sign_out(){
        restart();
    }

    @Override
    protected void viewRef(View view) {
    }

    @Override
    protected void handler() {
        if(member!=null){
            String name = member.getFirst_name() + " '" + member.getNick_name() + "' " + member.getLast_name();
            txt_name.setText(name);
            if(member.getEmail()!=null)
                txt_mail.setText(member.getEmail());
            if(member.getDc_adress()!=null)
                txt_dc.setText(member.getDc_adress());
            if(member.getMobile_number()!=null)
                txt_phone.setText(member.getMobile_number());
        }
    }

    private void restart(){
        Data.member=null;
        settings = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = settings.edit();
        editor.remove("login");
        editor.apply();

        startActivity(new Intent(getContext(),LoginActivity.class));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}