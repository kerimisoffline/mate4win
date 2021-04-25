/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.data.FetchNewGroup;

public class NewGroupTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private FetchNewGroup fetchNewGroup;
    private String title;
    private String sub_title;
    private String platform;
    private String category;
    private String email;
    private String telegram;
    private String discord;
    private String skype;
    private String instagram;

    public NewGroupTask(Context context, String title, String sub_title, String platform, String category, String email, String telegram, String discord, String skype, String instagram, AsyncResponse asyncResponse) {
        this.delegate = asyncResponse;
        this.context = context;
        this.title = title;
        this.sub_title = sub_title;
        this.platform = platform;
        this.category = category;
        this.email = email;
        this.telegram = telegram;
        this.discord = discord;
        this.skype = skype;
        this.instagram = instagram;
    }

    @Override
    public Object call() throws Exception {
        fetchNewGroup = new FetchNewGroup();
        fetchNewGroup.Call(context, title, sub_title, platform, category, email, telegram, discord, skype, instagram);
        return fetchNewGroup;
    }

    @Override
    public void onPostExecute(Object result) {
        delegate.processFinish(result);
    }

}