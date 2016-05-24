package demo.nhatthai.cafegrapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.fragment.FeedFragment;
import demo.nhatthai.cafegrapp.fragment.MainFragment;

/**
 * Created by nhatthai on 5/20/16.
 */
public class MainActivity extends BaseActivity {
    private FeedFragment feedFragment;
    private Toolbar mToolbar;
    private TextView mTxtViewTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        showActionBar(false);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        mTxtViewTitleBar = (TextView) mToolbar.findViewById(R.id.toolbarTitle);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        }; // Drawer Toggle Object Made

        // Drawer Listener set to the Drawer toggle
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        //add fragment
        if (savedInstanceState == null) {
            MainFragment navDrawerFragment = new MainFragment(this);
            navDrawerFragment.setUp((DrawerLayout) findViewById(R.id.drawer_layout));
            addFragment(R.id.navDrawer, navDrawerFragment, "navDrawerFragment");

            feedFragment = new FeedFragment(this);
            addFragment(R.id.fragmentContainer, feedFragment, "feedFragment");
        } else {
            getFragmentManager().findFragmentByTag("navDrawerFragment");
            getFragmentManager().findFragmentByTag("feedFragment");
        }

    }

    public void replaceFragment(Fragment fragment, String fragmentTag, String backStackStateName) {
        mTxtViewTitleBar.setText("");
        replaceFragment(R.id.fragmentContainer, fragment, fragmentTag, backStackStateName);
    }

    public void replaceFeedFragment() {
        mTxtViewTitleBar.setText("Flavors");
        replaceFragment(R.id.fragmentContainer, feedFragment, "feedFragment", "");
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        Log.d("Android", "Include option mennu");
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_main_actions, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }
}
