/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.task;


/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import java.util.concurrent.Callable;

public interface CustomCallable<R> extends Callable<R> {
    void onPostExecute(R result);
    void onBackground();
}
