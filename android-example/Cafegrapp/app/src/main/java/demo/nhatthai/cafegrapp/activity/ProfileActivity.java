package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.fragment.ProfileFragment;

/**
 * Created by nhatthai on 5/18/16.
 */
public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        showActionBar(false);

        //add fragment
        if (savedInstanceState == null) {
            ProfileFragment profileFragment = new ProfileFragment();
            addFragment(R.id.container, profileFragment, "profileFragment");
        } else {
            getFragmentManager().findFragmentByTag("profileFragment");
        }
    }
}
