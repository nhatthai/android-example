package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 6/29/16.
 */
public class CreateFeedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_create);
        showActionBar(true);
    }
}
