/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

import android.content.DialogInterface;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.adapter.GroupsAdapter;
import app.mate4win.gg.adapter.GroupsDetailAdapter;
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

public class GroupsDetailFragment extends BaseFragment {

    public static GroupsDetailFragment fragment;

    @BindView(R.id.rv_members)
    RecyclerView rv_members;
    @BindView(R.id.txt_title) TextView txt_title;
    @BindView(R.id.txt_sub_title) TextView txt_sub_title;
    @BindView(R.id.txt_description) TextView txt_description;
    @BindView(R.id.txt_pending) TextView txt_pending;
    @BindView(R.id.btn_pending) LinearLayout btn_pending;

    @BindView(R.id.btn_detail) FrameLayout btn_detail;

    @BindDimen(R.dimen.grid_spacing) int grid_spacing;


    private GridItemDecoration gridItemDecoration;
    private GroupsDetailAdapter groupsDetailAdapter;

    public GroupsDetailFragment(){
        layoutId = R.layout.fragment_groups_detail;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        setAdapter();
        rv_members.setLayoutManager(new LinearLayoutManager(getContext()));

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactView contactView = new ContactView(getContext(),R.style.TransparentBottomSheetDialog);
                contactView.initView(selectedGroups);
                contactView.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {

                    }
                });
                contactView.show();
            }
        });

        btn_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.Current!=null)
                    MainActivity.Current.Navigate(R.id.navigation_groups_pending,null,null,null);
            }
        });


        ItemClickSupport.addTo(rv_members).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
            }
        });
    }
    private void setAdapter(){
        if(selectedGroups!=null){
            txt_title.setText(selectedGroups.getTitle());
            txt_sub_title.setText(selectedGroups.getSub_title());
            txt_description.setText(selectedGroups.getDescription());

            if(selectedGroups.getPending()!=null) {
                if (selectedGroups.getPending().size() == 0)
                    txt_pending.setVisibility(View.GONE);
                else {
                    txt_pending.setVisibility(View.VISIBLE);
                    txt_pending.setText(String.valueOf(selectedGroups.getPending().size()));
                }
            }
        }
        if(groupsDetailAdapter == null)
            groupsDetailAdapter = new GroupsDetailAdapter(getContext());
        rv_members.setAdapter(groupsDetailAdapter);

        setData();
    }

    private void setData(){
        if(groupsDetailAdapter != null) {
            groupsDetailAdapter.UpdateData(selectedGroups.getMembers());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
        groupsDetailAdapter = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
