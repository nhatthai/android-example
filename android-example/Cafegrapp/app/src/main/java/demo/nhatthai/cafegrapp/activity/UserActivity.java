package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 7/5/16.
 */
public class UserActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        showActionBar(true);
    }
}
