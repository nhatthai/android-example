package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 5/18/16.
 */
public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        showActionBar(true);
    }

}
