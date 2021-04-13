/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.adapter;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import android.content.Context;
import android.text.Html;
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
import app.mate4win.gg.util.Data;
import butterknife.BindView;
import butterknife.ButterKnife;

import static app.mate4win.gg.util.Config.isNotNull;
import static app.mate4win.gg.util.Config.loadImage;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private Context context;
    private Boolean desc_visible = false;
    private List<Category> items;

    public CategoriesAdapter(Context context) {
        this.context = context;
    }

    public void UpdateData(List<Category> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_show) ImageView img_show;
        @BindView(R.id.txt_title) TextView txt_title;
        @BindView(R.id.layout) LinearLayout layout;
        LinearLayout.LayoutParams layoutParams;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        Category item = items.get(position);
        if(item != null) {
            if(isNotNull(item.getImg()))
                loadImage.load(context, item.getImg(), holder.img_show, false, 0);
            holder.txt_title.setText(item.getTitle());
        }

        item = null;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
