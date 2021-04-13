/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ContentLoadingProgressBar;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Locale;

import app.mate4win.gg.R;
import app.mate4win.gg.model.Groups;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static app.mate4win.gg.util.Config.isNotNull;

public class ContactView extends BottomSheetDialog {

    public static ContactView Current;

    private View dialogView;
    private Unbinder unbinder;
    public Groups c_groups;

    @BindView(R.id.btn_telegram) LinearLayout btn_telegram;
    @BindView(R.id.btn_discord) LinearLayout btn_discord;
    @BindView(R.id.btn_skype) LinearLayout btn_skype;
    @BindView(R.id.btn_instagram) LinearLayout btn_instagram;

    @BindView(R.id.txt_instagram) TextView txt_instagram;
    @BindView(R.id.txt_skype) TextView txt_skype;
    @BindView(R.id.txt_telegram) TextView txt_telegram;
    @BindView(R.id.txt_discord) TextView txt_discord;



    private SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());


    @OnClick(R.id.btn_cancel) void click_cancel(){
        dismiss();
    }

    public ContactView(@NonNull Context context) {
        super(context);
    }

    public ContactView(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected ContactView(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void initView(Groups group){
        Current = this;
        c_groups = group;

        if(dialogView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialogView = inflater.inflate(R.layout.bs_contact_menu, null);
        }

        unbinder = ButterKnife.bind(this, dialogView);

        if(c_groups!=null && c_groups.getDc_adress()!=null) {
            txt_discord.setText(c_groups.getDc_adress());
            btn_discord.setVisibility(View.VISIBLE);
        }
        if(c_groups!=null && c_groups.getInsta_adress()!=null) {
            txt_instagram.setText(c_groups.getInsta_adress());
            btn_instagram.setVisibility(View.VISIBLE);

        }
        if(c_groups!=null && c_groups.getSkype_adress()!=null) {
            txt_skype.setText(c_groups.getSkype_adress());
            btn_skype.setVisibility(View.VISIBLE);
        }
        if(c_groups!=null && c_groups.getTelegram()!=null) {
            txt_telegram.setText(c_groups.getTelegram());
            btn_telegram.setVisibility(View.VISIBLE);
        }


        setCancelable(true);

        setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    setCancelable(true);
                    dismiss();
                }

                return true;
            }
        });

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialogView = null;
                sdf = null;
                unbinder.unbind();
                Current = null;
                c_groups = null;
            }
        });

        dialogView.setMinimumHeight(getContext().getResources().getDisplayMetrics().heightPixels);

        setContentView(dialogView);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) dialogView.getParent());
        mBehavior.setPeekHeight(getContext().getResources().getDisplayMetrics().heightPixels);

    }
}
