package demo.nhatthai.cafegrapp.activity;

import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionInflater;
import android.os.Bundle;
import android.view.Gravity;

import demo.nhatthai.cafegrapp.R;

/**
 * Created by nhatthai on 5/12/16.
 */
public class FeedDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        showActionBar(true);
    }

//    private void setupWindowAnimations() {
//        Slide slideTransition = new Slide();
//        slideTransition.setSlideEdge(Gravity.LEFT);
//        slideTransition.setDuration(1000);
//        getWindow().setReenterTransition(slideTransition);
//        getWindow().setExitTransition(slideTransition);
//    }

}
