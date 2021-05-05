/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.model.Groups;
import app.mate4win.gg.model.Member;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchGroupMembersTask;
import app.mate4win.gg.task.FetchGroupsTask;
import app.mate4win.gg.task.FetchPendingTask;
import app.mate4win.gg.task.TaskRunner;
import app.mate4win.gg.ui.groups.GroupsPendingFragment;
import app.mate4win.gg.util.Data;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.concurrent.Task;

import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.groups;
import static app.mate4win.gg.util.Data.member;
import static app.mate4win.gg.util.Data.selectedGroups;

public class GroupsMembersAdapter extends RecyclerView.Adapter<GroupsMembersAdapter.ViewHolder> {

    private Context context;
    private List<Member> items;

    public GroupsMembersAdapter(Context context) {
        this.context = context;
    }

    public void UpdateData(List<Member> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout.LayoutParams layoutParams;

        @BindView(R.id.txt_pending) TextView txt_member;
        @BindView(R.id.btn_apply) AppCompatImageButton btn_apply;
        @BindView(R.id.btn_cancel) AppCompatImageButton btn_cancel;

        @BindString(R.string.sure_member_delete) String sure_delete;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            btn_apply.setVisibility(View.GONE);
        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_pending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        Member item = items.get(position);
        if(item != null) {
            holder.txt_member.setText(item.getNick_name());
            Member finalItem = item;

            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(finalItem.getId().equals(member.getId()) ? context.getResources().getString(R.string.sure_owner_delete) : context.getResources().getString(R.string.sure_member_delete,finalItem.getNick_name()))
                            .setCancelable(false)
                            .setPositiveButton(context.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(selectedGroups!=null && selectedGroups.getId()!=null){
                                        progressDialogMessage(context, "...");
                                        if(Data.member!=null) {
                                            new TaskRunner().executeAsync(new FetchGroupMembersTask(context, selectedGroups.getId(), finalItem.getId(),true, new AsyncResponse() {
                                                @Override
                                                public void processFinish(Object output) {
                                                    progressDialogMessage(null, null);

                                                    if(GroupsPendingFragment.fragment!=null)
                                                        GroupsPendingFragment.fragment.notifyData();
                                                }
                                            }));
                                        }
                                    }
                                    fetchGroupTask();
                                }
                            })
                            .setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                    alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.black));
                    alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.black));
                }
            });

        }

        item = null;
    }

    private void fetchGroupTask(){
        progressDialogMessage(context, "...");
        if(Data.member!=null) {
             new TaskRunner().executeAsync(new FetchGroupsTask(context,Data.member.getId(), new AsyncResponse() {
                @Override
                public void processFinish(Object output) {
                    progressDialogMessage(null, null);
                    if(groups!=null)
                        if(selectedGroups!=null){
                            for(Groups g: groups)
                                if(g.getId().equals(selectedGroups.getId()))
                                    selectedGroups = g;
                        }
                    if(MainActivity.Current!=null)
                        MainActivity.Current.Navigate(R.id.navigation_groups_settings,null,null,null);
                }
            }));
        }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
