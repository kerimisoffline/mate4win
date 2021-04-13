/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.R;
import app.mate4win.gg.data.MemberLogin;
import app.mate4win.gg.util.NetworkUtil;

public class MemberLoginTask extends BaseTask{

    private AsyncResponse delegate = null;
    private Context context;
    private String email;
    private String password;

    public MemberLoginTask(Context context, String email, String password,AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        this.context = context;
        this.email = email;
        this.password = password;
    }

    @Override
    public Object call() throws Exception {
        MemberLogin login = new MemberLogin();
        login.Call(context, email, password);
        return NetworkUtil.isOnline(context) ? login.getErrorMessage() : context.getResources().getString(R.string.connection);
    }

    @Override
    public void onPostExecute(Object result) {
        delegate.processFinish(result);
    }
}
