package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;
import android.util.Log;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.fragment.LoginFragment;

/**
 * Created by nhatthai on 5/12/16.
 */
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //show/hide Action Bar
        showActionBar(false);

        //add fragment
        if (savedInstanceState == null) {
            Log.d("Android", "Add Login Fragment");
            LoginFragment loginFragment = new LoginFragment();
            addFragment(R.id.container, loginFragment, "loginFragment");
        } else {
            getFragmentManager().findFragmentByTag("loginFragment");
        }

    }

}
