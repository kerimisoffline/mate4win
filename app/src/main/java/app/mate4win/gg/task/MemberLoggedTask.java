/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.data.MemberLogged;

public class MemberLoggedTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private String email;
    private MemberLogged memberLogged;

    public MemberLoggedTask(Context context, String email, AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        this.email = email;
        this.context = context;
    }

    @Override
    public Object call() throws Exception {
        memberLogged = new MemberLogged();
        memberLogged.Call(context,email);
        return memberLogged.getErrorMessage();
    }

    @Override
    public void onPostExecute(Object result) {
        delegate.processFinish(result);
    }

}
