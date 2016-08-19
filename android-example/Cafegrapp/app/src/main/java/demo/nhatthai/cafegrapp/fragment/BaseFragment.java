package demo.nhatthai.cafegrapp.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by nhatthai on 5/19/16.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param containerViewId
     * @param fragment
     * @param fragmentTag
     */
    public void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        this.getFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .commit();
    }

    /**
     *  @param fragment
     * @param fragmentTag
     * @param backStackStateName
     */
    public void replaceFragment(@NonNull Fragment fragment,
                                @IdRes int containerViewId,
                                @NonNull String fragmentTag,
                                @Nullable String backStackStateName) {
        this.getFragmentManager().beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }
}
