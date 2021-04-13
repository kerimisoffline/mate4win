/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

import android.content.Context;

import app.mate4win.gg.data.FetchCategories;
import app.mate4win.gg.data.FetchMainCategories;
import app.mate4win.gg.util.Data;

public class FetchMainCategoriesTask extends BaseTask {

    private AsyncResponse delegate = null;
    private Context context;

    public FetchMainCategoriesTask(Context context, AsyncResponse asyncResponse) {
        delegate = asyncResponse;
        this.context = context;
    }

    @Override
    public Object call() throws Exception {
        new FetchMainCategories(context).Call();
        return Data.mainCategories;
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