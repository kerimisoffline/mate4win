/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.data.DeleteGroup;
import app.mate4win.gg.data.FetchNewGroup;

public class DeleteGroupTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private DeleteGroup deleteGroup;
    private String group_id;

    public DeleteGroupTask(Context context, String group_id, AsyncResponse asyncResponse) {
        this.delegate = asyncResponse;
        this.context = context;
        this.group_id = group_id;
    }

    @Override
    public Object call() throws Exception {
        deleteGroup = new DeleteGroup();
        deleteGroup.Call(context, group_id);
        return deleteGroup;
    }

    @Override
    public void onPostExecute(Object result) {
        delegate.processFinish(result);
    }

}