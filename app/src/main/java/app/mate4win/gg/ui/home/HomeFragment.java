/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.home;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.adapter.CategoriesAdapter;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchCategoriesTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.GridItemDecoration;
import app.mate4win.gg.util.ItemClickSupport;
import butterknife.BindDimen;
import butterknife.BindView;

import static app.mate4win.gg.activity.MainActivity.Current;
import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.categories;
import static app.mate4win.gg.util.Data.selectedCategory;

public class HomeFragment extends BaseFragment {

    public static HomeFragment Current;

    @BindView(R.id.rv_categories)
    RecyclerView rv_categories;
    @BindDimen(R.dimen.grid_spacing) int grid_spacing;


    private GridItemDecoration gridItemDecoration;
    private CategoriesAdapter categoriesAdapter;

    public HomeFragment(){
        layoutId = R.layout.fragment_home;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        Current = this;

        rv_categories.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_categories.setHasFixedSize(true);
        setAdapter();
        fetchCategoryTask(Data.selectedPlatform.getPlatform());


        ItemClickSupport.addTo(rv_categories).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if(position < 0 || categories == null || categories.size() == 0 || categories.get(position) == null)
                    return;

                selectedCategory = categories.get(position);

                if(MainActivity.Current!=null)
                    MainActivity.Current.Navigate(R.id.navigation_post,null,null,null);
            }
        });
    }
    private void setAdapter(){

        if(categoriesAdapter == null)
            categoriesAdapter = new CategoriesAdapter(getContext());

        rv_categories.setAdapter(categoriesAdapter);
    }

    private void setData(){
        if(categoriesAdapter != null) {
            categoriesAdapter.UpdateData(categories);
        }
        setAdapter();
    }

    private void fetchCategoryTask(String platform){
        progressDialogMessage(getContext(), "...");
        getRunner().executeAsync(new FetchCategoriesTask(getContext(),platform, new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                progressDialogMessage(null, null);
                if(Current == null || categoriesAdapter == null)
                    return;
                setData();
            }
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Current = null;
        categoriesAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}