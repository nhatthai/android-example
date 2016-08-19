package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;

import demo.nhatthai.cafegrapp.R;

public class MapActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        showActionBar(false);
    }

}

