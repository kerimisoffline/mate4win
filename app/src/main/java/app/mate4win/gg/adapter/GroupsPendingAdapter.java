/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.adapter;

import android.content.Context;
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
import app.mate4win.gg.model.Member;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchGroupMembersTask;
import app.mate4win.gg.task.FetchPendingTask;
import app.mate4win.gg.task.TaskRunner;
import app.mate4win.gg.ui.groups.GroupsPendingFragment;
import app.mate4win.gg.util.Data;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.selectedGroups;

public class GroupsPendingAdapter extends RecyclerView.Adapter<GroupsPendingAdapter.ViewHolder> {

    private Context context;
    private Boolean desc_visible = false;
    private List<Member> items;

    public GroupsPendingAdapter(Context context) {
        this.context = context;
    }

    public void UpdateData(List<Member> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout.LayoutParams layoutParams;

        @BindView(R.id.txt_pending) TextView txt_pending;
        @BindView(R.id.btn_apply) AppCompatImageButton btn_apply;
        @BindView(R.id.btn_cancel) AppCompatImageButton btn_cancel;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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
            holder.txt_pending.setText(item.getNick_name());
            Member finalItem = item;

            holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedGroups!=null && selectedGroups.getId()!=null){
                        progressDialogMessage(context, "...");
                        if(Data.member!=null) {
                            new TaskRunner().executeAsync(new FetchPendingTask(context, selectedGroups.getId(), finalItem.getId(),true, new AsyncResponse() {
                                @Override
                                public void processFinish(Object output) {
                                    progressDialogMessage(null, null);

                                    if(GroupsPendingFragment.fragment!=null)
                                        GroupsPendingFragment.fragment.notifyData();
                                }
                            }));
                        }
                    }

                }
            });

            holder.btn_apply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedGroups!=null && selectedGroups.getId()!=null){
                        progressDialogMessage(context, "...");
                        if(Data.member!=null) {
                            new TaskRunner().executeAsync(new FetchGroupMembersTask(context, selectedGroups.getId(), finalItem.getId(),false, new AsyncResponse() {
                                @Override
                                public void processFinish(Object output) {
                                    new TaskRunner().executeAsync(new FetchPendingTask(context, selectedGroups.getId(), finalItem.getId(),true, new AsyncResponse() {
                                        @Override
                                        public void processFinish(Object output) {
                                            progressDialogMessage(null, null);

                                            if(GroupsPendingFragment.fragment!=null)
                                                GroupsPendingFragment.fragment.notifyData();
                                        }
                                    }));
                                }
                            }));
                        }
                    }

                }
            });

        }

        item = null;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
