/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
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
import app.mate4win.gg.model.Category;
import app.mate4win.gg.model.Groups;
import app.mate4win.gg.model.MainCategory;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.mate4win.gg.util.Config.isNotNull;
import static app.mate4win.gg.util.Config.loadImage;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.ViewHolder> {

    private Context context;
    private Boolean desc_visible = false;
    private List<Groups> items;

    public GroupsAdapter(Context context) {
        this.context = context;
    }

    public void UpdateData(List<Groups> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout.LayoutParams layoutParams;

        @BindView(R.id.txt_title) TextView txt_title;
        @BindView(R.id.txt_sub_title) TextView txt_sub_title;
        @BindView(R.id.txt_member_count) TextView txt_member_count;
        @BindView(R.id.img_my_group) ImageView my_group;
        @BindView(R.id.btn_join) AppCompatImageButton img_button;


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
        Groups item = items.get(position);
        if(item != null) {
            holder.txt_title.setText(item.getTitle());
            holder.txt_sub_title.setText(item.getSub_title());
            holder.my_group.setVisibility(View.VISIBLE);
            holder.img_button.setVisibility(View.GONE);
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
