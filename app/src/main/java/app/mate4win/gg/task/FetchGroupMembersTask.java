/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;


import android.content.Context;

import app.mate4win.gg.data.FetchGroupMembers;
import app.mate4win.gg.data.FetchNewGroup;
import app.mate4win.gg.data.FetchPending;

public class FetchGroupMembersTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private FetchGroupMembers fetchGroupMembers;
    private String group_id;
    private String member_id;
    private Boolean isDelete;

    public FetchGroupMembersTask(Context context, String group_id, String member_id, Boolean isDelete, AsyncResponse asyncResponse) {
        this.delegate = asyncResponse;
        this.context = context;
        this.group_id = group_id;
        this.member_id = member_id;
        this.isDelete = isDelete;
    }

    @Override
    public Object call() throws Exception {
        fetchGroupMembers = new FetchGroupMembers();
        fetchGroupMembers.Call(context, group_id, member_id, isDelete);
        return fetchGroupMembers;
    }

    @Override
    public void onPostExecute(Object result) {
        delegate.processFinish(result);
    }

}