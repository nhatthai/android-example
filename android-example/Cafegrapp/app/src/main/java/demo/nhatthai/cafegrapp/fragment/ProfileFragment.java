package demo.nhatthai.cafegrapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.adapter.ProfileFeedAdapter;
import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.presenter.FeedPresenter;
import demo.nhatthai.cafegrapp.presenter.FeedPresenterImpl;
import demo.nhatthai.cafegrapp.view.FeedView;

/**
 * Created by nhatthai on 5/20/16.
 */
public class ProfileFragment extends BaseFragment
        implements FeedView, ProfileFeedAdapter.OnFeedItemClickListener{
    private Context context;
    private RecyclerView rvProfileFeeds;
    private ProfileFeedAdapter profileFeedAdapter;
    private ArrayList<Feed> feedList;
    private FeedPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_profile, container, false);
        context = mView.getContext();
        feedList = new ArrayList<Feed>();

        mPresenter = new FeedPresenterImpl(this);
        mPresenter.onResume();

        //initAdapter
        if (profileFeedAdapter == null) {
            Log.d("Android", "afsfd" + feedList.size());
            profileFeedAdapter = new ProfileFeedAdapter(context, feedList);
            profileFeedAdapter.setOnFeedItemClickListener(this);
        }

        //init list data
        rvProfileFeeds = (RecyclerView) mView.findViewById(R.id.rvProfileFeeds);
        rvProfileFeeds.setLayoutManager(new GridLayoutManager(context, 1));
        rvProfileFeeds.setAdapter(profileFeedAdapter);

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
        Log.d("Android", "Debug"+ feeds.size());
        profileFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Feed feedItem) {

    }

    @Override
    public void onProfileClick() {

    }

    @Override
    public void onLikeClick() {

    }
}
