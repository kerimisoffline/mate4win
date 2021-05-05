/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.mate4win.gg.R;
import app.mate4win.gg.adapter.GroupsDetailAdapter;
import app.mate4win.gg.adapter.GroupsMembersAdapter;
import app.mate4win.gg.adapter.GroupsPendingAdapter;
import app.mate4win.gg.model.Groups;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchGroupsTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.ui.view.ContactView;
import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.GridItemDecoration;
import app.mate4win.gg.util.ItemClickSupport;
import butterknife.BindDimen;
import butterknife.BindView;

import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.groups;
import static app.mate4win.gg.util.Data.selectedGroups;

public class GroupsMembersFragment extends BaseFragment {

    public static GroupsMembersFragment fragment;

    @BindView(R.id.rv_groups_members)
    RecyclerView rv_groups_members;

    private GroupsMembersAdapter groupsMembersAdapter;

    public GroupsMembersFragment(){
        layoutId = R.layout.fragment_groups_members;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        setAdapter();
        rv_groups_members.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemClickSupport.addTo(rv_groups_members).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            }
        });
    }
    private void setAdapter(){
        if(groupsMembersAdapter == null)
            groupsMembersAdapter = new GroupsMembersAdapter(getContext());
        rv_groups_members.setAdapter(groupsMembersAdapter);

        setData();
    }

    private void setData(){
        if(groupsMembersAdapter != null) {
            groupsMembersAdapter.UpdateData(selectedGroups.getMembers());
        }
    }

    public void notifyData(){
        progressDialogMessage(getContext(), "...");
        if(Data.member!=null) {
            getRunner().executeAsync(new FetchGroupsTask(getContext(),Data.member.getId(), new AsyncResponse() {
                @Override
                public void processFinish(Object output) {
                    progressDialogMessage(null, null);
                    if (fragment == null)
                        return;

                    if(groups!=null)
                        if(selectedGroups!=null){
                            for(Groups g: groups)
                                if(g.getId().equals(selectedGroups.getId()))
                                    selectedGroups = g;
                        }
                    setData();
                }
            }));
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
        groupsMembersAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
