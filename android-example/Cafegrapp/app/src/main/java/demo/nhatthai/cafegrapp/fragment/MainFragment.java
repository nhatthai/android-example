package demo.nhatthai.cafegrapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.support.v4.widget.DrawerLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.activity.MapActivity;
import demo.nhatthai.cafegrapp.events.MessageEvent;
import demo.nhatthai.cafegrapp.settings.Session;


/**
 * Created by nhatthai on 5/20/16.
 */
public class MainFragment extends BaseFragment
        implements NavigationView.OnNavigationItemSelectedListener {

    private Activity mActivity;
    private View mView;
    private DrawerLayout mDrawer;
    private TextView mTxtName, mTxtAddress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);

        Toolbar toolbar = (Toolbar) mView.findViewById(R.id.toolbar);

        //event click image profile
        ImageView navImgProfile = (ImageView) mView.findViewById(R.id.nav_img_profile);
        navImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onImgProfileClick(v);
            }
        });

        mTxtName = (TextView) mView.findViewById(R.id.txt_name_profile);
        mTxtName.setText(Session.firstname + " " + Session.lastname);

        mTxtAddress = (TextView) mView.findViewById(R.id.txt_address);
        mTxtAddress.setText(Session.city + ", " + Session.country);

        mDrawer = (DrawerLayout) mView.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mActivity, mDrawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) mView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //add fragment
        if (savedInstanceState == null) {
            FeedFragment feedFragment = new FeedFragment();
            addFragment(R.id.fragment_container, feedFragment, "feedFragment");
        }

        return mView;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // feeds
        if (id == R.id.nav_feed) {
            Fragment feedFragment = this.getFragmentManager().findFragmentByTag("feedFragment");

            if(feedFragment == null) {
                feedFragment = new FeedFragment();
            }

            this.replaceFragment(
                    feedFragment, R.id.fragment_container, "feedFragment", "feedFragment");

        } else if (id == R.id.nav_explore) {
            Intent i = new Intent(mActivity, MapActivity.class);
            startActivity(i);
            mActivity.overridePendingTransition(R.anim.push_down_in, R.anim.push_up_out);

        } else if (id == R.id.nav_cuisines) {

        } else if (id == R.id.nav_message) {

        } else if (id == R.id.nav_visitcafe) {

        } else if (id == R.id.nav_sign_out) {
            Log.d("Android", "Sign Out");
        }

        //DrawerLayout drawer = (DrawerLayout) mView.findViewById(R.id.drawer_layout);
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onImgProfileClick(View view) {
        ProfileFragment details = new ProfileFragment();

        ImageView imgview_profile = (ImageView) mView.findViewById(R.id.nav_img_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slideTransition = new Slide(Gravity.LEFT);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

            Slide slideTransitionExit = new Slide(Gravity.RIGHT);
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));

            ChangeBounds changeBoundsTransition = new ChangeBounds();
            changeBoundsTransition.setDuration(
                    getResources().getInteger(R.integer.anim_duration_medium));

            details.setEnterTransition(slideTransition);
            details.setExitTransition(slideTransitionExit);
            details.setSharedElementReturnTransition(changeBoundsTransition);
            details.setSharedElementEnterTransition(changeBoundsTransition);

            //pass data, set argument
            Bundle bundle = new Bundle();
            bundle.putString("text_name", mTxtName.getText().toString());
            details.setArguments(bundle);

            //get transition name
            String name = getString(R.string.transition_name);
            String navImgProfile = getString(R.string.transition_nav_img_profile);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, details)
                    .addToBackStack(null)
                    .addSharedElement(mTxtName, name)
                    .addSharedElement(imgview_profile, navImgProfile)
                    .commit();
        }
        //close Navigation Drawer
        mDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent event) {
        mTxtName.setText(event.firstname + " " + event.lastname);
        mTxtAddress.setText(event.city + ", " + Session.country);
    }

}

