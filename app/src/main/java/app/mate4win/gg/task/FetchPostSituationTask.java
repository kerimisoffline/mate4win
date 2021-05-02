/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.data.FetchNewGroup;
import app.mate4win.gg.data.FetchPost;
import app.mate4win.gg.data.FetchPostSituation;
import app.mate4win.gg.util.Data;

public class FetchPostSituationTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private String group_id;
    private Boolean situation;
    private FetchPostSituation fetchPostSituation;


    public FetchPostSituationTask(Context context,String group_id, Boolean situation, AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        this.context = context;
        this.group_id = group_id;
        this.situation = situation;
    }

    @Override
    public Object call() throws Exception {
        fetchPostSituation =  new FetchPostSituation();
        fetchPostSituation.Call(context,group_id,situation);
        return fetchPostSituation;
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