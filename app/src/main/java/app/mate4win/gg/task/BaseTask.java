/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;

public abstract class BaseTask<R> implements CustomCallable<R> {
    @Override
    public void onBackground() {

    }

    @Override
    public void onPostExecute(R result) {

    }

    @Override
    public R call() throws Exception {
        return null;
    }
}
