/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.home;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.adapter.GroupsAdapter;
import app.mate4win.gg.adapter.PostAdapter;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchGroupsTask;
import app.mate4win.gg.task.FetchPostTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.GridItemDecoration;
import app.mate4win.gg.util.ItemClickSupport;
import butterknife.BindDimen;
import butterknife.BindView;

import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.categories;
import static app.mate4win.gg.util.Data.groups;
import static app.mate4win.gg.util.Data.mainCategories;
import static app.mate4win.gg.util.Data.posts;


public class PostFragment extends BaseFragment {
    public static PostFragment fragment;

    @BindView(R.id.rv_posts)
    RecyclerView rv_posts;
    @BindDimen(R.dimen.grid_spacing) int grid_spacing;


    private GridItemDecoration gridItemDecoration;
    private PostAdapter postAdapter;

    public PostFragment(){
        layoutId = R.layout.fragment_post;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        setAdapter();
        fetchPostTask(Data.selectedCategory.getTitle());
        rv_posts.setLayoutManager(new LinearLayoutManager(getContext()));


        ItemClickSupport.addTo(rv_posts).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            }
        });
    }
    private void setAdapter(){

        if(postAdapter == null)
            postAdapter = new PostAdapter(getContext());

        rv_posts.setAdapter(postAdapter);
        setData();
    }

    private void setData(){
        if(postAdapter != null) {
            postAdapter.UpdateData(posts);
        }
    }

    private void fetchPostTask(String category){
        progressDialogMessage(getContext(), "...");
        if(Data.member!=null) {
            getRunner().executeAsync(new FetchPostTask(getContext(),category,new AsyncResponse() {
                @Override
                public void processFinish(Object output) {
                    progressDialogMessage(null, null);
                    if (fragment == null || postAdapter == null)
                        return;
                    setData();
                }
            }));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Data.selectedCategory = null;
        fragment = null;
        postAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
