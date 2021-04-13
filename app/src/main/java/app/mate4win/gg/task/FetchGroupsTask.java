/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.data.FetchCategories;
import app.mate4win.gg.data.FetchGroups;
import app.mate4win.gg.util.Data;

public class FetchGroupsTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private String member_id;

    public FetchGroupsTask(Context context, String member_id,AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        this.context = context;
        this.member_id = member_id;
    }

    @Override
    public Object call() throws Exception {
        new FetchGroups(context,member_id).Call();
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