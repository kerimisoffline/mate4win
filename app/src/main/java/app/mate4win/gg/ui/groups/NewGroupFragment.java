/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import app.mate4win.gg.R;
import app.mate4win.gg.activity.MainActivity;
import app.mate4win.gg.adapter.GroupsAdapter;
import app.mate4win.gg.data.FetchNewGroup;
import app.mate4win.gg.task.AsyncResponse;
import app.mate4win.gg.task.FetchGroupsTask;
import app.mate4win.gg.task.NewGroupTask;
import app.mate4win.gg.ui.BaseFragment;
import app.mate4win.gg.util.Config;
import app.mate4win.gg.util.Data;
import app.mate4win.gg.util.GridItemDecoration;
import app.mate4win.gg.util.ItemClickSupport;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.OnClick;

import static app.mate4win.gg.util.Config.progressDialogMessage;
import static app.mate4win.gg.util.Data.groups;
import static app.mate4win.gg.util.Data.selectedGroups;

public class NewGroupFragment extends BaseFragment {

    public static NewGroupFragment fragment;
    private boolean click_contact = false;


    @BindView(R.id.btn_contact) TextView btn_contact;
    @BindView(R.id.lyt_new_group) LinearLayout lyt_new_group;
    @BindView(R.id.lyt_contact_info) LinearLayout lyt_contact_info;
    @BindView(R.id.edt_title) AppCompatEditText edt_title;
    @BindView(R.id.edt_sub_title) AppCompatEditText edt_sub_title;
    @BindView(R.id.edt_platform) AppCompatEditText edt_platform;
    @BindView(R.id.edt_category) AppCompatEditText edt_category;
    @BindView(R.id.edt_email) AppCompatEditText edt_email;
    @BindView(R.id.edt_telegram) AppCompatEditText edt_telegram;
    @BindView(R.id.edt_discord) AppCompatEditText edt_discord;
    @BindView(R.id.edt_skype) AppCompatEditText edt_skype;
    @BindView(R.id.edt_instagram) AppCompatEditText edt_instagram;

    @BindView(R.id.btn_save) AppCompatButton btn_save;

    @BindDimen(R.dimen.grid_spacing) int grid_spacing;

    @OnClick(R.id.btn_save) void click_save(){
        if(click_contact)
        {
            btn_save.setText(R.string.create);
            lyt_new_group.setVisibility(View.VISIBLE);
            btn_contact.setVisibility(View.VISIBLE);
            lyt_contact_info.setVisibility(View.GONE);
            click_contact = false;
        } else  {
            update();
        }

    }

    @OnClick(R.id.btn_contact) void click_contact(){
        click_contact = true;

        btn_save.setText(R.string.save);
        lyt_new_group.setVisibility(View.GONE);
        btn_contact.setVisibility(View.GONE);
        lyt_contact_info.setVisibility(View.VISIBLE);
        /*
            Bundle bundle = new Bundle();
            bundle.putBoolean("click_contact", true);
            MainActivity.Current.Navigate(R.id.navigation_new_group, bundle, null, null);

         */
    }

    public NewGroupFragment(){
        layoutId = R.layout.fragment_new_group;
    }

    @Override
    protected void viewRef(View view) {
        super.viewRef(view);
    }

    @Override
    protected void handler() {
        fragment = this;

        //click_contact = getArguments() != null && getArguments().getBoolean("click_contact", false);

        if(click_contact){
            btn_contact.setVisibility(View.VISIBLE);
            lyt_new_group.setVisibility(View.GONE);
            btn_contact.setVisibility(View.GONE);
            lyt_contact_info.setVisibility(View.VISIBLE);
        } else {
            lyt_new_group.setVisibility(View.VISIBLE);
            btn_contact.setVisibility(View.VISIBLE);
            lyt_contact_info.setVisibility(View.GONE);
        }
    }

    private void update(){
        String title = edt_title != null && edt_title.getText() !=null ? edt_title.getText().toString() : null;
        String sub_title = edt_sub_title != null && edt_sub_title.getText() !=null ? edt_sub_title.getText().toString() : null;
        String platform = edt_platform != null && edt_platform.getText() !=null ? edt_platform.getText().toString() : null;
        String category = edt_category != null && edt_category.getText() !=null ? edt_category.getText().toString() : null;
        String email = edt_email != null && edt_email.getText() !=null ? edt_email.getText().toString() : null;
        String telegram = edt_telegram != null && edt_telegram.getText() !=null ? edt_telegram.getText().toString() : null;
        String discord = edt_discord != null && edt_discord.getText() !=null ? edt_discord.getText().toString() : null;
        String skype = edt_skype != null && edt_skype.getText() !=null ? edt_skype.getText().toString() : null;
        String instagram = edt_instagram != null && edt_instagram.getText() !=null ? edt_instagram.getText().toString() : null;

        Config.progressDialogMessage(getContext(),"...");
        getRunner().executeAsync(new NewGroupTask(getContext(), title, sub_title, platform, category, email, telegram, discord, skype, instagram, new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                Config.progressDialogMessage(null,null);
                FetchNewGroup out= (FetchNewGroup) output;
                if(out == null)
                    return;
                if(Config.isNotNull(out.getSuccessMessage())){
                    Config.AlertDialog(getContext(),"", out.getSuccessMessage());
                    if(MainActivity.Current!=null)
                    MainActivity.Current.Navigate(R.id.navigation_groups,null,null,null);

                } else if (Config.isNotNull(out.getErrorMessage())) {
                    Config.AlertDialog(getContext(),"",out.getErrorMessage());
                }
            }
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragment = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}