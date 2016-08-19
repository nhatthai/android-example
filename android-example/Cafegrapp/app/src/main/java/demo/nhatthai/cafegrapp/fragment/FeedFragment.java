package demo.nhatthai.cafegrapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Pair;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import demo.nhatthai.cafegrapp.R;
import demo.nhatthai.cafegrapp.activity.CreateFeedActivity;
import demo.nhatthai.cafegrapp.activity.FeedDetailActivity;
import demo.nhatthai.cafegrapp.activity.ImageActivity;
import demo.nhatthai.cafegrapp.activity.ProfileActivity;
import demo.nhatthai.cafegrapp.adapter.FeedAdapter;
import demo.nhatthai.cafegrapp.model.Feed;
import demo.nhatthai.cafegrapp.events.RecyclerViewScrollListener;
import demo.nhatthai.cafegrapp.model.FoodType;
import demo.nhatthai.cafegrapp.settings.Constant;
import demo.nhatthai.cafegrapp.view.FeedView;
import demo.nhatthai.cafegrapp.presenter.FeedPresenterImpl;
import demo.nhatthai.cafegrapp.presenter.FeedPresenter;

/**
 * Created by nhatthai on 5/13/16.
 */
public class FeedFragment extends BaseFragment implements
        FeedView, FeedAdapter.OnFeedItemClickListener {

    private FeedAdapter mFeedAdapter;
    private List<Feed> feedList = new ArrayList<>();
    private View mView;
    private FeedPresenter feedPresenter;

    public FeedFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_feeds, container, false);

        feedPresenter = new FeedPresenterImpl(this);
        feedPresenter.onFeedUpdate();

        final Activity activity = getActivity();
        feedList = new ArrayList<Feed>();

        //initAdapter
        if (mFeedAdapter == null) {
            mFeedAdapter = new FeedAdapter(activity, feedList);
            mFeedAdapter.setOnFeedItemClickListener(this);
        }

        //init list data
        RecyclerView mRvFeeds = (RecyclerView) mView.findViewById(R.id.rv_feeds);
        LinearLayoutManager layoutManager = new LinearLayoutManagerExt(activity);
        mRvFeeds.setHasFixedSize(true);
        mRvFeeds.setLayoutManager(layoutManager);
        mRvFeeds.setAdapter(mFeedAdapter);

        // add scroll listener
        mRvFeeds.addOnScrollListener(new RecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                Log.d("Android", "Load More");
                onLoadMoreFeed();
            }

            @Override
            public int getFooterViewType() {
                if (feedList.size() > 0) {
                    return feedList.get(feedList.size() - 1) != null ? 0 : 1;
                } else {
                    return 0;
                }
            }
        });

        //add new feed
        FloatingActionButton btnAdd = (FloatingActionButton) mView.findViewById(R.id.fab);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CreateFeedActivity.class);
                startActivityForResult(i, Constant.created);
            }
        });
        return mView;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLoadFoodType(List<FoodType> foodTypes) {

    }

    @Override
    public void onLoadFeed(Feed feed) {

    }

    public void onLoadMoreFeed() {
        feedList.add(null);
        mFeedAdapter.notifyItemInserted(feedList.size() - 1);

        //load more
        feedPresenter.onFeedMore();
    }

    @Override
    public void onFinishLoadMore(List<Feed> feeds) {
        //Remove loading item
        feedList.remove(feedList.size() - 1);
        mFeedAdapter.notifyItemRemoved(feedList.size());

        // fetch data here
        int index = feedList.size();
        feedList.addAll(feeds);

        mFeedAdapter.notifyItemRangeInserted(index, feedList.size() - index);
        mFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadFeeds(List<Feed> feeds) {

        feedList.addAll(feeds);
        mFeedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onProfileClick(Feed feedItem) {
        Intent i = new Intent(getActivity(), ProfileActivity.class);
        Bundle b = new Bundle();
        b.putString("username", feedItem.getUsername());
        b.putString("imgurl", feedItem.getImgUserUrl());
        i.putExtras(b);

        startActivity(i);

        //transition
        getActivity().overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    @Override
    public void onLikeClick() {
        Log.d("----", "onLikeClick");
    }

    @Override
    public void onFeedImageClick(final Feed feedItem) {
        //Feed Item Data
        String title = feedItem.getName();
        String imgFeedUrl = feedItem.getImageUrl();

        // Get the transition name from the string
        String transImgfeeditem = getString(R.string.transition_imgfeeditem);
        String transTitle = getString(R.string.transition_title);

        Intent i = new Intent(getActivity(), FeedDetailActivity.class);
        Bundle b = new Bundle();
        b.putString("title", title);
        b.putString("imgFeedUrl", imgFeedUrl);
        b.putInt("userId", feedItem.getUserId());
        i.putExtras(b);

        //share elements transition
        View img_feed = mView.findViewById(R.id.img_feed);
        View txt_title = mView.findViewById(R.id.txt_feed_title);
        Pair<View, String> p1 = Pair.create(img_feed, transImgfeeditem);
        Pair<View, String> p2 = Pair.create(txt_title, transTitle);

        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), p1, p2);

        startActivity(i, options.toBundle());

        //transition
        getActivity().overridePendingTransition(R.transition.right_in, R.transition.left_out);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Constant.created) {
            feedList.clear();
            feedPresenter.onFeedUpdate();
        }
    }

    /**
     * Fixed: IndexOutOfBoundsException: Inconsistency detected
     * https://code.google.com/p/android/issues/detail?id=77846
     *
     */
    static class LinearLayoutManagerExt extends LinearLayoutManager {

        public LinearLayoutManagerExt(Context context) {
            super(context);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler arg0, RecyclerView.State arg1) {
            try {
                super.onLayoutChildren(arg0, arg1);
            } catch (Exception e) {
                Log.d("Android","onLayoutChildren :" + e.toString());
            }

        }
    }
}





