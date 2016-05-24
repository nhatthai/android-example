package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.fragment.FeedFragment;

/**
 * Created by nhatthai on 5/12/16.
 */
public class FeedActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        showActionBar(false);

        //add fragment
        if (savedInstanceState == null) {
            FeedFragment feedFragment = new FeedFragment();
            addFragment(R.id.fragmentContainter, feedFragment, "feedFragment");
        } else {
            getFragmentManager().findFragmentByTag("feedFragment");
        }
    }

}
