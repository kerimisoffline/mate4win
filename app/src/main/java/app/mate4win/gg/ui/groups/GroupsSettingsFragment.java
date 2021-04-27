/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.FrameLayout;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.data.DeleteGroup;
import app.mate4win.gg.data.FetchNewGroup;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.DeleteGroupTask;
import app.mate4win.gg.task.NewGroupTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.Config;
import app.mate4win.gg.util.Data;
import butterknife.BindView;

public class GroupsSettingsFragment extends BaseFragment {

    public static GroupsSettingsFragment fragment;

    @BindView(R.id.delete_group) FrameLayout dlt_group;

    public GroupsSettingsFragment(){
        layoutId = R.layout.fragment_group_settings;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        dlt_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getResources().getString(R.string.sure_delete))
                        .setCancelable(false)
                        .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Data.selectedGroups!=null){
                                    Config.progressDialogMessage(getContext(), "...");
                                    getRunner().executeAsync(new DeleteGroupTask(getContext(),Data.selectedGroups.getId() , new AsyncResponse() {
                                        @Override
                                        public void processFinish(Object output) {
                                            Config.progressDialogMessage(null, null);
                                            DeleteGroup out = (DeleteGroup) output;
                                            if (out == null)
                                                return;
                                            if (Config.isNotNull(out.getSuccessMessage())) {
                                                Config.AlertDialog(getContext(), "", out.getSuccessMessage());
                                                if (MainActivity.Current != null)
                                                    MainActivity.Current.Navigate(R.id.navigation_groups, null, null, null);

                                            } else if (Config.isNotNull(out.getErrorMessage())) {
                                                Config.AlertDialog(getContext(), "", out.getErrorMessage());
                                            }
                                        }
                                    }));
                                }
                                if(MainActivity.Current!=null)
                                    MainActivity.Current.Navigate(R.id.navigation_groups,null,null,null);
                            }
                        })
                        .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}