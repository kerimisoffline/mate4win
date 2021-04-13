/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.profile;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import app.mate4win.gg.R;
import app.mate4win.gg.ui.BaseFragment;

public class SettingsFragment extends BaseFragment {

    public static SettingsFragment fragment;

    public SettingsFragment(){
        layoutId = R.layout.fragment_settings;
    }

    public static SettingsFragment newInstance(){
        if(fragment == null)
            fragment = new SettingsFragment();

        return fragment;
    }

    public static SettingsFragment getInstance() {
        return fragment;
    }

    @Override
    protected void handler() {
        super.handler();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}