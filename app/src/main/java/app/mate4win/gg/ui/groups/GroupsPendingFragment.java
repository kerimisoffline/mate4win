/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

import android.content.DialogInterface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.mate4win.gg.R;
import app.mate4win.gg.adapter.GroupsDetailAdapter;
import app.mate4win.gg.adapter.GroupsPendingAdapter;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.ui.view.ContactView;
import app.mate4win.gg.util.GridItemDecoration;
import app.mate4win.gg.util.ItemClickSupport;
import butterknife.BindDimen;
import butterknife.BindView;

import static app.mate4win.gg.util.Data.selectedGroups;

public class GroupsPendingFragment extends BaseFragment {

    public static GroupsPendingFragment fragment;

    @BindView(R.id.rv_pending_members)
    RecyclerView rv_pending;

    private GroupsPendingAdapter groupsPendingAdapter;

    public GroupsPendingFragment(){
        layoutId = R.layout.fragment_groups_pending;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        setAdapter();
        rv_pending.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemClickSupport.addTo(rv_pending).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            }
        });
    }
    private void setAdapter(){
        if(groupsPendingAdapter == null)
            groupsPendingAdapter = new GroupsPendingAdapter(getContext());
        rv_pending.setAdapter(groupsPendingAdapter);

        setData();
    }

    private void setData(){
        if(groupsPendingAdapter != null) {
            groupsPendingAdapter.UpdateData(selectedGroups.getPending());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
        groupsPendingAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
