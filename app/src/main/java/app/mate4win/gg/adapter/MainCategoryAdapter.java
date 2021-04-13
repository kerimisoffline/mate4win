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

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import app.mate4win.gg.R;
import app.mate4win.gg.model.Category;
import app.mate4win.gg.model.MainCategory;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.mate4win.gg.util.Config.isNotNull;
import static app.mate4win.gg.util.Config.loadImage;

public class MainCategoryAdapter extends RecyclerView.Adapter<MainCategoryAdapter.ViewHolder>{
    private Context context;
    private Boolean desc_visible = false;
    private List<MainCategory> items;

    public MainCategoryAdapter(Context context) {
        this.context = context;
    }

    public void UpdateData(List<MainCategory> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout) LinearLayout layout;
        @BindView(R.id.img_show) ImageView img_show;
        @BindView(R.id.txt_title) TextView txt_title;
        LinearLayout.LayoutParams layoutParams;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    @NotNull
    @Override
    public MainCategoryAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main_category, parent, false);
        return new MainCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull MainCategoryAdapter.ViewHolder holder, int position) {
        MainCategory item = items.get(position);
        if(item != null) {
            if(isNotNull(item.getImg()))
                loadImage.load(context, item.getImg(), holder.img_show, false, 0);
            if(item.getId().equals("1"))
                holder.txt_title.setText(R.string.txt_mobile);
            else if(item.getId().equals("2"))
                holder.txt_title.setText(R.string.txt_pc);
        }

        item = null;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

}
