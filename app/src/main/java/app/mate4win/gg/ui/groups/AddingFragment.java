/*
 * Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
 */

package app.mate4win.gg.ui.groups;

/*
Created by Abdulkerim Yıldırım (kermmyldrm@gmail.com)
*/

import app.mate4win.gg.R;
import app.mate4win.gg.ui.BaseFragment;

public class AddingFragment extends BaseFragment {

    public static AddingFragment fragment;

    public AddingFragment(){
        layoutId = R.layout.fragment_adding;
    }

    public static AddingFragment newInstance(){
        if(fragment == null)
            fragment = new AddingFragment();

        return fragment;
    }

    public static AddingFragment getInstance() {
        return fragment;
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