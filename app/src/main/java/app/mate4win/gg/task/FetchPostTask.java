/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.data.FetchGroups;
import app.mate4win.gg.data.FetchPost;
import app.mate4win.gg.util.Data;

public class FetchPostTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private String category;

    public FetchPostTask(Context context,String category, AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        this.context = context;
        this.category = category;
    }

    @Override
    public Object call() throws Exception {
        new FetchPost(context,category).Call();
        return Data.groups;
    }

    @Override
    public void onBackground() {
        //
    }

    @Override
    public void onPostExecute(Object result) {
        delegate.processFinish(result);
    }
}