package smart.liyinwang.jn.smarthome.core;

import android.support.v4.app.Fragment;

/**
 * Created by ajou on 2015-04-25.
 */
public abstract class BaseFragment extends Fragment {
    private boolean isVisible;

    public abstract void loadData();

    public void onVisible() {
        loadData();
    }

    public void onInvisible() {}

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
}
