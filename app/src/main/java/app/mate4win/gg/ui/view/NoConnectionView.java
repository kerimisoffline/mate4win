/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import app.mate4win.gg.R;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class NoConnectionView extends Dialog {
    private View dialogView;
    private Unbinder unbinder;
    private CountDownTimer timer;

    @OnClick(R.id.btn_ok) void click_ok(){
        dismiss();
    }

    public NoConnectionView(@NonNull Context context) {
        super(context);
    }

    public NoConnectionView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NoConnectionView(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void createDialog(){
        if(dialogView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            dialogView = inflater.inflate(R.layout.bs_no_connection, null);
        }

        unbinder = ButterKnife.bind(this, dialogView);

        if(timer != null)
            timer.cancel();
        timer = null;
        timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(timer != null){
                    timer = null;
                    dismiss();
                }
            }
        }.start();

        setContentView(dialogView);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(true);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                unbinder.unbind();
                dialogView = null;
                unbinder = null;
                if(timer != null)
                    timer.cancel();
                timer = null;
            }
        });

        show();
    }
}
