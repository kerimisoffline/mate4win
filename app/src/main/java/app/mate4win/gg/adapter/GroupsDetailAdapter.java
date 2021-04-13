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
import app.mate4win.gg.model.Member;
import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupsDetailAdapter extends RecyclerView.Adapter<GroupsDetailAdapter.ViewHolder> {

    private Context context;
    private Boolean desc_visible = false;
    private List<Member> items;

    public GroupsDetailAdapter(Context context) {
        this.context = context;
    }

    public void UpdateData(List<Member> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout.LayoutParams layoutParams;

        @BindView(R.id.txt_member)
        TextView txt_member;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_group_member, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        Member item = items.get(position);
        if(item != null) {
            holder.txt_member.setText(item.getNick_name());
        }

        item = null;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
