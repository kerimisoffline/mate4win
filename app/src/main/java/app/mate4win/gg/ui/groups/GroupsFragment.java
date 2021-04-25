/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.adapter.GroupsAdapter;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchGroupsTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.GridItemDecoration;
import app.mate4win.gg.util.ItemClickSupport;
import butterknife.BindDimen;
import butterknife.BindView;

import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.categories;
import static app.mate4win.gg.util.Data.groups;
import static app.mate4win.gg.util.Data.selectedCategory;
import static app.mate4win.gg.util.Data.selectedGroups;

public class GroupsFragment extends BaseFragment {

    public static GroupsFragment fragment;

    @BindView(R.id.rv_groups) RecyclerView rv_groups;
    @BindView(R.id.btn_new_group) FloatingActionButton btn_new_group;

    @BindDimen(R.dimen.grid_spacing) int grid_spacing;


    private GridItemDecoration gridItemDecoration;
    private GroupsAdapter groupsAdapter;

    public GroupsFragment(){
        layoutId = R.layout.fragment_groups;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        setAdapter();
        fetchGroupTask();
        rv_groups.setLayoutManager(new LinearLayoutManager(getContext()));


        ItemClickSupport.addTo(rv_groups).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                if(position < 0 || groups == null || groups.size() == 0 || groups.get(position) == null)
                    return;

                selectedGroups = groups.get(position);

                if(MainActivity.Current!=null)
                    MainActivity.Current.Navigate(R.id.navigation_groups_detail,null,null,null);
            }
        });

        btn_new_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.Current!=null)
                    MainActivity.Current.Navigate(R.id.navigation_new_group,null,null,null);
            }
        });
    }
    private void setAdapter(){

        if(groupsAdapter == null)
            groupsAdapter = new GroupsAdapter(getContext());

        rv_groups.setAdapter(groupsAdapter);
        setData();
    }

    private void setData(){
        if(groupsAdapter != null) {
            groupsAdapter.UpdateData(groups);
        }
    }

    private void fetchGroupTask(){
        progressDialogMessage(getContext(), "...");
        if(Data.member!=null) {
            getRunner().executeAsync(new FetchGroupsTask(getContext(),Data.member.getId(), new AsyncResponse() {
                @Override
                public void processFinish(Object output) {
                    progressDialogMessage(null, null);
                    if (fragment == null || groupsAdapter == null)
                        return;
                    setData();
                }
            }));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
        groupsAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchGroupTask();
    }
}