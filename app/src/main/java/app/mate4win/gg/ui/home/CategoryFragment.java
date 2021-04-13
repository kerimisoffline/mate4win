/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.home;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.BaseActivity;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.adapter.CategoriesAdapter;
import app.mate4win.gg.adapter.MainCategoryAdapter;
import app.mate4win.gg.model.Category;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchCategoriesTask;
import app.mate4win.gg.task.FetchMainCategoriesTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.GridItemDecoration;
import app.mate4win.gg.util.ItemClickSupport;
import butterknife.BindDimen;
import butterknife.BindView;

import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.categories;
import static app.mate4win.gg.util.Data.mainCategories;
import static app.mate4win.gg.util.Data.selectedPlatform;

public class CategoryFragment extends BaseFragment {

    public static CategoryFragment Current;

    @BindView(R.id.rv_main_category)
    RecyclerView rv_main_category;
    @BindDimen(R.dimen.grid_spacing) int grid_spacing;

    private GridItemDecoration gridItemDecoration;
    private MainCategoryAdapter mainCategoryAdapter;

    public CategoryFragment(){
        layoutId = R.layout.fragment_category;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        Current = this;

        rv_main_category.setLayoutManager(new LinearLayoutManager(getContext()));

        rv_main_category.setHasFixedSize(true);
        setAdapter();
        fetchCategoryTask();


        ItemClickSupport.addTo(rv_main_category).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if(position < 0 || mainCategories == null || mainCategories.size() == 0 || mainCategories.get(position) == null)
                    return;

                selectedPlatform = mainCategories.get(position);

                if(MainActivity.Current!=null)
                    MainActivity.Current.Navigate(R.id.navigation_home,null,null,null);
            }
        });
    }
    private void setAdapter(){

        if(mainCategoryAdapter == null)
            mainCategoryAdapter = new MainCategoryAdapter(getContext());

        rv_main_category.setAdapter(mainCategoryAdapter);
    }

    private void setData(){
        if(mainCategoryAdapter != null) {
            mainCategoryAdapter.UpdateData(mainCategories);
        }
        setAdapter();
    }

    private void fetchCategoryTask(){
        progressDialogMessage(getContext(), "...");
        getRunner().executeAsync(new FetchMainCategoriesTask(getContext(), new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                progressDialogMessage(null, null);
                if(Current == null || mainCategoryAdapter == null)
                    return;
                setData();
            }
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Current = null;
        mainCategoryAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchCategoryTask();
    }
}
