package demo.nhatthai.cafegrapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.activity.MainActivity;
import demo.nhatthai.cafegrapp.adapter.NavDrawerAdapter;


/**
 * Created by nhatthai on 5/20/16.
 */
public class MainFragment extends BaseFragment
        implements NavDrawerAdapter.OnNavItemClickListener {

    ActionBarDrawerToggle mDrawerToggle;
    RecyclerView mRecyclerView;
    private NavDrawerAdapter mAdapter;
    private TextView mTxtViewTitleBar;
    private DrawerLayout mDrawerLayout;
    private MainActivity activity;

    @SuppressLint("ValidFragment")
    public MainFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setUp(DrawerLayout drawerLayout) {
        Toolbar toolbar = (Toolbar) drawerLayout.findViewById(R.id.toolbar);
        mTxtViewTitleBar = (TextView) toolbar.findViewById(R.id.toolbarTitle);
        mDrawerLayout = drawerLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_drawer, container, false);
        Context context = view.getContext();

        mAdapter = new NavDrawerAdapter();
        mAdapter.setOnNavItemClickListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvMenuItem);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        // Setting the layout Manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));

        return view;
    }

    @Override
    public void onNavItemClick(int position) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(position) {
            case 0:
                mTxtViewTitleBar.setText("Flavors");
                mDrawerLayout.closeDrawers();
                //fragmentClass = FeedFragment.class;
                this.activity.replaceFeedFragment();
                return;
//            case 1:
//                fragmentClass = FeedFragment.class;
//                break;
            default:
                mTxtViewTitleBar.setText("");
                fragmentClass = ProfileFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.activity.replaceFragment(fragment, "", "");
        mDrawerLayout.closeDrawers();
    }
}
