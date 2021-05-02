/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.switchmaterial.SwitchMaterial;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.data.DeleteGroup;
import app.mate4win.gg.data.FetchNewGroup;
import app.mate4win.gg.data.FetchPostSituation;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.DeleteGroupTask;
import app.mate4win.gg.task.FetchPostSituationTask;
import app.mate4win.gg.task.NewGroupTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.Config;
import app.mate4win.gg.util.Data;
import butterknife.BindView;

import static app.mate4win.gg.util.Config.progressDialogMessage;

public class GroupsSettingsFragment extends BaseFragment {

    public static GroupsSettingsFragment fragment;
    private boolean isChecked = false;

    @BindView(R.id.delete_group) FrameLayout dlt_group;
    @BindView(R.id.switch_post) SwitchMaterial switch_post;

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

        isChecked = (Data.selectedGroups != null && Data.selectedGroups.getSituation().equals("1"));
        switch_post.setChecked(isChecked);

        if(Data.selectedGroups!=null) {
            switch_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialogMessage(getContext(), "...");
                    getRunner().executeAsync(new FetchPostSituationTask(getContext(), Data.selectedGroups.getId(), !isChecked, new AsyncResponse() {
                        @Override
                        public void processFinish(Object output) {
                            Config.progressDialogMessage(null, null);
                            FetchPostSituation out = (FetchPostSituation) output;
                            if (out == null)
                                return;
                            if (Config.isNotNull(out.getSuccessMessage())) {
                                Config.AlertDialog(getContext(), "", out.getSuccessMessage());

                            } else if (Config.isNotNull(out.getErrorMessage())) {
                                Config.AlertDialog(getContext(), "", out.getErrorMessage());
                            }
                        }
                    }));
                }
            });
        }


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
                                    progressDialogMessage(getContext(), "...");
                                    getRunner().executeAsync(new DeleteGroupTask(getContext(),Data.selectedGroups.getId() , new AsyncResponse() {
                                        @Override
                                        public void processFinish(Object output) {
                                            progressDialogMessage(null, null);
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