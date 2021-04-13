/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.mate4win.gg.Mate4WinApplication;
import app.mate4win.gg.task.TaskRunner;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    public Unbinder unbinder;
    private TaskRunner taskRunner;
    public int uiMode;
    public Mate4WinApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (Mate4WinApplication) this.getApplication();
        setContentView(setActivityIdentifier());
        uiMode = getResources().getConfiguration().uiMode;
        unbinder = ButterKnife.bind(this);
        create();
    }

    public int orientation() {
        return getResources().getConfiguration().orientation;
    }

    protected abstract int setActivityIdentifier();

    protected void create() {
    }

    protected void resume() {
    }

    protected void changeConfig() {
    }

    protected void changeUiMode() {
    }

    @Override
    public void onResume() {
        super.onResume();
        //application.setCurrentActivity(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //clearReferences();
        unbinder.unbind();
        System.gc();
    }

    public TaskRunner getRunner(){
        if(taskRunner == null)
            taskRunner = new TaskRunner();

        return taskRunner;
    }

    private void clearReferences() {
        AppCompatActivity currActivity = application.getCurrentActivity();
        if (this.equals(currActivity))
            application.setCurrentActivity(null);
    }

}
