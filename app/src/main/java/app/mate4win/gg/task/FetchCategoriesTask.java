/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/


import android.content.Context;

import app.mate4win.gg.data.FetchCategories;
import app.mate4win.gg.util.Data;

public class FetchCategoriesTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;
    private String platform;

    public FetchCategoriesTask(Context context, String platform, AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        this.context = context;
        this.platform = platform;
    }

    @Override
    public Object call() throws Exception {
        new FetchCategories(context,platform).Call();
        return Data.categories;
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