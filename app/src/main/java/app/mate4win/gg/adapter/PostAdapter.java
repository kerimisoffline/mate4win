/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.adapter;


import android.content.Context;
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
import app.mate4win.gg.model.Post;
import butterknife.BindView;
import butterknife.ButterKnife;

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

        @BindView(R.id.txt_title)
        TextView txt_title;
        @BindView(R.id.txt_sub_title) TextView txt_sub_title;
        @BindView(R.id.txt_member_count) TextView txt_member_count;
        @BindView(R.id.img_my_group)
        ImageView my_group;
        @BindView(R.id.btn_join)
        AppCompatImageButton img_button;


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
            holder.my_group.setVisibility(View.GONE);
            holder.img_button.setVisibility(View.VISIBLE);
            String txt = item.getMember_count() + "/5";
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
