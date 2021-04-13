/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui;


/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import app.mate4win.gg.task.TaskRunner;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseFragment extends Fragment {
    protected int layoutId;
    protected View view;
    Unbinder unbinder;
    public Boolean isPortrait = false;
    private TaskRunner taskRunner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(layoutId, container, false);
        unbinder = ButterKnife.bind(this, view);
        isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        setRetainInstance(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        viewRef(view);
        handler();
    }

    protected void viewRef(View view){

    }

    protected void handler(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        System.gc();
    }

    public TaskRunner getRunner(){
        if(taskRunner == null)
            taskRunner = new TaskRunner();
        return taskRunner;
    }
}
