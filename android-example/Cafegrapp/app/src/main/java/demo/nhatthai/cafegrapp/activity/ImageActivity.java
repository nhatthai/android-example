package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 6/23/16.
 */
public class ImageActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        showActionBar(true);
    }
}
