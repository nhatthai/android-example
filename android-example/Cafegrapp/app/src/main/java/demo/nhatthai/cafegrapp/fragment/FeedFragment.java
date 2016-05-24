package demo.nhatthai.cafegrapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.activity.MainActivity;
import demo.nhatthai.cafegrapp.adapter.FeedAdapter;
import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.view.FeedView;
import demo.nhatthai.cafegrapp.presenter.FeedPresenterImpl;
import demo.nhatthai.cafegrapp.presenter.FeedPresenter;

/**
 * Created by nhatthai on 5/13/16.
 */
public class FeedFragment extends BaseFragment implements FeedView, FeedAdapter.OnFeedItemClickListener {

    private  RecyclerView rvFeeds;
    private FeedAdapter feedAdapter;
    private FeedPresenter mPresenter;
    private ArrayList<Feed> feedList = new ArrayList<>();
    private Context context;
    private MainActivity activity;

    public FeedFragment() {

    }

    public FeedFragment(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_feeds, container, false);
        context = mView.getContext();

        mPresenter = new FeedPresenterImpl(this);
        mPresenter.onResume();

        final Activity activity = getActivity();
        feedList = new ArrayList<Feed>();

        //initAdapter
        if (feedAdapter == null) {
            feedAdapter = new FeedAdapter(activity, feedList);
            feedAdapter.setOnFeedItemClickListener(this);
        }

        //init list data
        rvFeeds = (RecyclerView) mView.findViewById(R.id.rvFeeds);
        rvFeeds.setLayoutManager(new GridLayoutManager(activity, 1));
        rvFeeds.setAdapter(feedAdapter);

        return mView;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setFeedsFragment(List<Feed> feeds) {
        feedList.addAll(feeds);
        feedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Feed feedItem) {

    }

    @Override
    public void onProfileClick() {
        Fragment fragment = null;
        Class fragmentClass;
        fragmentClass = ProfileFragment.class;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.activity.replaceFragment(fragment, "", "");
        //mDrawerLayout.closeDrawers();
    }

    @Override
    public void onLikeClick() {
        Log.d("----", "onLikeClick");
    }

}
