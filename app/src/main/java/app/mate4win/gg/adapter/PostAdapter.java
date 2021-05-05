/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import app.mate4win.gg.R;
import app.mate4win.gg.model.Groups;
import app.mate4win.gg.model.Member;
import app.mate4win.gg.model.Post;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchGroupsTask;
import app.mate4win.gg.task.FetchPendingTask;
import app.mate4win.gg.task.TaskRunner;
import app.mate4win.gg.util.Data;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.mate4win.gg.util.Config.progressDialogMessage;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private Boolean desc_visible = false;
    private List<Post> items;

    public PostAdapter(Context context) {
        this.context = context;
    }

    public void UpdateData(List<Post> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout.LayoutParams layoutParams;

        @BindView(R.id.txt_title) TextView txt_title;
        @BindView(R.id.txt_sub_title) TextView txt_sub_title;
        @BindView(R.id.txt_member_count) TextView txt_member_count;
        @BindView(R.id.img_my_group) ImageView my_group;
        @BindView(R.id.btn_join) AppCompatImageButton btn_join;


        @BindDrawable(R.drawable.ic_baseline_hourglass) Drawable ic_hourglass;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_groups, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        Post item = items.get(position);
        if(item != null) {
            holder.txt_title.setText(item.getTitle());
            holder.txt_sub_title.setText(item.getSub_title());
            holder.btn_join.setClickable(true);
            holder.my_group.setVisibility(View.GONE);
            holder.btn_join.setVisibility(View.VISIBLE);

            if(Data.groups!=null) {
                for (Groups g : Data.groups) {
                    if (g != null && g.getId().equals(item.getGroup_id())) {
                        for (Member m : g.getMembers()) {
                            if (Data.member.getId().equals(m.getId())) {
                                holder.my_group.setVisibility(View.VISIBLE);
                                holder.btn_join.setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }

            if(Data.posts!=null) {
                for (Post p : Data.posts) {
                    if(p!=null && p.getGroup_id().equals(item.getGroup_id())) {
                        for (Member m : p.getPending()){
                            if(Data.member.getId().equals(m.getId())) {
                                holder.my_group.setVisibility(View.GONE);
                                holder.btn_join.setVisibility(View.VISIBLE);
                                holder.btn_join.setImageDrawable(holder.ic_hourglass);
                                holder.btn_join.setClickable(false);
                            }
                        }
                    }
                }
            }

            Post finalItem = item;
            if(holder.btn_join.isClickable()) {
                holder.btn_join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressDialogMessage(context, "...");
                        if (Data.member != null) {
                            new TaskRunner().executeAsync(new FetchPendingTask(context, finalItem.getGroup_id(), Data.member.getId(), false, new AsyncResponse() {
                                @Override
                                public void processFinish(Object output) {
                                    progressDialogMessage(null, null);

                                    holder.btn_join.setImageDrawable(holder.ic_hourglass);
                                }
                            }));
                        }

                    }
                });
            }


            String txt = item.getMember_count();
            holder.txt_member_count.setText(txt);
            txt= null;
        }

        item = null;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
